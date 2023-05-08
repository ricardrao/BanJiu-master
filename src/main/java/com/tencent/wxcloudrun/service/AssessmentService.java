package com.tencent.wxcloudrun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.wxcloudrun.entity.Assessment;

import java.util.List;

public interface AssessmentService  extends IService<Assessment> {
    public boolean addAssessment(Assessment assessment);
    public boolean deleteAssessment(int essayId);
    public boolean updateAssessment(int assessmentId, Assessment assessment);
    List<Assessment> getAllAssessments(int current, int size);
    Assessment getAssessment(int assessmentId);
}
