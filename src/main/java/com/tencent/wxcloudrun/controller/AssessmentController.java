package com.tencent.wxcloudrun.controller;

import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.entity.Assessment;
import com.tencent.wxcloudrun.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class AssessmentController {
    @Autowired
    private AssessmentService assessmentPromptService;
    @RequestMapping(value = "/getAssessment", method = RequestMethod.GET)
    public ApiResponse getEssayPrompt(@RequestParam(value = "assessmentId") Integer assessmentId) {
        Assessment assessment = assessmentPromptService.getAssessment(assessmentId);
        if (assessment != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("assessment", assessment);
            return ApiResponse.ok(jsonObject);
        }
        return ApiResponse.error("find a assessment failed", null);
    }
    @RequestMapping(value = "/getAllAssessments",method = RequestMethod.GET)
    public ApiResponse getAllEssayPrompts(@RequestParam(value = "current") Integer current, @RequestParam(value = "size") Integer size) {
        List<Assessment> assessments = assessmentPromptService.getAllAssessments(current, size);
        if (assessments != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("assessments", assessments);
            return ApiResponse.ok(jsonObject);
        }
        return ApiResponse.error("find assessments failed", null);
    }
    @RequestMapping(value = "/addAssessment",method = RequestMethod.POST)
    public ApiResponse addAssessment(@RequestParam(value = "assessment") Assessment assessment) {
        if (assessmentPromptService.addAssessment(assessment)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("assessment", assessment);
            return ApiResponse.ok(jsonObject);
        }
        return ApiResponse.error("add a assessment failed", null);
    }

    @RequestMapping(value = "/updateAssessment",method = RequestMethod.POST)
    public ApiResponse updateAssessment(@RequestParam(value = "assessmentId") Integer assessmentId, @RequestParam(value = "assessment") Assessment assessment) {
        if (assessmentPromptService.updateAssessment(assessmentId, assessment)) {
            return ApiResponse.ok();
        }
        return ApiResponse.error( "update a assessment failed", null);
    }

    @RequestMapping(value = "/deleteAssessment", method = RequestMethod.POST)
    public ApiResponse deleteAssessment(@RequestParam(value = "assessmentId") Integer assessmentId) {
        if (assessmentPromptService.deleteAssessment(assessmentId)) {
            return ApiResponse.ok();
        }
        return ApiResponse.error( "dalete a assessment failed", null);
    }
}
