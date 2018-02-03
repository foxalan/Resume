package com.example.alan.resume.delegate.home;

import com.example.alan.resume.database.DatabaseManager;
import com.example.alan.resume.entity.ProjectInfo;
import com.example.alan.resume.entity.UserInfo;
import com.example.alan.resume.recycler.DataConverter;
import com.example.alan.resume.recycler.ItemType;
import com.example.alan.resume.recycler.MultipleFields;
import com.example.alan.resume.recycler.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Function :
 * Modify Date : 2018/2/2
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class HomeDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {

        ArrayList<MultipleItemEntity> data = new ArrayList<>();

        List<UserInfo> userInfoList = DatabaseManager.getInstance().getUseInfoDao().loadAll();
        for (UserInfo info:userInfoList){
            String name = info.getMName();
            int age = info.getMAge();
            String picPath = info.getMPicPath();
            String phone = info.getMPhone();

            MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                    .setItemType(ItemType.USER)
                    .setField(MultipleFields.NAME,name)
                    .setField(MultipleFields.AGE,age)
                    .setField(MultipleFields.PIC,picPath)
                    .setField(MultipleFields.PHONE,phone)
                    .build();
            data.add(itemEntity);
        }

        List<ProjectInfo> projectInfoList = DatabaseManager.getInstance().getProjectInfoDao().loadAll();
        for (ProjectInfo info:projectInfoList){
            String title = info.getMTitle();
            String context = info.getMContext();
            String startTime = info.getMStartTime();
            String endTime = info.getMEndTime();

            MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                    .setItemType(ItemType.PROJECT)
                    .setField(MultipleFields.START_TIME,startTime)
                    .setField(MultipleFields.END_TIME,endTime)
                    .setField(MultipleFields.PORJECT_TITLE,title)
                    .setField(MultipleFields.PROJECT_CONTEXT,context)
                    .build();
            data.add(itemEntity);
        }


        return data;
    }
}
