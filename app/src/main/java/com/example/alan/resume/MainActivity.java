package com.example.alan.resume;

import android.os.Bundle;
import android.support.v7.app.ActionBar;

import com.example.alan.resume.base.ProxyActivity;
import com.example.alan.resume.base.ResumeDelegate;
import com.example.alan.resume.delegate.HomeDelegate;

import qiu.niorgai.StatusBarCompat;


public class MainActivity extends ProxyActivity {

    @Override
    public ResumeDelegate setRootDelegate() {
        return new HomeDelegate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.hide();
        }
        //设置android沉浸式状态栏功能

        StatusBarCompat.translucentStatusBar(this, true);
    }
}
