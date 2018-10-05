package org.hackathon.distributedfs.distributedFs.service.io;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;

@Service
public class FileIo {
    public String write(String name,byte[] content) throws Exception{
        File file = new File(name);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(content);
        fos.flush();
        return file.getCanonicalPath();
    }

    public byte[] read(String name) throws Exception{
        /*FileInputStream fis = new FileInputStream(new File(name));
        BufferedReader br= new BufferedReader(new InputStreamReader(fis));
        String content = "";
        while (true) {
            String temp = br.readLine();
            if(temp == null){
                break;
            }
            content+=temp+"\n";
        }
        return content;*/
        /*FileReader fr =
                new FileReader(name);

        String content = "";
        int i;
        while ((i=fr.read()) != -1)
            *//*System.out.print((char) i);*//*
            content += (char)i;
        return content;*/
        /*return new String(Files.readAllBytes(new File(name).toPath()));*/
        return (Files.readAllBytes(new File(name).toPath()));
    }
}