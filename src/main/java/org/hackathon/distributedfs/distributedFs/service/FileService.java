package org.hackathon.distributedfs.distributedFs.service;

import org.hackathon.distributedfs.distributedFs.model.entity.File;
import org.hackathon.distributedfs.distributedFs.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    public File findById(Long id){
        return fileRepository.findById(id).orElse(null);
    }
    public void add(String name,Long size){
        File file = new File();
        file.setName(name);
        file.setSie(size);
        fileRepository.save(file);
    }
    public File save(String name,Long size){
        File file = new File();
        file.setSie(size);
        file.setName(name);
        fileRepository.save(file);
        return file;
    }
}
