package com.example.alan.resume.delegate.exp;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.alan.resume.R;
import com.example.alan.resume.delegate.edu.IEduModifyLongClickListener;
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

    private IExpInfoClickListener infoClickListener;
    private IEduModifyLongClickListener modifyLongClickListener;

    public void setModifyLongClickListener(IEduModifyLongClickListener modifyLongClickListener) {
        this.modifyLongClickListener = modifyLongClickListener;
    }

    public void setInfoClickListener(IExpInfoClickListener infoClickListener) {
        this.infoClickListener = infoClickListener;
    }

    protected ExpDetailAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ItemType.EXP_DETAIL, R.layout.item_exp_detail);
    }

    @Override
    protected void convert(MultipleViewHolder holder, final MultipleItemEntity entity) {
        switch (holder.getItemViewType()) {
            case ItemType.EXP_DETAIL:

                final long id = entity.getField(ExpItemFields.EXP_ITEM_ID);

                RelativeLayout relativeLayout = holder.getView(R.id.rl_exp);
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
                        if (modifyLongClickListener!=null){
                            modifyLongClickListener.onItemClick(id);
                        }
                        return false;
                    }
                });

                String company = entity.getField(ExpItemFields.EXP_ITEM_COMPANY);
                String startTime = entity.getField(ExpItemFields.EXP_ITEM_START_TIME);
                String endTime = entity.getField(ExpItemFields.EXP_ITEM_END_TIME);


                AppCompatTextView textView = holder.getView(R.id.tv_exp_item_company);
                textView.setText(company);
                holder.setText(R.id.tv_exp_item_time,startTime+"-"+endTime);
                break;
            default:
                break;
        }
    }
}
