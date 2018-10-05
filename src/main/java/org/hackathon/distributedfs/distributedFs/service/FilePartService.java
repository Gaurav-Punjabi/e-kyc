package org.hackathon.distributedfs.distributedFs.service;

import org.hackathon.distributedfs.distributedFs.model.entity.File;
import org.hackathon.distributedfs.distributedFs.model.entity.FilePart;
import org.hackathon.distributedfs.distributedFs.model.entity.Server;
import org.hackathon.distributedfs.distributedFs.repository.FilePartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FilePartService {
    @Autowired
    FilePartRepository filePartRepository;

    public void add(Long size, Server server, File file){
        FilePart filePart = new FilePart();
        filePart.setFile(file);
        filePart.setServer(server);
        filePart.setSize(size);
        /*filePart.setContent(content);*/
        filePartRepository.save(filePart);
    }

    public ArrayList<FilePart> findAllByFileId(Long id){
        ArrayList<FilePart> fileParts = new ArrayList<>();
        filePartRepository.findAllByFileId(id).forEach(filePart -> fileParts.add(filePart));
        return fileParts;
    }

}
