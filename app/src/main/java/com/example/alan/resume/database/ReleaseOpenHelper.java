package com.example.alan.resume.database;

import android.content.Context;

import com.example.alan.resume.entity.DaoMaster;

import org.greenrobot.greendao.database.Database;

/**
 * Function :
 * Modify Date : 2018/2/1
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class ReleaseOpenHelper extends DaoMaster.DevOpenHelper {

    public ReleaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
    }
}
