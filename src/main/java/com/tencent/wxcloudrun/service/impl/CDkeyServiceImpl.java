package com.tencent.wxcloudrun.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.config.QuestionCategory;
import com.tencent.wxcloudrun.config.UserCategory;
import com.tencent.wxcloudrun.entity.CDkey;
import com.tencent.wxcloudrun.entity.File;
import com.tencent.wxcloudrun.entity.Url;
import com.tencent.wxcloudrun.entity.User;
import com.tencent.wxcloudrun.mapper.CDkeyMapper;
import com.tencent.wxcloudrun.mapper.FileMapper;
import com.tencent.wxcloudrun.mapper.StudentRemainTimesMapper;
import com.tencent.wxcloudrun.mapper.UserMapper;
import com.tencent.wxcloudrun.service.CDkeyService;
import com.tencent.wxcloudrun.service.FileService;
import com.tencent.wxcloudrun.service.StudentRemainTimesService;
import com.tencent.wxcloudrun.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CDkeyServiceImpl extends ServiceImpl<CDkeyMapper, CDkey> implements CDkeyService {
    /*





     */

    @Resource
    private CDkeyMapper cdkeyMapper;

    @Resource
    private UserService userService;

    @Resource
    private StudentRemainTimesService studentRemainTimesService;


    public void generateCDkey(CDkey cdkey){
        this.save(cdkey);
    }

    public CDkey checkCDkey(String cdkey){
        QueryWrapper<CDkey> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cdkey", cdkey);

        List<CDkey> cdkeyList = cdkeyMapper.selectList(queryWrapper);

        if(cdkeyList==null || cdkeyList.size()==0){
            return null;
        }
        CDkey curKey = cdkeyList.get(0);
        if(curKey.isUsed() == true){
            return null;
        }
        return curKey;
    }

    public void expireCDkey(User user, CDkey cdkey){
        cdkey.setUsed(true);
        cdkeyMapper.updateById(cdkey);
    }


    //消费CDkey
    //cdkey设置失效->user表增加times->studentRemainTimes表新增Times
    @Transactional
    public ApiResponse userConsumeCDkey(String userPhoneNumber, CDkey cdkey){
        User user = userService.getUserByPhoneNumber(userPhoneNumber).get(0);
        if(!UserCategory.STUDENT.name().equalsIgnoreCase(user.getUserCategory())){
            return ApiResponse.error("illegal user category");
        }

        this.expireCDkey(user, cdkey);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        userService.updateUserValidTimes(user, cdkey.getValidTimes(), formatter.format(new Date()));
        String keyQuestionCategory = cdkey.getQuestionCategory();
        if(keyQuestionCategory.equals(QuestionCategory.original.name())){
            studentRemainTimesService.updateStudentOriginalTimes(user, cdkey);
        } else {
            studentRemainTimesService.updateStudentSimulateTimes(user, cdkey);
        }
        User curUser = userService.getUserByPhoneNumber(user.getPhoneNumber()).get(0);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user", curUser);
        return ApiResponse.ok(jsonObject);
    }


}
