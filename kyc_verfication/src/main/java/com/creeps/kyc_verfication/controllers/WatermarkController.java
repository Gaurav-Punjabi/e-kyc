package com.creeps.kyc_verfication.controllers;

import com.creeps.kyc_verfication.services.FileService;
import com.creeps.kyc_verfication.services.WatermarkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@RestController
@RequestMapping("api/v1/watermarking")
public class WatermarkController {

    @Autowired
    private WatermarkingService watermarkingService;
    @Autowired
    private FileService fileService;

    /**
     * Upload single file using Spring Controller
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<StreamingResponseBody> uploadFileHandler(@RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                String name = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists())
                    dir.mkdirs();
                System.out.println(rootPath);

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                System.out.println("Server File Location=" + serverFile.getAbsolutePath());

                // Watermarking the given pdf file.....
                byte[] modifiedContent = watermarkingService.watermarkPDF("Hello World", bytes);


                // Overwriting the orignal pdf file with the watermarked file
                if(fileService.writeFileContent(serverFile.getAbsolutePath(), modifiedContent)) {
                    System.out.println("The PDF was watermarked sucessfully");
                } else {
                    System.out.println("Couldnt Watermark the PDF");
                }

                final HttpHeaders headers = new HttpHeaders();
                headers.setContentDispositionFormData("filename", file.getOriginalFilename());

                StreamingResponseBody streamingResponseBody = outputStream -> outputStream.write(modifiedContent);

                return ResponseEntity.ok()
                        .headers(headers)
                        .contentType(MediaType.valueOf("application/force-download"))
                        .body(streamingResponseBody);

            } catch (Exception e) {
                e.printStackTrace();

                final HttpHeaders headers = new HttpHeaders();
                headers.setContentDispositionFormData("filename", file.getOriginalFilename());

                StreamingResponseBody streamingResponseBody = outputStream -> outputStream.write(file.getBytes());

                return ResponseEntity.ok()
                        .headers(headers)
                        .contentType(MediaType.valueOf("application/force-download"))
                        .body(streamingResponseBody);
            }
        } else {
            final HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData("filename", file.getOriginalFilename());

            StreamingResponseBody streamingResponseBody = outputStream -> outputStream.write(file.getBytes());

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.valueOf("application/force-download"))
                    .body(streamingResponseBody);
        }
    }

    /**
     * Upload single file using Spring Controller
     */
    @RequestMapping(value = "/extractData", method = RequestMethod.POST)
    public @ResponseBody
    String extractData(@RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                String name = file.getOriginalFilename();
                byte[] bytes = file.getBytes();

                return watermarkingService.extractData(bytes);
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        } else {
            final HttpHeaders headers = new HttpHeaders();
            return "";
        }
    }

}
