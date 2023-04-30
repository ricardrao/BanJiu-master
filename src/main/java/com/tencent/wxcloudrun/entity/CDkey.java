package com.tencent.wxcloudrun.entity;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class CDkey {

    @Id
    private int id;
    private String cdkey;
    private String questionCategory;
    private int validTimes;
    private String createTime;
    private boolean isUsed;
    private String userAccount;
    private String userName;
    private String userPhoneNumber;


    @Override
    public String toString() {
        return "CDkey{" +
                "id=" + id +
                ", cdkey='" + cdkey + '\'' +
                ", questionCategory='" + questionCategory + '\'' +
                ", validTimes=" + validTimes +
                ", createTime='" + createTime + '\'' +
                ", isUsed=" + isUsed +
                ", userAccount='" + userAccount + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhoneNumber='" + userPhoneNumber + '\'' +
                '}';
    }
}
