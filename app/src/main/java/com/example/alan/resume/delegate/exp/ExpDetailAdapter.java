package com.example.alan.resume.delegate.exp;

import com.example.alan.resume.R;
import com.example.alan.resume.recycler.ItemType;
import com.example.alan.resume.recycler.MultipleItemEntity;
import com.example.alan.resume.recycler.MultipleRecyclerAdapter;
import com.example.alan.resume.recycler.MultipleViewHolder;

import java.util.List;

/**
 * Function :
 * Modify Date : 2018/2/6
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class ExpDetailAdapter extends MultipleRecyclerAdapter {

    protected ExpDetailAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ItemType.EXP_DETAIL, R.layout.item_exp_detail);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        switch (holder.getItemViewType()) {
            case ItemType.EXP_DETAIL:
                String company = entity.getField(ExpItemFields.EXP_ITEM_COMPANY);
                String startTime = entity.getField(ExpItemFields.EXP_ITEM_START_TIME);
                String endTime = entity.getField(ExpItemFields.EXP_ITEM_END_TIME);
                holder.setText(R.id.tv_exp_company_item, company);
                holder.setText(R.id.tv_exp_item_time,startTime+"-"+endTime);
                break;
            default:
                break;
        }
    }
}
