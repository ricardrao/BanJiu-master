package com.tencent.wxcloudrun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
@Data
@TableName("Assessment")
public class Assessment {
    @Id
    @TableId(value = "assessmentId", type = IdType.AUTO)
    private Integer AssessmentId;

    @TableField(value = "title")
    private String title;

    @TableField(value = "content")
    private String content;

    @TableField(value = "district")
    private String district;

    @TableField(value = "year")
    private String year;

    @TableField(value = "isOriginal")
    private String isOriginal;

    @TableField(value = "subject")
    private String subject;

    @TableField(value = "category")
    private String category;

    @TableField(value = "createTime")
    private String createTime;

    @TableField(value = "updateTime")
    private String updateTime;

    public Assessment(String title, String content, String district, String year, String isOriginal,
                      String subject, String category, String createTime, String updateTime) {
        this.title = title;
        this.content = content;
        this.district = district;
        this.year = year;
        this.isOriginal = isOriginal;
        this.subject = subject;
        this.category = category;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Assessment() {
    }
}
