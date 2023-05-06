package com.tencent.wxcloudrun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.entity.Url;
import com.tencent.wxcloudrun.entity.User;
import com.tencent.wxcloudrun.mapper.UrlMapper;
import com.tencent.wxcloudrun.service.UrlService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UrlServiceImpl extends ServiceImpl<UrlMapper, Url> implements UrlService {
    /*

     */

    @Resource
    private UrlMapper urlMapper;

    public int getUrlAmount(){
        return this.urlMapper.selectCount(null);
    }

    public List<Url> getUrlByName(String urlName){
        QueryWrapper<Url> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("urlName", urlName);

        List<Url> urlByName = urlMapper.selectList(queryWrapper);
        return urlByName;
    }

    public void addUrl(Url url){
        this.save(url);
    }


}
