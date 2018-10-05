package com.creeps.pdfocr.logger;

import org.springframework.stereotype.Service;

@Service
public class Logger {


    public Logger(){}

    /**
     * This method logs the provided data to the terminal
     * @param TAG
     * @param message
     */
    public void log(String TAG, String message){
        System.out.println(TAG+" : "+message);
    }
}
