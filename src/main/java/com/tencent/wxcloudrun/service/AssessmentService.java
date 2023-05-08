package com.tencent.wxcloudrun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.entity.Assessment;

import java.util.List;

public interface AssessmentService  extends IService<Assessment> {
    boolean addAssessment(Assessment assessment);
    boolean deleteAssessment(int essayId);
    boolean updateAssessment(int assessmentId, Assessment assessment);
    List<Assessment> getAllAssessments(int current, int size);
    Assessment getAssessment(int assessmentId);
    ApiResponse checkAssessmentInfo(Assessment assessment);
}
