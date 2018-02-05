package com.example.alan.resume.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

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
    @Generated(hash = 1692653798)
    public EducateInfo(long mId, String mStartTime, String mEndTime, String mSchool,
            String mSchoolType, String mPro) {
        this.mId = mId;
        this.mStartTime = mStartTime;
        this.mEndTime = mEndTime;
        this.mSchool = mSchool;
        this.mSchoolType = mSchoolType;
        this.mPro = mPro;
    }
    @Generated(hash = 1136590377)
    public EducateInfo() {
    }
    public long getMId() {
        return this.mId;
    }
    public void setMId(long mId) {
        this.mId = mId;
    }
    public String getMStartTime() {
        return this.mStartTime;
    }
    public void setMStartTime(String mStartTime) {
        this.mStartTime = mStartTime;
    }
    public String getMEndTime() {
        return this.mEndTime;
    }
    public void setMEndTime(String mEndTime) {
        this.mEndTime = mEndTime;
    }
    public String getMSchool() {
        return this.mSchool;
    }
    public void setMSchool(String mSchool) {
        this.mSchool = mSchool;
    }
    public String getMSchoolType() {
        return this.mSchoolType;
    }
    public void setMSchoolType(String mSchoolType) {
        this.mSchoolType = mSchoolType;
    }
    public String getMPro() {
        return this.mPro;
    }
    public void setMPro(String mPro) {
        this.mPro = mPro;
    }


}
