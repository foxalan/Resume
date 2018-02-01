package com.example.alan.resume.application;

import android.app.Application;

import com.example.alan.resume.database.DatabaseManager;

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
    }
}
