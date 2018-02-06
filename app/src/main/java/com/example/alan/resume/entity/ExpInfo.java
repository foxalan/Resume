package com.example.alan.resume.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Function :
 * Modify Date : 2018/2/6
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */


@Entity
public class ExpInfo {

    @Id(autoincrement = true)
    private long id;
    private String startTime;
    private String endTime;
    private String company;
    private String job;
    private String workDes;

    @Generated(hash = 977480700)
    public ExpInfo(long id, String startTime, String endTime, String company,
            String job, String workDes) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.company = company;
        this.job = job;
        this.workDes = workDes;
    }

    @Generated(hash = 1692443358)
    public ExpInfo() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJob() {
        return this.job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getWorkDes() {
        return this.workDes;
    }

    public void setWorkDes(String workDes) {
        this.workDes = workDes;
    }


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private long id;
        private String startTime;
        private String endTime;
        private String company;
        private String job;
        private String workDes;

        public Builder setId(long mId) {
            this.id = mId;
            return this;
        }

        public Builder withStartTime(String startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder withEndTime(String endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder withCompany(String company) {
            this.company = company;
            return this;
        }

        public Builder withJob(String job) {
            this.job = job;
            return this;
        }

        public Builder withWorkDes(String workDes) {
            this.workDes = workDes;
            return this;
        }

        public ExpInfo build() {
            return new ExpInfo(id, startTime, endTime, company, job, workDes);
        }

    }


}
