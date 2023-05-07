package com.tencent.wxcloudrun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Id;

@Data
@TableName("cdkey")
public class CDkey {

    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "cdkey")
    private String cdkey;

    @TableField(value = "questionCategory")
    private String questionCategory;

    @TableField(value = "validTimes")
    private int validTimes;

    @TableField(value = "createTime")
    private String createTime;

    @TableField(value = "isUsed")
    private boolean isUsed;

    @TableField(value = "userAccount")
    private String userAccount;

    @TableField(value = "userName")
    private String userName;

    @TableField(value = "userPhoneNumber")
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
