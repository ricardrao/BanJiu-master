package com.tencent.wxcloudrun.mapper;

import com.tencent.wxcloudrun.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select count(*) from Users")
    int getUserAmount();

    @Select("select * from Users where userName = #{userName}")
    List<User> getUserByName(@Param("userName") String userName);

    @Select("select * from Users where account = #{account}")
    List<User> getUserByAccount(@Param("account") String account);

    @Select("select * from Users where phoneNumber = #{phoneNumber}")
    List<User> getUserByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Insert("insert into Users (account, password, userName, phoneNumber, userCategory, createTime, updateTime) " +
            "values (#{user.account}, #{user.password}, #{user.userName}, #{user.phoneNumber}, #{user.userCategory}, #{user.createTime}, #{user.updateTime})")
    void addUser(@Param("user") User user);

    @Select("select * from Users where account = #{account} and password = #{password}")
    List<User> getUserByAccountAndPassword(@Param("account") String account, @Param("password") String password);
}
