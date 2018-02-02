package com.example.alan.resume.application;

import android.app.Application;

import com.example.alan.resume.database.DatabaseManager;
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
                .setId(1)
                .setName("alan")
                .setAge(23)
                .setExperience("2")
                .setLocation("wuhan")
                .setPhone("18202710074")
                .build();
        DatabaseManager.getInstance().getUseInfoDao().insert(userInfo);

        //设置字体
        Iconify.with(new FontAwesomeModule());
    }
}
