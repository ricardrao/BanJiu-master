package com.tencent.wxcloudrun.entity;

import lombok.Data;

import javax.persistence.Table;
import javax.persistence.Id;

@Data
@Table(name = "StudentRemainTimes")
public class StudentRemainTimes {

    @Id
    private int id;
    private int studentId;
    private String studentName;
    private String studentPhoneNumber;
    private int originalQuestionTimes;
    private int simulateQuestionTimes;



}
