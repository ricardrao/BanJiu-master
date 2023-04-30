package com.tencent.wxcloudrun.controller;


import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.config.UrlCategory;
import com.tencent.wxcloudrun.entity.Url;
import com.tencent.wxcloudrun.entity.User;
import com.tencent.wxcloudrun.service.UrlService;
import com.tencent.wxcloudrun.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UrlController {

    @Autowired
    private UrlService urlService;

    private HashSet<String> urlCategorySet = new HashSet<String>(){{
        add(UrlCategory.CORRECT.name());
        add(UrlCategory.TEACH.name());
    }};


    private static Logger logger = LoggerFactory.getLogger(UrlController.class);

    @RequestMapping("/getUrlAmount")
    public ApiResponse getUrlAmount(){
        int urlAmount = urlService.getUrlAmount();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("urlAmount", urlAmount);
        logger.info("\"/getUrlAmount\" Api Response = " + jsonObject.toJSONString());
        return ApiResponse.ok(jsonObject);
    }

    @RequestMapping("/getUrlInfo")
    public ApiResponse getUrlInfo(@RequestParam(value = "urlName") String urlName){
        if(urlName==null || "".equals(urlName)){
            return ApiResponse.error("please input urlName");
        }
        List<Url> urlList = urlService.getUrl(urlName);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url", urlList);
        return ApiResponse.ok(jsonObject);
    }

    //调用示例 /addUrl?urlInfo={"urlName":"https://...","urlCategory":"TEACH","urlImage":"binary data"}
    @RequestMapping("/addUrl")
    public ApiResponse addUrl(@RequestParam(value = "urlInfo") String urlInfo){
        if(urlInfo==null || "".equals(urlInfo)){
            return ApiResponse.error("no url message!");
        }
        JSONObject urlObject = JSONObject.parseObject(urlInfo);
        String urlName = String.valueOf(urlObject.get("urlName"));
        String urlCategory = String.valueOf(urlObject.get("urlCategory"));
        String urlImage = String.valueOf(urlObject.get("urlImage"));


        if(urlName==null || "".equals(urlName)){
            return ApiResponse.error("illegal urlName!");
        }
        if(urlCategory==null || "".equals(urlCategory)){
            return ApiResponse.error("illegal urlCategory!");
        }
        if(urlImage==null || "".equals(urlImage)){
            return ApiResponse.error("illegal urlImage!");
        }


        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Url url = new Url(urlName, urlCategory, urlImage, formatter.format(new Date()), formatter.format(new Date()));

        urlService.addUrl(url);
        return ApiResponse.ok();
    }




}











