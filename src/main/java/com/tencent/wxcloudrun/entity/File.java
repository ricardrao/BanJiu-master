package com.tencent.wxcloudrun.entity;



import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "Files")
public class File implements Serializable {

    @Id
    private int fileId;

    private String fileName;

    private String filePrefix;


    private String createTime;


    private String updateTime;


    public File(String fileName,String filePrefix, String createTime, String updateTime) {
        this.fileName = fileName;
        this.filePrefix = filePrefix;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public File() {
    }
}