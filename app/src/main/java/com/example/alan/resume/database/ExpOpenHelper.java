package com.example.alan.resume.database;

import com.example.alan.resume.delegate.exp.detail.ExpBean;
import com.example.alan.resume.entity.ExpInfo;

import java.util.List;

/**
 * Function :
 * Modify Date : 2018/2/8
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class ExpOpenHelper {

    private static final class Holder{
        private static ExpOpenHelper helper = new ExpOpenHelper();
    }

    public static ExpOpenHelper getInstance(){
        return ExpOpenHelper.Holder.helper;
    }

    public void insert(List<ExpBean> expBeanList){
        List<ExpInfo> expInfoList = DatabaseManager.getInstance().getExpDao().loadAll();
        int size = expInfoList.size();
        long currentId = 0;
        if (size!=0){
            currentId = expInfoList.get(size-1).getId()+1;
        }

        String mStartTime = expBeanList.get(0).getmContext();
        String mEndTime = expBeanList.get(1).getmContext();
        String mCompany = expBeanList.get(2).getmContext();
        String mJob = expBeanList.get(3).getmContext();
        String mJobDes = expBeanList.get(4).getmContext();
        ExpInfo expInfo = ExpInfo.builder()
                .setId(currentId)
                .withStartTime(mStartTime)
                .withEndTime(mEndTime)
                .withCompany(mCompany)
                .withJob(mJob)
                .withWorkDes(mJobDes)
                .build();
        DatabaseManager.getInstance().getExpDao().insert(expInfo);
    }

    public void update(ExpInfo info){
        DatabaseManager.getInstance().getExpDao().update(info);
    }

    public void delete(long id){
        DatabaseManager.getInstance().getExpDao().deleteByKey(id);
    }
}
