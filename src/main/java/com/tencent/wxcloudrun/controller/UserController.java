package com.tencent.wxcloudrun.controller;


import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.config.*;
import com.tencent.wxcloudrun.entity.User;
import com.tencent.wxcloudrun.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    private HashSet<String> userCategorySet = new HashSet<String>(){{
        add(UserCategory.STUDENT.name());
        add(UserCategory.ADMINISTRATOR.name());
        add(UserCategory.TEACHER.name());
    }};

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
        List<User> userList = userService.getUserByName(uesrName);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user", userList);
        return ApiResponse.ok(jsonObject);
    }

//调用示例 /registerUser?userInfo={"account":"zbxxx","password":"zbx111","userName":"朱柏贤","phoneNumber":"1823123293","userCategory":"student"}
    @RequestMapping("/registerUser")
    public ApiResponse registerUser(@RequestParam(value = "userInfo") String userInfo){
        if(userInfo==null || "".equals(userInfo)){
            return ApiResponse.error("no user message!");
        }
        JSONObject userObject = JSONObject.parseObject(userInfo);
        String account = String.valueOf(userObject.get("account"));
        String password = String.valueOf(userObject.get("password"));
        String phoneNumber = String.valueOf(userObject.get("phoneNumber"));
        String userName = String.valueOf(userObject.get("userName"));
        String userCategory = String.valueOf(userObject.get("userCategory"));

        if(userService.userIsExist(account, phoneNumber)){
            return ApiResponse.error("user exists");
        }

        if(!userCategorySet.contains(userCategory)){
            return ApiResponse.error("user category is not allowed");
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        User user = new User(account, password, userName, phoneNumber,
                                userCategory, formatter.format(new Date()), formatter.format(new Date()));

        userService.addUser(user);
        return ApiResponse.ok();
    }
}
