package com.tencent.wxcloudrun.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Data
@TableName("Users")
public class User {

    @Id
    @TableId(value = "userId", type = IdType.AUTO)
    private int userId;


    @TableField(value = "account")
    private String account;


    @TableField(value = "password")
    private String password;


    @TableField(value = "userName")
    private String userName;


    @TableField(value = "phoneNumber")
    private String phoneNumber;


    @TableField(value = "userCategory")
    private String userCategory;


    @TableField(value = "createTime")
    private String createTime;


    @TableField(value = "updateTime")
    private String updateTime;


    @TableField(value = "homeworkCorrectionTimes")
    private int homeworkCorrectionTimes;


    @TableField(value = "targetYear")
    private int targetYear;

    @TableField(value = "targetSchool")
    private String targetSchool;

    @TableField(value = "targetMajor")
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
