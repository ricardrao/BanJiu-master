package com.tencent.wxcloudrun.service;


import com.tencent.wxcloudrun.entity.CDkey;
import com.tencent.wxcloudrun.mapper.CDkeyMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CDkeyService {

    @Resource
    private CDkeyMapper cdkeyMapper;

    public void generateCDkey(CDkey cdkey){
        cdkeyMapper.generateCDkey(cdkey);
    }

}
