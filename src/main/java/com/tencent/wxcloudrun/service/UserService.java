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

    public List<User> getUserByName(String name){
        return userMapper.getUserByName(name);
    }

    public boolean userIsExist(String account, String phoneNumber){
        List<User> userList1 = userMapper.getUserByAccount(account);
        List<User> userList2 = userMapper.getUserByPhoneNumber(phoneNumber);
        if(userList1.size() == 0 && userList2.size() == 0){
            return false;
        } else return true;
    }

    public void addUser(User user){
        userMapper.addUser(user);
    }

}
