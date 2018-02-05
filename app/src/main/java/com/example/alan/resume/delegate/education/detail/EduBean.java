package com.example.alan.resume.delegate.education.detail;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Function :
 * Modify Date : 2018/2/5
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class EduBean implements MultiItemEntity {

    private int mId;
    private String mTitle;
    private String mContext;
    private int mItemType;

    public void setmId(int mId) {
        this.mId = mId;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmContext(String mContext) {
        this.mContext = mContext;
    }

    public void setmItemType(int mItemType) {
        this.mItemType = mItemType;
    }

    public int getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmContext() {
        return mContext;
    }

    public int getmItemType() {
        return mItemType;
    }

    public EduBean(int mId, String mTitle, String mContext, int mItemType) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mContext = mContext;
        this.mItemType = mItemType;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public int getItemType() {
        return mItemType;
    }

    public static class Builder {

        private int mId;
        private String mTitle;
        private String mContext;
        private int mItemType;

        public Builder setItemType(int ItemType){
            this.mItemType = ItemType;
            return this;
        }

        public Builder withId(int id) {
            this.mId = id;
            return this;
        }

        public Builder withTitle(String title) {
            this.mTitle = title;
            return this;
        }

        public Builder withContext(String context) {
            this.mContext = context;
            return this;
        }



        public EduBean build() {
            return new EduBean(mId, mTitle, mContext,mItemType);
        }
    }
}
