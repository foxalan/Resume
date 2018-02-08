package com.example.alan.resume.delegate.pro;

import android.util.Log;

import com.example.alan.resume.database.DatabaseManager;
import com.example.alan.resume.entity.ProInfo;
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

public class ProDetailConvert extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {

        ArrayList<MultipleItemEntity> data = new ArrayList<>();

        List<ProInfo> proInfoList = DatabaseManager.getInstance().getProjectInfoDao().loadAll();

        for (ProInfo info : proInfoList) {
            String name = info.getMTitle();
            String startTime = info.getMStartTime();
            String endTime = info.getMEndTime();
            long id = info.getMId();
            String context = info.getMContext();

            Log.e("tang", info.toString());
            MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                    .setItemType(ItemType.PRO_DETAIL)
                    .setField(ProItemFields.PRO_ITEM_ID,id)
                    .setField(ProItemFields.PRO_ITEM_START_TIME,startTime)
                    .setField(ProItemFields.PRO_ITEM_END_TIME,endTime)
                    .setField(ProItemFields.PRO_ITEM_TITLE,name)
                    .setField(ProItemFields.PRO_ITEM_CONTEXT,context)
                    .build();
            data.add(itemEntity);
        }

        return data;
    }
}
