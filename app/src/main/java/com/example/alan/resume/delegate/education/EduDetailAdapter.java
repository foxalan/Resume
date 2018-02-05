package com.example.alan.resume.delegate.education;

import com.example.alan.resume.R;
import com.example.alan.resume.recycler.ItemType;
import com.example.alan.resume.recycler.MultipleItemEntity;
import com.example.alan.resume.recycler.MultipleRecyclerAdapter;
import com.example.alan.resume.recycler.MultipleViewHolder;

import java.util.List;

/**
 * Function :
 * Modify Date : 2018/2/5
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class EduDetailAdapter extends MultipleRecyclerAdapter {

    protected EduDetailAdapter(List<MultipleItemEntity> data) {
        super(data);

        addItemType(ItemType.EDU_DETAIL, R.layout.item_edu_detail);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
       
        switch (holder.getItemViewType()) {
            case ItemType.EDU_DETAIL:
                String school = entity.getField(EduItemFields.EDU_ITEM_SCHOOL);
                String startTime = entity.getField(EduItemFields.EDU_ITEM_START_TIME);
                String endTime = entity.getField(EduItemFields.EDU_ITEM_END_TIME);
                holder.setText(R.id.tv_edu_item_school, school);
                holder.setText(R.id.tv_edu_item_time,startTime+"-"+endTime);
                break;
            default:
                break;
        }
    }
}
