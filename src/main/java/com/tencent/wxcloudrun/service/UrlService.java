package com.tencent.wxcloudrun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.wxcloudrun.entity.Url;
import com.tencent.wxcloudrun.entity.User;
import com.tencent.wxcloudrun.mapper.UrlMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


public interface UrlService extends IService<Url> {

    public int getUrlAmount();

    public List<Url> getUrlByName(String urlName);

    public void addUrl(Url url);




}
