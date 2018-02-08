package com.example.alan.resume.delegate.pro.detail;

import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.alan.resume.R;
import com.example.alan.resume.recycler.ItemType;

import java.util.List;

/**
 * Function :
 * Modify Date : 2018/2/8
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class ProInfoAdapter extends BaseMultiItemQuickAdapter<ProBean,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ProInfoAdapter(List<ProBean> data) {
        super(data);
        addItemType(ItemType.DETAIL_INFO, R.layout.item_pro_info);

    }

    private IProInfoPositionClickListener infoPositionClickListener;

    public void setInfoPositionClickListener(IProInfoPositionClickListener infoPositionClickListener) {
        this.infoPositionClickListener = infoPositionClickListener;
    }

    @Override
    protected void convert(BaseViewHolder helper, final ProBean item) {
        switch (helper.getItemViewType()) {
            case ItemType.DETAIL_INFO:
                RelativeLayout relativeLayout = helper.getView(R.id.rl_pro_info);
                relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        infoPositionClickListener.onItemClick(item.getmId());
                    }
                });
                String title = item.getmTitle();
                String context = item.getmContext();
                helper.setText(R.id.tv_pro_item_title, title);
                helper.setText(R.id.tv_pro_item_info_context, context);
                break;
            default:
                break;
        }
    }
}
