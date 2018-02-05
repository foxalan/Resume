package com.example.alan.resume.delegate.education.detail;

import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.alan.resume.R;
import com.example.alan.resume.recycler.ItemType;

import java.util.List;

/**
 * Function :
 * Modify Date : 2018/1/30
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class EduInfoAdapter extends BaseMultiItemQuickAdapter<EduBean, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    private IEduInfoClickListener infoClickListener;

    public void setInfoClickListener(IEduInfoClickListener infoClickListener) {
        this.infoClickListener = infoClickListener;
    }

    public EduInfoAdapter(List<EduBean> data) {
        super(data);
        addItemType(ItemType.EDU_DETAIL_INFO, R.layout.item_edu_info);
    }


    @Override
    protected void convert(BaseViewHolder helper, final EduBean item) {
        switch (helper.getItemViewType()) {
            case ItemType.EDU_DETAIL_INFO:
                RelativeLayout relativeLayout = helper.getView(R.id.rl_edu_info);
                relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        infoClickListener.onItemClick(item.getmId());
                    }
                });
                String title = item.getmTitle();
                String context = item.getmContext();
                helper.setText(R.id.tv_edu_item_title, title);
                helper.setText(R.id.tv_edu_item_info_context, context);
                break;
            default:
                break;
        }
    }
}
