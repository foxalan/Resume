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
public class EduInfo {

    @Id(autoincrement = true)
    private long mId;
    private String mStartTime;
    private String mEndTime;
    private String mSchool;
    private String mSchoolType;
    private String mPro;

    @Generated(hash = 1898416164)
    public EduInfo(long mId, String mStartTime, String mEndTime, String mSchool,
            String mSchoolType, String mPro) {
        this.mId = mId;
        this.mStartTime = mStartTime;
        this.mEndTime = mEndTime;
        this.mSchool = mSchool;
        this.mSchoolType = mSchoolType;
        this.mPro = mPro;
    }
    @Generated(hash = 396650992)
    public EduInfo() {
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


    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {

        private long mId;
        private String mStartTime;
        private String mEndTime;
        private String mSchool;
        private String mSchoolType;
        private String mPro;

        public Builder setId(long mId) {
            this.mId = mId;
            return this;
        }

        public Builder setStartTime(String mStartTime) {
            this.mStartTime = mStartTime;
            return this;
        }

        public Builder setEndTime(String mEndTime) {
            this.mEndTime = mEndTime;
            return this;
        }

        public Builder setSchool(String mSchool) {
            this.mSchool = mSchool;
            return this;
        }

        public Builder setSchoolType(String mSchoolType) {
            this.mSchoolType = mSchoolType;
            return this;
        }

        public Builder setPro(String mPro) {
            this.mPro = mPro;
            return this;
        }


        public EduInfo build() {
            return new EduInfo(mId, mStartTime, mEndTime, mSchool, mSchoolType,mPro);
        }

    }

    @Override
    public String toString() {
        return "EduInfo{" +
                "mId=" + mId +
                ", mStartTime='" + mStartTime + '\'' +
                ", mEndTime='" + mEndTime + '\'' +
                ", mSchool='" + mSchool + '\'' +
                ", mSchoolType='" + mSchoolType + '\'' +
                ", mPro='" + mPro + '\'' +
                '}';
    }
}
