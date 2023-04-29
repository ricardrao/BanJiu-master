package com.tencent.wxcloudrun.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "Urls")
public class Url {

    @Id
    private int urlId;

    private String urlName;

    private String urlCategory;


    private String urlImage;

    private String createTime;

    private String updateTime;


    public Url(String urlName, String urlCategory, String urlImage, String createTime, String updateTime) {
        this.urlName = urlName;
        this.urlCategory = urlCategory;
        this.urlImage = urlImage;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Url() {
    }
}