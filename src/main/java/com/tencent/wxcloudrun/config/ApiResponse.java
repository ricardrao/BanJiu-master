package com.tencent.wxcloudrun.config;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.HashMap;

@Data
public final class ApiResponse {

  private Integer code;
  private String message;
  private JSONObject data;

  private ApiResponse(int code, String message, JSONObject data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }
  
  public static ApiResponse ok() {
    return new ApiResponse(200, "success", new JSONObject());
  }

  public static ApiResponse ok(JSONObject data) {
    return new ApiResponse(200, "success", data);
  }

  public static ApiResponse error(String errorMsg) {
    return new ApiResponse(400, errorMsg, new JSONObject());
  }

  public static ApiResponse error(String errorMsg, JSONObject data) {
    return new ApiResponse(400, errorMsg, data);
  }

}
