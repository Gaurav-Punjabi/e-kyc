package com.creeps.pdfocr.pdfocr;

import com.creeps.pdfocr.filehandler.FileHandler;
import com.creeps.pdfocr.logger.Logger;
import com.creeps.pdfocr.remotecall.RemoteCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
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
    private final static String OCR_SPACE_API_KEY = "cc217ec21388957";

    private final static String OCR_URL = "https://api.ocr.space/parse/image";
    @Autowired
    private Logger logger;

    @Autowired
    private FileHandler fileHandler;

    @Autowired
    private RemoteCaller remoteCaller;

    @RequestMapping(value = "uploadfile", method = RequestMethod.POST)
    public ResponseEntity<String> obtainMultipartPDFData(@RequestParam("file")MultipartFile file){

        if(!file.isEmpty()){



            if(fileHandler.createTempFile(file)) {

                // file created successfully

                // todo sending remote call to ocr.space

                String response = this.remoteCaller.getOCRData(OCR_URL, OCR_SPACE_API_KEY, file);
                return ResponseEntity.status(HttpStatus.OK).body(response);

            }



        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(file.getOriginalFilename()+" couldnt be uploaded");

    }





}
