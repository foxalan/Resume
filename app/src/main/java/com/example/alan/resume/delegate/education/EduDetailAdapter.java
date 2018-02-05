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
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ItemType.EDU_DETAIL:

                break;
            default:
                break;
        }
    }
}
