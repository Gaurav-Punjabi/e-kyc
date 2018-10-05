package org.hackathon.distributedfs.distributedFs.service;

import org.hackathon.distributedfs.distributedFs.model.Server;
import org.hackathon.distributedfs.distributedFs.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServerService {
    @Autowired
    ServerRepository serverRepository;

    /*following code will register a new server as a target for file storage*//*
    public void registerServer(String url ){

    }*/

    /*following code will return a list of all servers */
    public ArrayList<Server> findAll(){
        ArrayList<Server> list = new ArrayList<>();
        serverRepository.findAll().forEach(server -> list.add(server));
        return list;
    }

}
