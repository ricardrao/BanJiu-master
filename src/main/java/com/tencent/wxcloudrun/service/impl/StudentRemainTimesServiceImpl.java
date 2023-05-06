package com.tencent.wxcloudrun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.entity.File;
import com.tencent.wxcloudrun.entity.StudentRemainTimes;
import com.tencent.wxcloudrun.mapper.FileMapper;
import com.tencent.wxcloudrun.mapper.StudentRemainTimesMapper;
import com.tencent.wxcloudrun.service.FileService;
import com.tencent.wxcloudrun.service.StudentRemainTimesService;
import org.springframework.stereotype.Service;


@Service
public class StudentRemainTimesServiceImpl extends ServiceImpl<StudentRemainTimesMapper, StudentRemainTimes> implements StudentRemainTimesService {
}
