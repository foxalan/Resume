package com.example.alan.resume.delegate.home;

import com.example.alan.resume.database.DatabaseManager;
import com.example.alan.resume.entity.EduInfo;
import com.example.alan.resume.entity.ExpInfo;
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
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class HomeDataConverter extends DataConverter {

    /**
     * 查询数据库获取所有的数据
     */
    @Override
    public ArrayList<MultipleItemEntity> convert() {

        ArrayList<MultipleItemEntity> data = new ArrayList<>();

        //用户信息
        List<UserInfo> userInfoList = DatabaseManager.getInstance().getUseInfoDao().loadAll();
        for (UserInfo info : userInfoList) {
            String name = info.getMName();
            int age = info.getMAge();
            String picPath = info.getMPicPath();
            String phone = info.getMPhone();

            MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                    .setItemType(ItemType.USER)
                    .setField(MultipleFields.NAME, name)
                    .setField(MultipleFields.AGE, age)
                    .setField(MultipleFields.PIC, picPath)
                    .setField(MultipleFields.PHONE, phone)
                    .build();
            data.add(itemEntity);
        }

//        //项目经验
//        List<ProInfo> proInfoList = DatabaseManager.getInstance().getProjectInfoDao().loadAll();
//        for (ProInfo info : proInfoList) {
//            String title = info.getMTitle();
//            String context = info.getMContext();
//            String startTime = info.getMStartTime();
//            String endTime = info.getMEndTime();
//
//            MultipleItemEntity itemEntity = MultipleItemEntity.builder()
//                    .setItemType(ItemType.PROJECT)
//                    .setField(MultipleFields.PRO_START_TIME, startTime)
//                    .setField(MultipleFields.PRO_END_TIME, endTime)
//                    .setField(MultipleFields.PRO_TITLE, title)
//                    .setField(MultipleFields.PRO_CONTEXT, context)
//                    .build();
//            data.add(itemEntity);
//        }

        //学历
        List<EduInfo> eduInfoList = DatabaseManager.getInstance().getEducateInfoDao().loadAll();
        for (EduInfo info : eduInfoList) {
            String school = info.getMSchool();
            String startTime = info.getMStartTime();
            String endTime = info.getMEndTime();
            String schoolType = info.getMSchoolType();
            String pro = info.getMPro();

            MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                    .setItemType(ItemType.EDUCATION)
                    .setField(MultipleFields.EDU_SCHOOL, school)
                    .setField(MultipleFields.EDU_SCHOOL_TYPE, schoolType)
                    .setField(MultipleFields.EDU_START_TIME, startTime)
                    .setField(MultipleFields.EDU_PRO, pro)
                    .setField(MultipleFields.EDU_END_TIME, endTime)
                    .build();
            data.add(itemEntity);
        }

        //工作经验
        List<ExpInfo> expInfoList = DatabaseManager.getInstance().getExpDao().loadAll();
        MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                .setItemType(ItemType.EXPERIENCE)
                .setField(MultipleFields.EXP_ALL, expInfoList)
                .build();
        data.add(itemEntity);


        return data;
    }
}
