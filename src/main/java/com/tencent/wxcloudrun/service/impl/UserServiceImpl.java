package com.tencent.wxcloudrun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.config.ApiResponse;
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

    public String getUserExistsResult(String account, String phoneNumber){
        QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
        QueryWrapper<User> queryWrapper2 = new QueryWrapper<>();
        LambdaQueryWrapper<User> userLambdaQueryWrapperAccount = queryWrapper1.lambda().eq(User::getAccount, account);
        LambdaQueryWrapper<User> userLambdaQueryWrapperPhoneNumber = queryWrapper2.lambda().eq(User::getPhoneNumber, phoneNumber);
        List<User> userList1 = userMapper.selectList(userLambdaQueryWrapperAccount);
        List<User> userList2 = userMapper.selectList(userLambdaQueryWrapperPhoneNumber);
        if(userList1!=null && userList1.size()>0){
            return "user account has already existed";
        } else if (userList2!=null && userList2.size()>0){
            return "user phone number has already existed";
        } else {
            return "approved";
        }
    }
    public void updateUserValidTimes(User user, int validTimes, String updateTimes){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("userId", user.getUserId());
        user.setHomeworkCorrectionTimes(user.getHomeworkCorrectionTimes()+validTimes);
        user.setUpdateTime(updateTimes);
        userMapper.update(user,updateWrapper);
    }

    public List<User> getUserByName(String userName){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("userName", userName);
        List<User> user = userMapper.selectList(wrapper);
        return user;
    }

    public List<User> getUserByPhoneNumber(String userPhoneNumber){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phoneNumber", userPhoneNumber);

        List<User> userList = userMapper.selectList(queryWrapper);
        return userList;
    }

    public List<User> getUserByAccountAndPassword(String account, String password){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account)
                .eq("password", password);

        List<User> userList = userMapper.selectList(queryWrapper);
        return userList;
    }

}
