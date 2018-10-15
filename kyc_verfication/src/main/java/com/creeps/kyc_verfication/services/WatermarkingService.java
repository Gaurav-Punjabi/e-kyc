package com.creeps.kyc_verfication.services;

import org.springframework.stereotype.Service;

@Service
public class WatermarkingService {
    public byte[] watermarkPDF(final String message,
                               final byte[] fileContent) {
        return append(fileContent, message.getBytes());
    }

    private byte[] append(final byte[] source,
                          final byte[] destination) {
        // create a destination array that is the size of the two arrays
        byte[] result = new byte[source.length + destination.length];
        for(int i=0;i<destination.length;i++)
            result[i] = destination[i];
        for(int i=destination.length,j=0;j<source.length;i++,j++)
            result[i] = source[j];

        for(int i=0;i<destination.length;i++)
            System.out.println("result = " + result[i]);
        return result;
    }


    publicw String extractData(final byte[] bytes) {
        String text = new String(bytes);

        return text.replaceAll("%PHP.*$", "");
    }
}
