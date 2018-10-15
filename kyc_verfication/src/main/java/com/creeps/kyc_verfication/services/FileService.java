package com.creeps.kyc_verfication.services;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileService {
    public boolean writeFileContent(final String path,
                                    final byte[] data) {
        try {
            File file = new File(path);

            if(!file.exists())
                return false;
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(data);
            return true;
        } catch(IOException ioe) {
            ioe.printStackTrace();
            return false;
        }
    }
}
