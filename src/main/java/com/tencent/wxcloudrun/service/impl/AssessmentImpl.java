package com.tencent.wxcloudrun.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.entity.Assessment;
import com.tencent.wxcloudrun.mapper.AssessmentMapper;
import com.tencent.wxcloudrun.service.AssessmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AssessmentImpl extends ServiceImpl<AssessmentMapper, Assessment> implements AssessmentService {
    @Resource
    private AssessmentMapper assessmentMapper;

    @Override
    public boolean addAssessment(Assessment assessment) {
        return this.saveOrUpdate(assessment);
    }

    @Override
    public boolean deleteAssessment(int essayId) {
        if(assessmentMapper.deleteById(essayId) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateAssessment(Assessment assessment) {
        if (assessmentMapper.updateById(assessment) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Assessment> getAllAssessments(int current, int size) {
        QueryWrapper<Assessment> queryWrapper = new QueryWrapper<>();
        Page<Assessment> page = new Page<>(current, size);
        page = assessmentMapper.selectPage(page, queryWrapper);
        List<Assessment> assessments = page.getRecords();
        return assessments;
    }

    public Assessment getAssessment(int essayPromptId) {
        Assessment assessment = assessmentMapper.selectById(essayPromptId);
        return assessment;
    }
}
