package org.hackathon.distributedfs.distributedFs.service;

import org.hackathon.distributedfs.distributedFs.model.FileFragment;
import org.hackathon.distributedfs.distributedFs.model.FileServer;
import org.hackathon.distributedfs.distributedFs.model.entity.File;
import org.hackathon.distributedfs.distributedFs.model.entity.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileDeliveryService {
    @Autowired
    private PerformPost performPost;
    @Autowired
    private FilePartService filePartService;

    /*this service is responsible for delivering the parts of file to different servers */
    public void deliverFiles(File file,List<FileFragment> fragments, List<Server> servers) throws Exception{
        for (int i = 0;i<fragments.size();i++){
            Server destinationServer = servers.get(i%servers.size());
            String destinationUrl = destinationServer.getUrl();
            //destinationUrl = "http://localhost:8080/receiveFragment";
            //System.out.println("content = "+fragments.get(i).getContent());
            //while(performPost.post(destinationUrl,fragments.get(i).getContent()) != 200);
            System.out.println("before performing delivery");
            performPost.post(destinationUrl,file.getId()+"-"+i,fragments.get(i).getContent());
            System.out.println("after performing delivery");
            /*code to add entry to database */
            filePartService.add((long)fragments.get(i).getContent().length(),destinationServer,file);
        }
    }
}
