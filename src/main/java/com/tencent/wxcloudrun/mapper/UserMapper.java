package com.tencent.wxcloudrun.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.wxcloudrun.entity.File;
import com.tencent.wxcloudrun.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    /*
    @Select("select count(*) from Users")
    int getUserAmount();

    @Select("select * from Users where userName = #{userName}")
    List<User> getUserByName(@Param("userName") String userName);

    @Select("select * from Users where account = #{account}")
    List<User> getUserByAccount(@Param("account") String account);

    @Select("select * from Users where phoneNumber = #{phoneNumber}")
    List<User> getUserByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Insert("insert into Users (account, password, userName, phoneNumber, userCategory, createTime, updateTime, homeworkCorrectionTimes) " +
            "values (#{user.account}, #{user.password}, #{user.userName}, #{user.phoneNumber}, #{user.userCategory}, #{user.createTime}, #{user.updateTime}, 0)")
    void addUser(@Param("user") User user);

    @Select("select * from Users where account = #{account} and password = #{password}")
    List<User> getUserByAccountAndPassword(@Param("account") String account, @Param("password") String password);

    @Update("update Users set homeworkCorrectionTimes = homeworkCorrectionTimes + #{times}, updateTime = #{updateTime}" +
            "where phoneNumber = #{user.phoneNumber}")
    void updateUserValidTimes(@Param("user") User user, int times, String updateTime);

     */
}
