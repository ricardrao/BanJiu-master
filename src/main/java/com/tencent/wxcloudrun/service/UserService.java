package com.tencent.wxcloudrun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.entity.File;
import com.tencent.wxcloudrun.entity.User;
import com.tencent.wxcloudrun.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


public interface UserService extends IService<User> {


    //获取用户数量

    //根据名称查询用户
    List<User> getUserByName(String UserName);

    //
    List<User> getUserByPhoneNumber(String phoneNumber);
    //用户是否存在

    //增加用户

    //根据账号密码查询用户
    List<User> getUserByAccountAndPassword(String account, String password);

    //
    void updateUserValidTimes(User user, int validTimes, String updateTimes);

    String getUserExistsResult(String account, String phoneNumber);

}
