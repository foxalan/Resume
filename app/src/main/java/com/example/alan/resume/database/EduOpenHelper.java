package com.example.alan.resume.database;

import com.example.alan.resume.delegate.edu.detail.EduBean;
import com.example.alan.resume.entity.EduInfo;

import java.util.List;

/**
 * Function :
 * Modify Date : 2018/2/7
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class EduOpenHelper {

    private static final class Holder{
        private static EduOpenHelper helper = new EduOpenHelper();
    }

    public static EduOpenHelper getInstance(){
        return Holder.helper;
    }

    public void insert(List<EduBean> eduBeanList){
        List<EduInfo> eduInfoList = DatabaseManager.getInstance().getEducateInfoDao().loadAll();
        int size = eduInfoList.size();
        long currentId = 0;
        if (size!=0){
            currentId = eduInfoList.get(size-1).getMId()+1;
        }

         String mStartTime = eduBeanList.get(0).getmContext();
         String mEndTime = eduBeanList.get(1).getmContext();
         String mSchool = eduBeanList.get(2).getmContext();
         String mSchoolType = eduBeanList.get(3).getmContext();
         String mPro = eduBeanList.get(4).getmContext();
         EduInfo eduInfo = EduInfo.builder()
                 .setId(currentId)
                 .setStartTime(mStartTime)
                 .setEndTime(mEndTime)
                 .setSchool(mSchool)
                 .setSchoolType(mSchoolType)
                 .setPro(mPro)
                 .build();
         DatabaseManager.getInstance().getEducateInfoDao().insert(eduInfo);
    }

    public void update(EduInfo info){
        DatabaseManager.getInstance().getEducateInfoDao().update(info);
    }
}
