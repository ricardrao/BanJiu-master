package com.tencent.wxcloudrun.mapper;

import com.tencent.wxcloudrun.entity.CDkey;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CDkeyMapper {


    @Insert("insert into cdkey (cdkey, questionCategory, validTimes, createTime, isUsed)" +
            "values (#{CDkey.cdkey}, #{CDkey.questionCategory}, #{CDkey.validTimes}, #{CDkey.createTime}, #{CDkey.isUsed})")
    void generateCDkey(@Param("CDkey") CDkey key);


}
