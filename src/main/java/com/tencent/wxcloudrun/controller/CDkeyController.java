package com.tencent.wxcloudrun.controller;


import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.entity.CDkey;
import com.tencent.wxcloudrun.service.CDkeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
public class CDkeyController {

    @Autowired
    private CDkeyService cdkeyService;


    Set<String> questionCategorySet = new HashSet<String>(){{
        add("original");
        add("simulate");
    }};


    //调用示例   /generateCDkey?generateInfo={"validTimes":10,"questionCategory":"simulate"}
    @RequestMapping("/generateCDkey")
    public ApiResponse generateCDkey(@RequestParam(value = "generateInfo") String generateInfo){
        if(generateInfo==null || "".equals(generateInfo)){
            return ApiResponse.error("generate infomation is null");
        }
        CDkey cdkey = JSONObject.parseObject(generateInfo, CDkey.class);
        String questionCategory = cdkey.getQuestionCategory();
        int validTimes = cdkey.getValidTimes();
        if(questionCategory==null || "".equals(questionCategory)){
            return ApiResponse.error("invalid question category");
        }
        if(!questionCategorySet.contains(questionCategory)){
            return ApiResponse.error("invalid question category");
        }
        if(validTimes<=0){
            return ApiResponse.error("invalid times");
        }
        String key = UUID.randomUUID().toString().replace("-", "");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String currentTime = formatter.format(new Date());


        cdkey.setCdkey(key);
        cdkey.setCreateTime(currentTime);
        cdkey.setUsed(false);

        cdkeyService.generateCDkey(cdkey);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("CDkeyInfomation", cdkey);

        return ApiResponse.ok(jsonObject);
    }
}
