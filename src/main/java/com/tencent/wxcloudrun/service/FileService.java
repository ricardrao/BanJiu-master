package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.entity.File;

import com.tencent.wxcloudrun.mapper.FileMapper;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FileService {

    @Resource
    private FileMapper fileMapper;


    public void addFile(File file){
        fileMapper.addFile(file);
    }


}