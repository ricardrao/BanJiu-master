package com.tencent.wxcloudrun.controller;


import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.entity.User;
import com.tencent.wxcloudrun.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/getUserAmount")
    public ApiResponse getUserAmount(){
        int userAmount = userService.getUserAmount();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userAmount", userAmount);
        logger.info("\"/getUserAmount\" Api Response = " + jsonObject.toJSONString());
        return ApiResponse.ok(jsonObject);
    }

    @RequestMapping("/getUserInfo")
    public ApiResponse getUserInfo(@RequestParam(value = "userName") String uesrName){
        if(uesrName==null || "".equals(uesrName)){
            return ApiResponse.error("please input userName");
        }
        List<User> userList = userService.getUser(uesrName);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user", userList);
        return ApiResponse.ok(jsonObject);
    }


}
