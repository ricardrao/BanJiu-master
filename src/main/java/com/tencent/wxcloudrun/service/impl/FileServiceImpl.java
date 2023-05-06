package com.tencent.wxcloudrun.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.entity.File;
import com.tencent.wxcloudrun.mapper.FileMapper;
import com.tencent.wxcloudrun.service.FileService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

}
