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
            byte dataBytes[] = file.getBytes();
            /*if(!is_pdf(dataBytes))
                return false;
            */
            bout.write(dataBytes);
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

    /**
     * Test if the data in the given byte array represents a PDF file.
     */
    public static boolean is_pdf(byte[] data) {
        if (data != null && data.length > 4 &&
                data[0] == 0x25 && // %
                data[1] == 0x50 && // P
                data[2] == 0x44 && // D
                data[3] == 0x46 && // F
                data[4] == 0x2D) { // -

            // version 1.3 file terminator
            if (data[5] == 0x31 && data[6] == 0x2E && data[7] == 0x33 &&
                    data[data.length - 7] == 0x25 && // %
                    data[data.length - 6] == 0x25 && // %
                    data[data.length - 5] == 0x45 && // E
                    data[data.length - 4] == 0x4F && // O
                    data[data.length - 3] == 0x46 && // F
                    data[data.length - 2] == 0x20 && // SPACE
                    data[data.length - 1] == 0x0A) { // EOL
                return true;
            }

            // version 1.3 file terminator
            if (data[5] == 0x31 && data[6] == 0x2E && data[7] == 0x34 &&
                    data[data.length - 6] == 0x25 && // %
                    data[data.length - 5] == 0x25 && // %
                    data[data.length - 4] == 0x45 && // E
                    data[data.length - 3] == 0x4F && // O
                    data[data.length - 2] == 0x46 && // F
                    data[data.length - 1] == 0x0A) { // EOL
                return true;
            }
        }
        return false;
    }
}
