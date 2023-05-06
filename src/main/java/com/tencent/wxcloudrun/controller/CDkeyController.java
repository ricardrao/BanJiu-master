package com.tencent.wxcloudrun.controller;


import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.config.QuestionCategory;
import com.tencent.wxcloudrun.entity.CDkey;
import com.tencent.wxcloudrun.entity.User;
import com.tencent.wxcloudrun.service.CDkeyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    private Logger logger = LoggerFactory.getLogger(CDkeyController.class);

    Set<String> questionCategorySet = new HashSet<String>(){{
        add("original");
        add("simulate");
    }};


    //调用示例   /generateCDkey?generateInfo={"validTimes":10,"questionCategory":"simulate"}
    @RequestMapping(value = "/generateCDkey", method = RequestMethod.POST)
    public ApiResponse generateCDkey(@RequestBody String generateInfo){
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

    // 示例：/consumeCDkey?userPhoneNumber=18761181193&&CDkey=d2792e5fc38448619b6a2eccd6979f06
    @RequestMapping(value = "/consumeCDkey", method = RequestMethod.POST)
    public ApiResponse consumeCDkey(@RequestBody JSONObject params){
        //user直接从当前操作账号获取，怎么实现
        if(params==null || "".equals(params.toString())){
            return ApiResponse.error("please input request body");
        }
        String CDkey = (String) params.get("CDkey");
        String userPhoneNumber = (String) params.get("userPhoneNumber");

        if(CDkey==null || "".equals(CDkey)){
            return ApiResponse.error("please input CDkey");
        }
        CDkey cdkey = cdkeyService.checkCDkey(CDkey);
        if(cdkey == null){
            return ApiResponse.error("cdkey is not exist or is already used");
        }
        if(userPhoneNumber==null || "".equals(userPhoneNumber)){
            return ApiResponse.error("invalid user");
        }
        ApiResponse result = null;
        try {
            result = cdkeyService.userConsumeCDkey(userPhoneNumber, cdkey);
        } catch (Exception e){
            logger.info(String.valueOf(e));
            return ApiResponse.error("error");
        }
        return result;

    }



}
