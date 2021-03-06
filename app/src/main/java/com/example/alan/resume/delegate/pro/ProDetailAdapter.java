package com.example.alan.resume.delegate.pro;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.alan.resume.R;
import com.example.alan.resume.recycler.ItemType;
import com.example.alan.resume.recycler.MultipleItemEntity;
import com.example.alan.resume.recycler.MultipleRecyclerAdapter;
import com.example.alan.resume.recycler.MultipleViewHolder;

import java.util.List;

/**
 * Function :
 * Modify Date : 2018/2/8
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class ProDetailAdapter extends MultipleRecyclerAdapter {

    private IProInfoClickListener infoClickListener;
    private IProModifyLongClickListerer modifyLongClickListerer;

    public void setModifyLongClickListerer(IProModifyLongClickListerer modifyLongClickListerer) {
        this.modifyLongClickListerer = modifyLongClickListerer;
    }

    public void setInfoClickListener(IProInfoClickListener infoClickListener) {
        this.infoClickListener = infoClickListener;
    }

    protected ProDetailAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ItemType.PRO_DETAIL, R.layout.item_pro_detail);

    }

    @Override
    protected void convert(MultipleViewHolder holder,final MultipleItemEntity entity) {

        switch (holder.getItemViewType()) {
            case ItemType.PRO_DETAIL:

                final long id = entity.getField(ProItemFields.PRO_ITEM_ID);

                RelativeLayout relativeLayout = holder.getView(R.id.rl_pro_item);
                relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (infoClickListener!=null){
                            infoClickListener.onItemClick(id);
                        }
                    }
                });

                relativeLayout.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if (modifyLongClickListerer!=null){
                            modifyLongClickListerer.onItemClick(id);
                        }
                        return false;
                    }
                });

                String title = entity.getField(ProItemFields.PRO_ITEM_TITLE);
                String startTime = entity.getField(ProItemFields.PRO_ITEM_START_TIME);
                String endTime = entity.getField(ProItemFields.PRO_ITEM_END_TIME);

                AppCompatTextView textView = holder.getView(R.id.tv_pro_item_name);
                textView.setText(title);
                holder.setText(R.id.tv_pro_item_time,startTime+"-"+endTime);

                break;
            default:
                break;
        }
    }
}
