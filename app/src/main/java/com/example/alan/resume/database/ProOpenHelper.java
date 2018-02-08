package com.example.alan.resume.database;

import com.example.alan.resume.delegate.pro.detail.ProBean;
import com.example.alan.resume.entity.ProInfo;

import java.util.List;

/**
 * Function :
 * Modify Date : 2018/2/8
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class ProOpenHelper {

    private static final class Holder{
        private static ProOpenHelper helper = new ProOpenHelper();
    }

    public static ProOpenHelper getInstance(){
        return ProOpenHelper.Holder.helper;
    }

    public void insert(List<ProBean> proBeanList){
        List<ProInfo> proInfoList = DatabaseManager.getInstance().getProjectInfoDao().loadAll();
        int size = proInfoList.size();
        long currentId = 0;
        if (size!=0){
            currentId = proInfoList.get(size-1).getMId()+1;
        }

        String mStartTime = proBeanList.get(0).getmContext();
        String mEndTime = proBeanList.get(1).getmContext();
        String mCompany = proBeanList.get(2).getmContext();
        String mJob = proBeanList.get(3).getmContext();

        ProInfo proInfo = ProInfo.builder()
                .setId(currentId)
                .setStartTime(mStartTime)
                .setEndTime(mEndTime)
                .setTitle(mCompany)
                .setContext(mJob)
                .build();
        DatabaseManager.getInstance().getProjectInfoDao().insert(proInfo);
    }

    public void update(ProInfo info){
        DatabaseManager.getInstance().getProjectInfoDao().update(info);
    }
}
