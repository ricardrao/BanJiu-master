package com.tencent.wxcloudrun.config;

public enum QuestionCategory {


    original("original"),
    simulate("simulate");


    private String value;

    QuestionCategory(String value) {
        this.value = value;
    }
}
