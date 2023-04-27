package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.entity.User;
import com.tencent.wxcloudrun.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public int getUserAmount(){
        return userMapper.getUserAmount();
    }

    public List<User> getUser(String name){
        return userMapper.getUser(name);
    }

}
