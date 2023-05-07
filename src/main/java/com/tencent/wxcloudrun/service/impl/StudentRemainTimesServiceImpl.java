package com.tencent.wxcloudrun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.entity.CDkey;
import com.tencent.wxcloudrun.entity.File;
import com.tencent.wxcloudrun.entity.StudentRemainTimes;
import com.tencent.wxcloudrun.entity.User;
import com.tencent.wxcloudrun.mapper.FileMapper;
import com.tencent.wxcloudrun.mapper.StudentRemainTimesMapper;
import com.tencent.wxcloudrun.mapper.UserMapper;
import com.tencent.wxcloudrun.service.FileService;
import com.tencent.wxcloudrun.service.StudentRemainTimesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


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
        //查询出已有学生
        QueryWrapper<StudentRemainTimes> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(StudentRemainTimes::getStudentId, user.getUserId());
        List<StudentRemainTimes> studentList = studentRemainTimesMapper.selectList(queryWrapper);
        StudentRemainTimes curStudent = studentList.get(0);
        //更新真题批改次数
        UpdateWrapper<StudentRemainTimes> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(StudentRemainTimes::getStudentPhoneNumber, user.getPhoneNumber());
        updateWrapper.lambda().set(StudentRemainTimes::getOriginalQuestionTimes, curStudent.getOriginalQuestionTimes() + cdkey.getValidTimes());
        studentRemainTimesMapper.update(null, updateWrapper);
    }

    public void updateStudentSimulateTimes(User user, CDkey cdkey) {
        //查询出已有学生
        QueryWrapper<StudentRemainTimes> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(StudentRemainTimes::getStudentId, user.getUserId());
        List<StudentRemainTimes> studentList = studentRemainTimesMapper.selectList(queryWrapper);
        StudentRemainTimes curStudent = studentList.get(0);
        //更新模拟题批改次数
        UpdateWrapper<StudentRemainTimes> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("studentPhoneNumber", user.getPhoneNumber());
        updateWrapper.lambda().set(StudentRemainTimes::getSimulateQuestionTimes, curStudent.getOriginalQuestionTimes() + cdkey.getValidTimes());
        studentRemainTimesMapper.update(null, updateWrapper);

    }

}
