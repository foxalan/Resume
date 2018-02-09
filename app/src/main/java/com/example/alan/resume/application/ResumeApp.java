package com.example.alan.resume.application;

import android.app.Application;

import com.example.alan.resume.database.DatabaseManager;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Function :
 * Modify Date : 2018/2/1
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
//        UserInfo userInfo = UserInfo.builder()
//
//                .setName("莫莫伽")
//                .setAge(23)
//                .setExperience("2")
//                .setLocation("武汉")
//                .setPhone("18202710074")
//                .build();
//        DatabaseManager.getInstance().getUseInfoDao().insert(userInfo);
//
//        ProInfo projectInfo = ProInfo.builder()
//                .setId(1)
//                .setStartTime("2017-06-11")
//                .setEndTime("2017-09-11")
//                .setTitle("五粮液")
//                .setContext("五粮液一诺")
//                .build();
//        DatabaseManager.getInstance().getProjectInfoDao().insert(projectInfo);
//
//        EduInfo educateInfo = EduInfo.builder()
//                .setId(1)
//                .setStartTime("2012-09")
//                .setEndTime("2016-07")
//                .setSchool("湖北大学")
//                .setSchoolType("本科")
//                .setPro("信息与计算科学")
//                .build();
//
//        DatabaseManager.getInstance().getEducateInfoDao().insert(educateInfo);
//        ExpInfo info = ExpInfo.builder()
//                .setId(3)
//                .withStartTime("2016-7")
//                .withEndTime("2018-1")
//                .withCompany("富土")
//                .withJob("程序猿")
//                .withWorkDes("混日子")
//                .build();
//        DatabaseManager.getInstance().getExpDao().insert(info);

        Resume.init(this)
                .configure();

        //设置字体
        Iconify.with(new FontAwesomeModule());


    }
}
