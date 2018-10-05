package org.hackathon.distributedfs.distributedFs.controller;

import org.hackathon.distributedfs.distributedFs.model.FileFragment;
import org.hackathon.distributedfs.distributedFs.model.entity.File;
import org.hackathon.distributedfs.distributedFs.service.*;
import org.hackathon.distributedfs.distributedFs.service.io.FileIo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/distributed/v1")
public class DeliveryController {
    public final String BASE_PATH = "base/";

    @Autowired
    private FileDeliveryService fileDeliveryService;

    @Autowired
    private FileSplitter fileSplitter;

    @Autowired
    private ServerService serverService;

    @Autowired
    private FileService fileService;

    @Autowired
    private FileReceiverService fileReceiverService;

    @Autowired
    FileIo fileIo;

    @PostMapping("/split")
    public ResponseEntity<?> split(@RequestParam("file")MultipartFile multipartFile) throws Exception{
        String fileContent = new String(multipartFile.getBytes());
        List<FileFragment> fragments =  fileSplitter.split(fileContent,(int)serverService.getCount());
        File file = fileService.save(multipartFile.getOriginalFilename(),multipartFile.getSize());
        String path = fileIo.write(BASE_PATH+file.getId()+".pdf",multipartFile.getBytes());
        fileDeliveryService.deliverFiles(file,fragments,serverService.findAll());
        return ResponseEntity.ok(null);
    }

    @GetMapping("/merge/{fileId}")
    public ResponseEntity<?> merge(@PathVariable("fileId") Long fileId) throws Exception{
        System.out.println("id = "+fileId);
        File file = fileService.findById(fileId);
        String content = fileReceiverService.receiveFiles(file);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        // Here you have to set the actual filename of your pdf
        String filename = BASE_PATH+fileId+".pdf";
        byte [] byteContent = fileIo.read(filename);
        System.out.println("file path = "+filename);
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(byteContent, headers, HttpStatus.OK);
        return response;
    }
}
