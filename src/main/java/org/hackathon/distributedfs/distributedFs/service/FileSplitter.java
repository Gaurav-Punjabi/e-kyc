package org.hackathon.distributedfs.distributedFs.service;

import org.hackathon.distributedfs.distributedFs.model.FileFragment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedList;
import java.util.List;

@Service
public class FileSplitter {
    public List<FileFragment> split(MultipartFile multipartFile,int count) throws Exception{
        return split(new String(multipartFile.getBytes()),count);
    }

    public List<FileFragment> split(String content,int count) throws Exception{
        List<FileFragment> fragments = new LinkedList<>();
        int partitionSize = content.length()/count;
        boolean flag = false;
        if(content.length()%count != 0){
            flag = true;
        }
        for(int i = 0;i<count;i++){
            String temp = content.substring(i*partitionSize,i*partitionSize+partitionSize);
            FileFragment fragment = new FileFragment();
            fragment.setContent(temp);
            fragments.add(fragment);
        }
        if(flag){
            String temp = content.substring(content.length()-(content.length()%count));
            FileFragment fragment = new FileFragment();
            fragment.setContent(temp);
            fragments.add(fragment);
        }
        return fragments;
    }
}
