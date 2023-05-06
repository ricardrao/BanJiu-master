package com.tencent.wxcloudrun.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.wxcloudrun.entity.Url;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UrlMapper extends BaseMapper<Url> {
    /*
    @Select("select count(*) from Urls")
    int getUrlAmount();

    @Select("select * from Urls where urlName = #{urlName}")
    List<Url> getUrlByName(@Param("urlName") String urlName);


    @Insert("insert into Urls (urlName, urlContent, urlCategory, urlImage, createTime, updateTime) " +
            "values (#{url.urlName}, #{url.urlContent}, #{url.urlCategory}, #{url.urlImage}, #{url.createTime}, #{url.updateTime})")
    void addUrl(@Param("url") Url url);
    */
}