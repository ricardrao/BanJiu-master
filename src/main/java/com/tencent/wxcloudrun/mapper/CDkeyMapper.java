package com.tencent.wxcloudrun.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.wxcloudrun.entity.CDkey;
import com.tencent.wxcloudrun.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CDkeyMapper extends BaseMapper<CDkey> {

    /*
    @Insert("insert into cdkey (cdkey, questionCategory, validTimes, createTime, isUsed)" +
            "values (#{cdkey.cdkey}, #{cdkey.questionCategory}, #{cdkey.validTimes}, #{cdkey.createTime}, #{cdkey.isUsed})")
    void generateCDkey(@Param("cdkey") CDkey cdkey);

    @Select("select * from cdkey where cdkey = #{cdkey}")
    List<CDkey> checkCDkey(@Param("cdkey") String cdkey);

    @Update("update cdkey set isUsed = '1', userAccount = #{user.account}, userName = #{user.userName}, userPhoneNumber = #{user.phoneNumber}" +
            "where cdkey = #{cdkey.cdkey}")
    void expireCDkey(@Param("user") User user, @Param("cdkey") CDkey cdkey);
    */

}
