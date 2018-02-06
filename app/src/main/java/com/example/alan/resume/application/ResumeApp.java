package com.example.alan.resume.application;

import android.app.Application;

import com.example.alan.resume.database.DatabaseManager;
import com.example.alan.resume.entity.EduInfo;
import com.example.alan.resume.entity.ExpInfo;
import com.example.alan.resume.entity.ProInfo;
import com.example.alan.resume.entity.UserInfo;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Function :
 * Modify Date : 2018/2/1
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class ResumeApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化数据库
        DatabaseManager.getInstance().init(this);
        UserInfo userInfo = UserInfo.builder()

                .setName("alan")
                .setAge(23)
                .setExperience("2")
                .setLocation("wuhan")
                .setPhone("18202710074")
                .build();
        UserInfo userInfo2 = UserInfo.builder()
                .setId(10)
                .setName("alan2")
                .setAge(234)
                .setExperience("12")
                .setLocation("wuhan1")
                .setPhone("182027100741")
                .build();
        DatabaseManager.getInstance().getUseInfoDao().insert(userInfo);

        DatabaseManager.getInstance().getUseInfoDao().insert(userInfo2);

        ProInfo projectInfo = ProInfo.builder()
                .setId(1)
                .setStartTime("2017-06-11")
                .setEndTime("2017-09-11")
                .setTitle("WLY")
                .setContext("wly create")
                .build();
        DatabaseManager.getInstance().getProjectInfoDao().insert(projectInfo);

        EduInfo educateInfo = EduInfo.builder()
                .setId(1)
                .setStartTime("2012-09")
                .setEndTime("2016-07")
                .setSchool("湖北大学")
                .setSchoolType("本科")
                .setPro("XIN")
                .build();

        DatabaseManager.getInstance().getEducateInfoDao().insert(educateInfo);
        ExpInfo info = ExpInfo.builder()
                .setId(3)
                .withStartTime("2016-7")
                .withEndTime("2018-1")
                .withCompany("fxc")
                .withWorkDes("coder")
                .withWorkDes("just code maker")
                .build();
        DatabaseManager.getInstance().getExpDao().insert(info);
        Resume.init(this);

        //设置字体
        Iconify.with(new FontAwesomeModule());
    }
}
