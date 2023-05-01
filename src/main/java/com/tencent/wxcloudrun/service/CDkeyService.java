package com.tencent.wxcloudrun.service;


import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.config.QuestionCategory;
import com.tencent.wxcloudrun.config.UserCategory;
import com.tencent.wxcloudrun.entity.CDkey;
import com.tencent.wxcloudrun.entity.User;
import com.tencent.wxcloudrun.mapper.CDkeyMapper;
import com.tencent.wxcloudrun.mapper.StudentRemainTimesMapper;
import com.tencent.wxcloudrun.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CDkeyService {

    @Resource
    private CDkeyMapper cdkeyMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private StudentRemainTimesMapper studentRemainTimesMapper;

    public void generateCDkey(CDkey cdkey){
        cdkeyMapper.generateCDkey(cdkey);
    }

    public CDkey checkCDkey(String cdkey){
        List<CDkey> cdkeyList = cdkeyMapper.checkCDkey(cdkey);
        if(cdkeyList==null || cdkeyList.size()==0){
            return null;
        }
        CDkey curKey = cdkeyList.get(0);
        if(curKey.isUsed() == true){
            return null;
        }
        return curKey;
    }

    @Transactional
    public ApiResponse userConsumeCDkey(String userPhoneNumber, CDkey cdkey){
        User user = userMapper.getUserByPhoneNumber(userPhoneNumber).get(0);
        if(!UserCategory.STUDENT.name().equalsIgnoreCase(user.getUserCategory())){
            return ApiResponse.error("illegal user category");
        }
        //cdkey设置失效->user表增加times->studentRemainTimes表新增Times
        cdkeyMapper.expireCDkey(user, cdkey);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        userMapper.updateUserValidTimes(user, cdkey.getValidTimes(), formatter.format(new Date()));
        String keyQuestionCategory = cdkey.getQuestionCategory();
        if(keyQuestionCategory.equals(QuestionCategory.original.name())){
            studentRemainTimesMapper.updateStudentOriginalTimes(user, cdkey);
        } else {
            studentRemainTimesMapper.updateStudentSimulateTimes(user, cdkey);
        }
        User curUser = userMapper.getUserByPhoneNumber(user.getPhoneNumber()).get(0);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user", curUser);
        return ApiResponse.ok(jsonObject);
    }

}
