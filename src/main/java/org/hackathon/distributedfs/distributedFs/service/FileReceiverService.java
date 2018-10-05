package org.hackathon.distributedfs.distributedFs.service;

import org.hackathon.distributedfs.distributedFs.model.entity.File;
import org.hackathon.distributedfs.distributedFs.model.entity.FilePart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.util.ArrayList;

@Service
public class FileReceiverService {
    @Autowired
    private PerformGet performGet;
    @Autowired
    private FilePartService filePartService;


    public String receiveFiles(File file) throws Exception{
        ArrayList<FilePart> fileParts = filePartService.findAllByFileId(file.getId());
        String content = "";
        for(int i = 0;i<fileParts.size();i++){
            FilePart filePart = fileParts.get(i);
            String name = file.getId()+"-"+i;
            String url = filePart.getServer().getUrl().substring(0,21);
            url +="/readFragment/"+name;
            System.out.println("path for reading = "+url);
            String temp = performGet.get(url);
            /*File temp = performGet(url);*/
            /*temp = URLDecoder.decode(temp, "UTF-8");;*/
            System.out.println(name +" = "+temp.length());
            //System.out.println(" db content = "+filePart.getContent().length());
            content += temp;
        }
        System.out.println("received content length = "+content.length());
        return content;
    }
}
