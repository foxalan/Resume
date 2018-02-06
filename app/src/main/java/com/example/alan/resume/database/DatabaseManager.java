package com.example.alan.resume.database;

import android.content.Context;

import com.example.alan.resume.entity.DaoMaster;
import com.example.alan.resume.entity.DaoSession;
import com.example.alan.resume.entity.EducateInfoDao;
import com.example.alan.resume.entity.ExpInfoDao;
import com.example.alan.resume.entity.ProjectInfoDao;
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
    private DaoSession mDaoSessionProject = null;

    private UserInfoDao mDao = null;
    private ProjectInfoDao mProjectInfoDao = null;

    private EducateInfoDao mEducateInfoDao = null;
    private DaoSession mDaoSessionEdu = null;

    private ExpInfoDao mExpDao = null;
    private DaoSession mDaoSessionExp = null;


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

        final ReleaseOpenHelper projectHelper = new ReleaseOpenHelper(context, "fast.db");
        final Database dbProject = projectHelper.getWritableDb();
        mDaoSessionProject = new DaoMaster(dbProject).newSession();
        mProjectInfoDao = mDaoSessionProject.getProjectInfoDao();

        final ReleaseOpenHelper eduHelper = new ReleaseOpenHelper(context, "fast_edu.db");
        final Database dbEdu = eduHelper.getWritableDb();
        mDaoSessionEdu = new DaoMaster(dbEdu).newSession();
        mEducateInfoDao = mDaoSessionEdu.getEducateInfoDao();

        final ReleaseOpenHelper expHelper = new ReleaseOpenHelper(context,"fast_exp.db");
        final Database dbExp = expHelper.getWritableDb();
        mDaoSessionExp = new DaoMaster(dbExp).newSession();
        mExpDao = mDaoSessionExp.getExpInfoDao();

    }

    public final UserInfoDao getUseInfoDao() {
        return mDao;
    }

    public ProjectInfoDao getProjectInfoDao() {
        return mProjectInfoDao;
    }

    public EducateInfoDao getEducateInfoDao() {
        return mEducateInfoDao;
    }

    public ExpInfoDao getExpDao() {
        return mExpDao;
    }
}
