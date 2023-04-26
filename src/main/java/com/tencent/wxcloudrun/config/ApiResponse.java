package com.tencent.wxcloudrun.config;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.HashMap;

@Data
public final class ApiResponse {

  private Integer code;
  private String errorMsg;
  private JSONObject data;

  private ApiResponse(int code, String errorMsg, JSONObject data) {
    this.code = code;
    this.errorMsg = errorMsg;
    this.data = data;
  }
  
  public static ApiResponse ok() {
    return new ApiResponse(0, "", new JSONObject());
  }

  public static ApiResponse ok(JSONObject data) {
    return new ApiResponse(0, "", data);
  }

  public static ApiResponse error(String errorMsg) {
    return new ApiResponse(0, errorMsg, new JSONObject());
  }

}
