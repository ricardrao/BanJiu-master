package com.tencent.wxcloudrun.controller;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT })
@Controller
public class FileUploadController {

    @Autowired
    private WechatFileUploader wechatFileUploader;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("fileInput") MultipartFile multipartFile) {
        if (!multipartFile.isEmpty()) {
            try {



                try {
                    String fileName = multipartFile.getOriginalFilename();
                    // 获取文件后缀
                    String prefix = fileName.substring(fileName.lastIndexOf("."));


                    File file = File.createTempFile(fileName, prefix);
                    multipartFile.transferTo(file);
                    System.out.println(file.length());
                    String accessToken = getAccessToken();
                    System.out.println(accessToken);
                    // 若需要防止生成的临时文件重复,可以在文件名后添加随机码
                    //String filename = UUID.randomUUID().toString();
                    String result = wechatFileUploader.upload(accessToken, fileName, file);

                    return "redirect:/index.html";
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return "redirect:/index.html";
    }

    private static final String APP_ID = "wxa7a8b87f8f413285";
    private static final String APP_SECRET = "ee76129ea65276fac28257163c189ef2";
    // 微信的 access_token 接口地址
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APP_ID + "&secret=" + APP_SECRET;

    /**
     * 获取微信的 access_token
     */
    public static String getAccessToken() {
        String accessToken = null;
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(ACCESS_TOKEN_URL);
            CloseableHttpResponse response = httpclient.execute(httpget);
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            JSONObject jsonObject = JSONObject.parseObject(result);
            accessToken = jsonObject.getString("access_token");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accessToken;
    }

}