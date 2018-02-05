package com.example.alan.resume.application;

import android.app.Application;

import com.example.alan.resume.database.DatabaseManager;
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
//        UserInfo userInfo = UserInfo.builder()
//
//                .setName("alan")
//                .setAge(23)
//                .setExperience("2")
//                .setLocation("wuhan")
//                .setPhone("18202710074")
//                .build();
//        UserInfo userInfo2 = UserInfo.builder()
//                .setId(10)
//                .setName("alan2")
//                .setAge(234)
//                .setExperience("12")
//                .setLocation("wuhan1")
//                .setPhone("182027100741")
//                .build();
//        DatabaseManager.getInstance().getUseInfoDao().insert(userInfo);
//
//        DatabaseManager.getInstance().getUseInfoDao().insert(userInfo2);
//
//        ProjectInfo projectInfo = ProjectInfo.builder()
//                .setId(1)
//                .setStartTime("2017-06-11")
//                .setEndTime("2017-09-11")
//                .setTitle("WLY")
//                .setContext("wly create")
//                .build();
//        DatabaseManager.getInstance().getProjectInfoDao().insert(projectInfo);


        //设置字体
        Iconify.with(new FontAwesomeModule());
    }
}
