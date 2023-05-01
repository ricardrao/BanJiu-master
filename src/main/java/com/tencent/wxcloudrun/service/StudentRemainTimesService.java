package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.entity.User;
import com.tencent.wxcloudrun.mapper.StudentRemainTimesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentRemainTimesService {

    @Resource
    private StudentRemainTimesMapper studentRemainTimesMapper;

    public void addStudent(User user){
        studentRemainTimesMapper.addStudent(user);
    }
}
