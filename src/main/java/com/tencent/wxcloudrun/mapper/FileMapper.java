package com.tencent.wxcloudrun.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.wxcloudrun.entity.File;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FileMapper extends BaseMapper<File> {
    /*
    @Insert("insert into Files (fileName, filePrefix, createTime, updateTime) " +
            "values (#{file.fileName}, #{file.filePrefix}, #{file.createTime}, #{file.updateTime})")
    void addFile(@Param("file") File file);

     */
}