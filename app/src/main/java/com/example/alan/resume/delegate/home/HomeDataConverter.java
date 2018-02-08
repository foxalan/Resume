package com.example.alan.resume.delegate.home;

import com.example.alan.resume.database.DatabaseManager;
import com.example.alan.resume.entity.EduInfo;
import com.example.alan.resume.entity.ExpInfo;
import com.example.alan.resume.entity.ProInfo;
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


        //学历
        List<EduInfo> eduInfoList = DatabaseManager.getInstance().getEducateInfoDao().loadAll();
        MultipleItemEntity itemEduEntity = MultipleItemEntity.builder()
                .setItemType(ItemType.EDUCATION)
                .setField(MultipleFields.EDU_ALL, eduInfoList)
                .build();
        data.add(itemEduEntity);

        //工作经验
        List<ExpInfo> expInfoList = DatabaseManager.getInstance().getExpDao().loadAll();
        MultipleItemEntity itemExpEntity = MultipleItemEntity.builder()
                .setItemType(ItemType.EXPERIENCE)
                .setField(MultipleFields.EXP_ALL, expInfoList)
                .build();
        data.add(itemExpEntity);

        //项目经验
        List<ProInfo> proInfoList = DatabaseManager.getInstance().getProjectInfoDao().loadAll();
        MultipleItemEntity itemProEntity = MultipleItemEntity.builder()
                .setItemType(ItemType.PROJECT)
                .setField(MultipleFields.PRO_ALL, proInfoList)
                .build();
        data.add(itemProEntity);


        return data;
    }
}
