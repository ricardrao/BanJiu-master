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


    public User(String account, String password, String userName, String phoneNumber, String userCategory, String createTime, String updateTime) {
        this.account = account;
        this.password = password;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.userCategory = userCategory;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public User() {
    }
}
