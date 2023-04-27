package com.tencent.wxcloudrun.mapper;

import com.tencent.wxcloudrun.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select count(*) from Users")
    int getUserAmount();

    @Select("Select * from Users where userName = #{userName}")
    List<User> getUser(@Param("userName") String userName);

}
