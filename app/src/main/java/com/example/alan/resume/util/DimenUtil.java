package com.example.alan.resume.util;


import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.example.alan.resume.application.Resume;


public class DimenUtil {

    public static int getScreenWidth(){

        return getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(){

        return getDisplayMetrics().heightPixels;
    }

    private static DisplayMetrics getDisplayMetrics(){

        final Resources resources = Resume.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();

        return dm;
    }
}
