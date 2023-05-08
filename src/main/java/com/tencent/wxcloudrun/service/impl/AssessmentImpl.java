package com.tencent.wxcloudrun.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.entity.Assessment;
import com.tencent.wxcloudrun.mapper.AssessmentMapper;
import com.tencent.wxcloudrun.service.AssessmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AssessmentImpl extends ServiceImpl<AssessmentMapper, Assessment> implements AssessmentService {
    @Resource
    private AssessmentMapper assessmentMapper;

    @Override
    public boolean addAssessment(Assessment assessment) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String currentTime = formatter.format(new Date());
        assessment.setCreateTime(currentTime);
        assessment.setUpdateTime(currentTime);
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
    public boolean updateAssessment(int assessmentId, Assessment assessment) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String currentTime = formatter.format(new Date());
        assessment.setUpdateTime(currentTime);
        assessment.setAssessmentId(assessmentId);
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


    public ApiResponse checkAssessmentInfo(Assessment assessment){
        ApiResponse response = null;

        String year = assessment.getYear();
        String district = assessment.getDistrict();
        String isOriginal = assessment.getIsOriginal();
        String subject = assessment.getSubject();
        String category = assessment.getCategory();
        String title = assessment.getTitle();
        String content = assessment.getContent();

        if(year==null || "".equals(year)){
            response = ApiResponse.error("请填写考题年份");
        }else if(district==null || "".equals(district)){
            response = ApiResponse.error("请填写考题地区");
        }else if(isOriginal==null || "".equals(isOriginal)){
            response = ApiResponse.error("请填写考题是否为真题");
        }else if(subject==null || "".equals(subject)){
            response = ApiResponse.error("请填写考题所属学科");
        }else if(category==null || "".equals(category)){
            response = ApiResponse.error("请填写考题的题目类型");
        }else if(title==null || "".equals(title)){
            response = ApiResponse.error("请填写考题的标题");
        }else if(content==null || "".equals(content)){
            response = ApiResponse.error("请填写考题的题目内容");
        }else response = ApiResponse.ok();

        return response;
    }

}
