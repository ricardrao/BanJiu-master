package com.tencent.wxcloudrun.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@TableName("Urls")
public class Url {

    @Id
    @TableId(value = "urlId", type = IdType.AUTO)
    private int urlId;

    @TableField(value = "urlName")
    private String urlName;

    @TableField(value = "urlContent")
    private String urlContent;

    @TableField(value = "urlCategory")
    private String urlCategory;

    @TableField(value = "fileName")
    private String fileName;

    @TableField(value = "filePrefix")
    private String filePrefix;


    @TableField(value = "createTime")
    private String createTime;


    @TableField(value = "updateTime")
    private String updateTime;


    public Url(String urlName, String urlContent, String urlCategory,String fileName,String filePrefix, String createTime, String updateTime) {
        this.urlName = urlName;
        this.urlContent = urlContent;
        this.urlCategory = urlCategory;
        this.fileName = fileName;
        this.filePrefix = filePrefix;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Url() {
    }
}