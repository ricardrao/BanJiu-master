package com.tencent.wxcloudrun.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tencent.wxcloudrun.config.*;
import com.tencent.wxcloudrun.entity.StudentRemainTimes;
import com.tencent.wxcloudrun.entity.User;
import com.tencent.wxcloudrun.service.StudentRemainTimesService;
import com.tencent.wxcloudrun.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentRemainTimesService studentRemainTimesService;

    private HashSet<String> userCategorySet = new HashSet<String>(){{
        add(UserCategory.STUDENT.name());
        add(UserCategory.ADMINISTRATOR.name());
        add(UserCategory.TEACHER.name());
    }};

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/getUserAmount")
    public ApiResponse getUserAmount(){
        int userAmount = userService.count();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userAmount", userAmount);
        logger.info("\"/getUserAmount\" Api Response = " + jsonObject.toJSONString());
        return ApiResponse.ok(jsonObject);
    }



    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public ApiResponse getUserInfo(@RequestParam(value = "userName") String userName){
        if(userName==null || "".equals(userName)){
            return ApiResponse.error("please input userName");
        }

        List<User> userList = userService.getUserByName(userName);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user", userList);
        return ApiResponse.ok(jsonObject);
    }

//调用示例 /registerUser?userInfo={"account":"zbxxx","password":"zbx111","userName":"朱柏贤","phoneNumber":"1823123293","userCategory":"STUDENT"}
    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    @Transactional
    public ApiResponse registerUser(@RequestBody String userInfo){
        if(userInfo==null || "".equals(userInfo)){
            return ApiResponse.error("no user message!");
        }
        User user = JSONObject.parseObject(userInfo, User.class);

        String account = user.getAccount();
        String password = user.getPassword();
        String phoneNumber = user.getPhoneNumber();
        String userName = user.getUserName();
        String userCategory = user.getUserCategory();

        if(account==null || "".equals(account)){
            return ApiResponse.error("illegal account!");
        }
        if(password==null || "".equals(password)){
            return ApiResponse.error("illegal password!");
        }
        if(phoneNumber==null || "".equals(phoneNumber)){
            return ApiResponse.error("illegal phoneNumber!");
        }
        if(userName==null || "".equals(userName)){
            return ApiResponse.error("illegal userName!");
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account)
                .eq("phoneNumber", phoneNumber);

        if(userService.getBaseMapper().selectOne(queryWrapper) == null){
            return ApiResponse.error("user exists");
        }

        if(!userCategorySet.contains(userCategory)){
            return ApiResponse.error("user category is not allowed");
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        User newUser = new User(account, password, userName, phoneNumber,
                                userCategory, formatter.format(new Date()), formatter.format(new Date()), 0, 0, "", "");

        userService.getBaseMapper().insert(newUser);

        User userByAccountAndPassword = userService.getUserByAccountAndPassword(newUser.getAccount(), newUser.getPassword());
        StudentRemainTimes studentRemainTimes = new StudentRemainTimes();
        studentRemainTimes.setStudentId(userByAccountAndPassword.getUserId());
        studentRemainTimes.setStudentName(userByAccountAndPassword.getUserName());
        studentRemainTimes.setStudentPhoneNumber(userByAccountAndPassword.getPhoneNumber());


        if(UserCategory.STUDENT.name().equalsIgnoreCase(userByAccountAndPassword.getUserCategory())) {
            studentRemainTimesService.getBaseMapper().insert(studentRemainTimes);
        }
        return ApiResponse.ok();
    }


// 调用示例/login?userInfo={"account":"zbx","password":"zbx111"}
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiResponse login(@RequestBody String userInfo){
        if(userInfo==null || "".equals(userInfo)){
            return ApiResponse.error("no user message!");
        }
        JSONObject userObject = JSONObject.parseObject(userInfo);
        String account = String.valueOf(userObject.get("account"));
        String password = String.valueOf(userObject.get("password"));
        User user = userService.getUserByAccountAndPassword(account, password);
        if(user == null){
            return ApiResponse.error("no such user or a wrong password");
        } else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user", user);
            return ApiResponse.ok(jsonObject);
        }

    }


}
