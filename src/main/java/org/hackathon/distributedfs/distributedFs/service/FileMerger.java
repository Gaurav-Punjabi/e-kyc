package org.hackathon.distributedfs.distributedFs.service;

import org.hackathon.distributedfs.distributedFs.model.FileFragment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileMerger {
    public String merge(List<FileFragment> fragments){
        String content = "";
        for( int i = 0;i<fragments.size();i++){
            content += fragments.get(i).getContent();
        }
        return content;
    }
}
