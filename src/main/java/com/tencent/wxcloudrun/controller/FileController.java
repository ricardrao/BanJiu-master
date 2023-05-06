package com.tencent.wxcloudrun.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.service.FileService;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT })
@Controller
public class FileController {

    @Autowired
    private WechatFileUploader wechatFileUploader;

    @Autowired
    private FileService fileService;


    @RequestMapping(value = "/getFile",method = RequestMethod.GET)
    public ApiResponse getFile(@RequestParam(value = "fileName") String fileName, @RequestParam(value = "filePrefix") String filePrefix) {



        return ApiResponse.ok();
    }

    @PostMapping(value = "/upload")
    public ApiResponse uploadFile(@RequestParam("fileInput") MultipartFile multipartFile) {
        if (!multipartFile.isEmpty()) {
            try {

                try {
                    String fileName = multipartFile.getOriginalFilename();
                    // 获取文件后缀
                    String prefix = fileName.substring(fileName.lastIndexOf("."));


                    File file = File.createTempFile(fileName, prefix);
                    multipartFile.transferTo(file);

                    String accessToken = getAccessToken();

                    // 若需要防止生成的临时文件重复,可以在文件名后添加随机码
                    fileName = UUID.randomUUID().toString();
                    System.out.println(accessToken);
                    String result = wechatFileUploader.upload(accessToken, fileName, file);

                    //将数据插入Files表
                    JSONObject fileObject = new JSONObject();
                    fileObject.put("fileName", fileName);
                    fileObject.put("filePrefix", prefix);

                    String urlName = String.valueOf(fileObject.get("fileName"));
                    String urlPrefix = String.valueOf(fileObject.get("filePrefix"));

                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    com.tencent.wxcloudrun.entity.File file1 = new com.tencent.wxcloudrun.entity.File(urlName,urlPrefix,formatter.format(new Date()), formatter.format(new Date()));
                    fileService.save(file1);

                    return ApiResponse.ok();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return ApiResponse.error("please upload file!!");
    }

    private static final String APP_ID = "wxa7a8b87f8f413285";
    private static final String APP_SECRET = "ee76129ea65276fac28257163c189ef2";
//    private static final String APP_ID = System.getenv("APP_ID");
//    private static final String APP_SECRET = System.getenv("APP_SECRET");
    // 微信的 access_token 接口地址
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APP_ID + "&secret=" + APP_SECRET;


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