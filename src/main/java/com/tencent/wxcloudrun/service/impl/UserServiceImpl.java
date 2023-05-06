package com.tencent.wxcloudrun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.entity.File;
import com.tencent.wxcloudrun.entity.User;
import com.tencent.wxcloudrun.mapper.FileMapper;
import com.tencent.wxcloudrun.mapper.UserMapper;
import com.tencent.wxcloudrun.service.FileService;
import com.tencent.wxcloudrun.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    /*

    @Resource
    private UserMapper userMapper;

    public int getUserAmount(){
        return userMapper.getUserAmount();
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


    */
    @Resource
    private UserMapper userMapper;

    public List<User> getUserByName(String userName){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("userName", userName);
        List<User> user = userMapper.selectList(wrapper);
        return user;
    }


    public User getUserByAccountAndPassword(String account, String password){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account)
                .eq("password", password);

        User userByAccountAndPassword = userMapper.selectOne(queryWrapper);
        return userByAccountAndPassword;
    }

}
