package com.example.alan.resume.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

/**
 * Function :
 * Modify Date : 2018/2/3
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

@Entity
public class EducateInfo {

    @Id(autoincrement = true)
    private long mId;
    private String mStartTime;
    private String mEndTime;
    private String mSchool;
    private String mSchoolType;
    private String mPro;


}
