package com.example.alan.resume.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Function :
 * Modify Date : 2018/2/2
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

@Entity
public class ProInfo {


    @Id(autoincrement = true)
    private long mId;
    private String mStartTime;
    private String mEndTime;
    private String mTitle;
    private String mContext;

    @Generated(hash = 419823608)
    public ProInfo(long mId, String mStartTime, String mEndTime, String mTitle,
            String mContext) {
        this.mId = mId;
        this.mStartTime = mStartTime;
        this.mEndTime = mEndTime;
        this.mTitle = mTitle;
        this.mContext = mContext;
    }

    @Generated(hash = 1765891849)
    public ProInfo() {
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

    public String getMTitle() {
        return this.mTitle;
    }

    public void setMTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getMContext() {
        return this.mContext;
    }

    public void setMContext(String mContext) {
        this.mContext = mContext;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {

        private long mId;
        private String mStartTime;
        private String mEndTime;
        private String mTitle;
        private String mContext;

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

        public Builder setTitle(String mTitle) {
            this.mTitle = mTitle;
            return this;
        }

        public Builder setContext(String mContext) {
            this.mContext = mContext;
            return this;
        }

        public ProInfo build() {
            return new ProInfo(mId, mStartTime, mEndTime, mTitle, mContext);
        }

    }


}
