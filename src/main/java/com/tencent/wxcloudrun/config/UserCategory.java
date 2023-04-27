package com.tencent.wxcloudrun.config;

public enum UserCategory {

    STUDENT("student"),
    TEACHER("teacher"),
    ADMINISTRATOR("administrator");

    private String value;

    UserCategory(String value) {
        this.value = value;
    }
}
