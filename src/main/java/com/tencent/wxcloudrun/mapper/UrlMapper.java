package com.tencent.wxcloudrun.mapper;


import com.tencent.wxcloudrun.entity.Url;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UrlMapper {

    @Select("select count(*) from Urls")
    int getUrlAmount();

    @Select("select * from Urls where url = #{url}")
    List<Url> getUrl(@Param("url") String url);


    @Insert("insert into Urls (urlName, urlCategory, urlImage, createTime, updateTime) " +
            "values (#{url.urlName}, #{url.urlCategory}, #{url.urlImage}, #{url.createTime}, #{url.updateTime})")
    void addUrl(@Param("url") Url url);

}