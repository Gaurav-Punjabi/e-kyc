package com.creeps.pdfocr.pdfocr;

import com.creeps.pdfocr.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RequestMapping("api/vi/pdfocr")
@RestController
public class PdfOcrController {
    private final static String TAG = "PdfOcrController";

    @Autowired
    private Logger logger;
    @RequestMapping(value = "uploadfile", method = RequestMethod.POST)
    public ResponseEntity<String> obtainMultipartPDF(@RequestParam("name") String fileName, @RequestParam("file")MultipartFile file){

        if(!file.isEmpty()){
            try{
                byte[] bytes = file.getBytes();
                String rootPath = System.getProperty("catalina.home");
                File tempDir = new File(rootPath+ File.separator+"tmpFiles");
                if(!tempDir.exists()) {
                    tempDir.mkdirs();
                    logger.log(TAG, "CREATED DIR");
                }

                File tempFile = new File(tempDir.getAbsolutePath()+File.separator+fileName);

                BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(tempFile));
                bout.write(file.getBytes());
                bout.close();

                logger.log(TAG, "WROTE to file");

                return ResponseEntity.ok(fileName+" uploaded ");
            }catch (IOException ioe){
                this.logger.log(TAG, "ioException");
                ioe.printStackTrace();
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fileName+" couldnt be uploaded");

    }
}
