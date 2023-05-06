package com.tencent.wxcloudrun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.wxcloudrun.entity.CDkey;
import com.tencent.wxcloudrun.entity.File;
import com.tencent.wxcloudrun.entity.StudentRemainTimes;
import com.tencent.wxcloudrun.entity.User;
import com.tencent.wxcloudrun.mapper.StudentRemainTimesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


public interface StudentRemainTimesService  extends IService<StudentRemainTimes> {


    //增加学生
    public void updateStudentOriginalTimes(User user, CDkey cdkey);

    public void updateStudentSimulateTimes(User user, CDkey cdkey);
}
