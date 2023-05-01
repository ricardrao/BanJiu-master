package com.tencent.wxcloudrun.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class WechatFileUploader {

    public String upload(String accessToken, String filename, File file) {
        System.out.println(file.length());
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/tcb/uploadfile?access_token=" + accessToken);
        String url = "https://api.weixin.qq.com/tcb/uploadfile" + "?access_token=" + accessToken;
        String jsonStr = "{\"env\":\"" + "prod-6g45dsyn5bf0c43b" + "\",\"path\":\"" + "test"   + "\"}";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject(url, jsonStr, String.class);
        System.out.println(result);
        JSONObject jsonObject = JSON.parseObject(result);
        String urlFileId = jsonObject.getString("file_id");
        String urlUpload = jsonObject.getString("url");
        String tokenUpload = jsonObject.getString("token");
        String tokenAuthorization = jsonObject.getString("authorization");
        String tokenCos_file_id = jsonObject.getString("cos_file_id");



        RestTemplate restTemplate2 = new RestTemplate();
        System.out.println(filename);
        // 上传文件
        MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("key", "test"  ); // 文件在云存储中的路径
        bodyMap.add("Signature", tokenAuthorization); // 上传所需的签名信息
        bodyMap.add("x-cos-security-token", tokenUpload); // 上传所需的 token
        bodyMap.add("x-cos-meta-fileid", tokenCos_file_id); // 上传所需的 token
        bodyMap.add("file", file); // 上传的文件
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(bodyMap, headers);
        ResponseEntity<String> responseEntity = restTemplate2.exchange(urlUpload, HttpMethod.POST, entity, String.class);


        return null;
    }
}