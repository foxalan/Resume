package com.example.alan.resume.delegate.exp.detail;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.alan.resume.R;
import com.example.alan.resume.delegate.edu.detail.IEduInfoClickListener;
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

public class ExpInfoAdapter extends BaseMultiItemQuickAdapter<ExpBean, BaseViewHolder> {
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

    public ExpInfoAdapter(List<ExpBean> data) {
        super(data);
        addItemType(ItemType.DETAIL_INFO, R.layout.item_edu_info);
        addItemType(ItemType.EXP_INFO_DES, R.layout.item_edu_info_des);
    }


    @Override
    protected void convert(BaseViewHolder helper, final ExpBean item) {
        switch (helper.getItemViewType()) {
            case ItemType.DETAIL_INFO:
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
            case ItemType.EXP_INFO_DES:
                RelativeLayout rl = helper.getView(R.id.rl_edu_info_des);
                rl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        infoClickListener.onItemClick(item.getmId());
                    }
                });
                String titleDes = item.getmTitle();
                String contextDes = item.getmContext();
                helper.setText(R.id.tv_edu_item_title_des, titleDes);
                AppCompatTextView textView = helper.getView(R.id.tv_edu_item_info_context_des);
                textView.setHint("已经填写" + contextDes.length() + "字");
//                helper.setText(R.id.tv_edu_item_info_context_des, contextDes);
                break;
            default:
                break;
        }
    }
}
