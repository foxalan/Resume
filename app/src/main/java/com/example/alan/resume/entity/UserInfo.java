package com.example.alan.resume.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Function :
 * Modify Date : 2018/2/1
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

@Entity
public class UserInfo {

    private long mId;
    private String mPicPath;
    private String mName;
    private int mAge;
    private String mPhone;
    private String mExperience;
    private String mLocation;

    @Generated(hash = 1362013739)
    public UserInfo(long mId, String mPicPath, String mName, int mAge,
            String mPhone, String mExperience, String mLocation) {
        this.mId = mId;
        this.mPicPath = mPicPath;
        this.mName = mName;
        this.mAge = mAge;
        this.mPhone = mPhone;
        this.mExperience = mExperience;
        this.mLocation = mLocation;
    }
    @Generated(hash = 1279772520)
    public UserInfo() {
    }
    public long getMId() {
        return this.mId;
    }
    public void setMId(long mId) {
        this.mId = mId;
    }
    public String getMPicPath() {
        return this.mPicPath;
    }
    public void setMPicPath(String mPicPath) {
        this.mPicPath = mPicPath;
    }
    public String getMName() {
        return this.mName;
    }
    public void setMName(String mName) {
        this.mName = mName;
    }
    public int getMAge() {
        return this.mAge;
    }
    public void setMAge(int mAge) {
        this.mAge = mAge;
    }
    public String getMPhone() {
        return this.mPhone;
    }
    public void setMPhone(String mPhone) {
        this.mPhone = mPhone;
    }
    public String getMExperience() {
        return this.mExperience;
    }
    public void setMExperience(String mExperience) {
        this.mExperience = mExperience;
    }
    public String getMLocation() {
        return this.mLocation;
    }
    public void setMLocation(String mLocation) {
        this.mLocation = mLocation;
    }

}