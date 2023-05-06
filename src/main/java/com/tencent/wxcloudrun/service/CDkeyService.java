package com.tencent.wxcloudrun.service;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.config.QuestionCategory;
import com.tencent.wxcloudrun.config.UserCategory;
import com.tencent.wxcloudrun.entity.CDkey;
import com.tencent.wxcloudrun.entity.StudentRemainTimes;
import com.tencent.wxcloudrun.entity.User;
import com.tencent.wxcloudrun.mapper.CDkeyMapper;
import com.tencent.wxcloudrun.mapper.StudentRemainTimesMapper;
import com.tencent.wxcloudrun.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public interface CDkeyService extends IService<CDkey> {

    public void generateCDkey(CDkey cdkey);

    public CDkey checkCDkey(String cdkey);

    public void expireCDkey(User user, CDkey cdkey);

    @Transactional
    public ApiResponse userConsumeCDkey(String userPhoneNumber, CDkey cdkey);

}
