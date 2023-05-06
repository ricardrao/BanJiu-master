package com.tencent.wxcloudrun.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.entity.CDkey;
import com.tencent.wxcloudrun.entity.File;
import com.tencent.wxcloudrun.entity.StudentRemainTimes;
import com.tencent.wxcloudrun.entity.User;
import com.tencent.wxcloudrun.mapper.FileMapper;
import com.tencent.wxcloudrun.mapper.StudentRemainTimesMapper;
import com.tencent.wxcloudrun.service.FileService;
import com.tencent.wxcloudrun.service.StudentRemainTimesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class StudentRemainTimesServiceImpl extends ServiceImpl<StudentRemainTimesMapper, StudentRemainTimes> implements StudentRemainTimesService {
/*
    @Update("update StudentRemainTimes set originalQuestionTimes = originalQuestionTimes + #{cdkey.validTimes} " +
            "where studentPhoneNumber = #{user.phoneNumber}")
    void updateStudentOriginalTimes(@Param("user") User user, @Param("cdkey") CDkey cdkey);

    @Update("update StudentRemainTimes set simulateQuestionTimes = simulateQuestionTimes + #{cdkey.validTimes} " +
            "where studentPhoneNumber = #{user.phoneNumber}")
    void updateStudentSimulateTimes(@Param("user") User user, @Param("cdkey") CDkey cdkey);
    */

    @Resource
    private StudentRemainTimesMapper studentRemainTimesMapper;

    public void addStudent(User user){
        StudentRemainTimes studentRemainTimes = new StudentRemainTimes();
        studentRemainTimes.setStudentId(user.getUserId());
        studentRemainTimes.setStudentName(user.getUserName());
        studentRemainTimes.setStudentPhoneNumber(user.getPhoneNumber());
        this.save(studentRemainTimes);
    }

    public void updateStudentOriginalTimes(User user, CDkey cdkey){
        UpdateWrapper<StudentRemainTimes> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("studentPhoneNumber", user.getPhoneNumber());
        StudentRemainTimes studentRemainTimes = new StudentRemainTimes();
        studentRemainTimes.setOriginalQuestionTimes(studentRemainTimes.getOriginalQuestionTimes() + cdkey.getValidTimes());
        studentRemainTimesMapper.update(studentRemainTimes,updateWrapper);
    }

    public void updateStudentSimulateTimes(User user, CDkey cdkey) {
        UpdateWrapper<StudentRemainTimes> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("studentPhoneNumber", user.getPhoneNumber());
        StudentRemainTimes studentRemainTimes = new StudentRemainTimes();
        studentRemainTimes.setSimulateQuestionTimes(studentRemainTimes.getSimulateQuestionTimes() + cdkey.getValidTimes());
        studentRemainTimesMapper.update(studentRemainTimes,updateWrapper);

    }

}
