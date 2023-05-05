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

    public String userIsExist(String account, String phoneNumber){
        List<User> userList1 = userMapper.getUserByAccount(account);
        List<User> userList2 = userMapper.getUserByPhoneNumber(phoneNumber);
        if(userList1!=null && userList1.size()!=0){
            return "user account has already existed";
        } else if(userList2!=null && userList2.size()!=0){
            return "user phone number has already existed";
        } else {
            return "approved";
        }
    }

    public void addUser(User user){
        userMapper.addUser(user);
    }

    public User getUserByAccountAndPassword(String account, String password){
        List<User> userList = userMapper.getUserByAccountAndPassword(account, password);
        if(userList==null || userList.size()==0){
            return null;
        }
        return userList.get(0);
    }

}
