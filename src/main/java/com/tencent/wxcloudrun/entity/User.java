package com.tencent.wxcloudrun.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "Users")
public class User {

    @Id
    private int userId;

    private String account;

    private String password;

    private String userName;

    private String phoneNumber;

    private String userCategory;

    private Date createTime;

    private Date updateTime;
}
