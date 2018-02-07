package com.example.alan.resume.delegate.exp;

import android.util.Log;

import com.example.alan.resume.database.DatabaseManager;
import com.example.alan.resume.entity.ExpInfo;
import com.example.alan.resume.recycler.DataConverter;
import com.example.alan.resume.recycler.ItemType;
import com.example.alan.resume.recycler.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Function :
 * Modify Date : 2018/2/6
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class ExpDetailConvert extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {

        ArrayList<MultipleItemEntity> data = new ArrayList<>();

        List<ExpInfo> expInfoList = DatabaseManager.getInstance().getExpDao().loadAll();

        for (ExpInfo info : expInfoList) {
            String school = info.getCompany();
            String startTime = info.getStartTime();
            String endTime = info.getEndTime();
            long id = info.getId();

            Log.e("tang", info.toString());
            MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                    .setItemType(ItemType.EXP_DETAIL)
                    .setField(ExpItemFields.EXP_ITEM_COMPANY, school)
                    .setField(ExpItemFields.EXP_ITEM_START_TIME, startTime)
                    .setField(ExpItemFields.EXP_ITEM_END_TIME, endTime)
                    .setField(ExpItemFields.EXP_ITEM_ID,id)
                    .build();
            data.add(itemEntity);
        }

        return data;
    }
}
