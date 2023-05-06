package com.tencent.wxcloudrun.entity;



import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@TableName("Files")
public class File implements Serializable {

    @Id
    @TableId(value = "fileId", type = IdType.AUTO)
    private int fileId;

    @TableField(value = "fileName")
    private String fileName;

    @TableField(value = "filePrefix")
    private String filePrefix;

    @TableField(value = "createTime", fill = FieldFill.INSERT)
    private String createTime;

    @TableField(value = "updateTime", fill = FieldFill.INSERT_UPDATE)
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