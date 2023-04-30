package com.tencent.wxcloudrun.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Data
@Table(name = "Users")
public class User {

    @Id
    private int userId;

    private String account;

    private String password;

    private String userName;

    private String phoneNumber;

    private String userCategory;

    private String createTime;

    private String updateTime;

    private int homeworkCorrectionTimes;

    private int targetYear;

    private String targetSchool;

    private String targetMajor;

    public User(String account, String password, String userName, String phoneNumber, String userCategory, String createTime,
                String updateTime, int homeworkCorrectionTimes, int targetYear, String targetMajor, String targetSchool) {
        this.account = account;
        this.password = password;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.userCategory = userCategory;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.homeworkCorrectionTimes = homeworkCorrectionTimes;
        this.targetYear = targetYear;
        this.targetSchool = targetSchool;
        this.targetMajor = targetMajor;
    }

    public User() {
    }
}
