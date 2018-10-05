package org.hackathon.slaveserver.slaveServer.controller;

import org.hackathon.slaveserver.slaveServer.service.FileIo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import javax.xml.ws.Response;

@RestController
public class DeliveryController {
    public static final String FRAGMENTS_PATH= "fragments/";

    @Autowired
    private FileIo fileIo;
    @PostMapping("/receiveFragment")
    public ResponseEntity<?> receiveFragment(@RequestParam(name ="name") String name,
            @RequestParam(name = "content") String content) throws Exception{
        System.out.println("received "+content.length());
        String path = fileIo.write(FRAGMENTS_PATH+name+".txt",content);
        System.out.println("Path = "+path);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/readFragment/{fragmentId}")
    public String readFragment(@PathVariable("fragmentId") String id) throws Exception{
        System.out.println("id = "+id);
        String fileName = FRAGMENTS_PATH+"/"+id+".txt";
        String content = fileIo.read(fileName);
        System.out.println("content length = "+content.length());
        return (content);
    }
}
