package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.entity.Url;
import com.tencent.wxcloudrun.mapper.UrlMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UrlService {

    @Resource
    private UrlMapper urlMapper;

    public int getUrlAmount(){
        return urlMapper.getUrlAmount();
    }

    public List<Url> getUrlByName(String urlName){
        return urlMapper.getUrlByName(urlName);
    }

    public void addUrl(Url url){
        urlMapper.addUrl(url);
    }


}
