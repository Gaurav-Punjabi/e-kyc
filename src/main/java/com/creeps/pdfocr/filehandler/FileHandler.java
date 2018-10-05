package com.creeps.pdfocr.filehandler;

import com.creeps.pdfocr.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileHandler {
    @Autowired
    private Logger logger;

    private final static String TAG="FileHandlerService";
    public boolean createTempFile(MultipartFile file){
        try{
            byte[] bytes = file.getBytes();
            String rootPath = System.getProperty("catalina.home");
            File tempDir = new File(rootPath+ File.separator+"tmpFiles");
            if(!tempDir.exists()) {
                tempDir.mkdirs();
                logger.log(TAG, "CREATED DIR");
            }

            File tempFile = new File(tempDir.getAbsolutePath()+File.separator+file.getOriginalFilename());

            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(tempFile));
            bout.write(file.getBytes());
            bout.close();
            logger.log(TAG,tempDir.getAbsolutePath());
            logger.log(TAG, "WROTE to file");

            return true;
        }catch (IOException ioe){
            this.logger.log(TAG, "ioException");
            ioe.printStackTrace();
        }

        return false;

    }
}
