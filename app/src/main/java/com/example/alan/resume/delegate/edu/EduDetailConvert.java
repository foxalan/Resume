package com.example.alan.resume.delegate.edu;

import android.util.Log;

import com.example.alan.resume.database.DatabaseManager;
import com.example.alan.resume.entity.EduInfo;
import com.example.alan.resume.recycler.DataConverter;
import com.example.alan.resume.recycler.ItemType;
import com.example.alan.resume.recycler.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Function :
 * Modify Date : 2018/2/5
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class EduDetailConvert extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {

        ArrayList<MultipleItemEntity> data = new ArrayList<>();

        List<EduInfo> eduInfoList = DatabaseManager.getInstance().getEducateInfoDao().loadAll();
        for (EduInfo info: eduInfoList){
            String school = info.getMSchool();
            String startTime = info.getMStartTime();
            String endTime = info.getMEndTime();
            long id = info.getMId();
            Log.e("huiye",info.toString());

            MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                    .setItemType(ItemType.EDU_DETAIL)
                    .setField(EduItemFields.EDU_ITEM_ID,id)
                    .setField(EduItemFields.EDU_ITEM_SCHOOL,school)
                    .setField(EduItemFields.EDU_ITEM_START_TIME,startTime)
                    .setField(EduItemFields.EDU_ITEM_END_TIME,endTime)
                    .build();
            data.add(itemEntity);
        }

        return data;
    }
}
