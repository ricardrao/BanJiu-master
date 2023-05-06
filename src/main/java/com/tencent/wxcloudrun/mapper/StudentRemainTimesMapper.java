package com.tencent.wxcloudrun.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.wxcloudrun.entity.CDkey;
import com.tencent.wxcloudrun.entity.StudentRemainTimes;
import com.tencent.wxcloudrun.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StudentRemainTimesMapper extends BaseMapper<StudentRemainTimes> {

    /*
    @Insert("insert into StudentRemainTimes (studentId, studentName, studentPhoneNumber, originalQuestionTimes, simulateQuestionTimes)" +
            "values (#{user.userId}, #{user.userName}, #{user.phoneNumber}, 0, 0)")
    void addStudent(@Param("user") User user);

    @Update("update StudentRemainTimes set originalQuestionTimes = originalQuestionTimes + #{cdkey.validTimes} " +
            "where studentPhoneNumber = #{user.phoneNumber}")
    void updateStudentOriginalTimes(@Param("user") User user, @Param("cdkey") CDkey cdkey);

    @Update("update StudentRemainTimes set simulateQuestionTimes = simulateQuestionTimes + #{cdkey.validTimes} " +
            "where studentPhoneNumber = #{user.phoneNumber}")
    void updateStudentSimulateTimes(@Param("user") User user, @Param("cdkey") CDkey cdkey);
    */

}
