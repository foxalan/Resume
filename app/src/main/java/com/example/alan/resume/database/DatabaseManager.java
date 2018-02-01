package com.example.alan.resume.database;

import android.content.Context;

import com.example.alan.resume.entity.DaoMaster;
import com.example.alan.resume.entity.DaoSession;
import com.example.alan.resume.entity.UserInfoDao;

import org.greenrobot.greendao.database.Database;

/**
 * Function :
 * Modify Date : 2018/2/1
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class DatabaseManager {

    private DaoSession mDaoSession = null;
    private DaoMaster mDaoMaster = null;
    private UserInfoDao mDao = null;

    private DatabaseManager() {
    }

    public DatabaseManager init(Context context) {
        initDao(context);
        return this;
    }

    private static final class Holder {
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }

    public static DatabaseManager getInstance() {
        return Holder.INSTANCE;
    }

    private void initDao(Context context) {
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "fast_ec.db");
        final Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mDao = mDaoSession.getUserInfoDao();
    }

    public final UserInfoDao getUseInfoDao() {
        return mDao;
    }


}
