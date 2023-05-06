package com.tencent.wxcloudrun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Table;
import javax.persistence.Id;

@Data

@TableName("StudentRemainTimes")
public class StudentRemainTimes {

    @Id
    private int id;

    @TableField(value = "studentId")
    private int studentId;

    @TableField(value = "studentName")
    private String studentName;

    @TableField(value = "studentPhoneNumber")
    private String studentPhoneNumber;

    @TableField(value = "originalQuestionTimes")
    private int originalQuestionTimes;

    @TableField(value = "simulateQuestionTimes")
    private int simulateQuestionTimes;



}
