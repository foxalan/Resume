package com.example.alan.resume.delegate.edu;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.alan.resume.base.ResumeDelegate;
import com.example.alan.resume.recycler.MultipleItemEntity;


/**
 * @author Alan
 * 添加Item的点击事件
 */

public class EduItemClickListener extends SimpleClickListener {

    private final ResumeDelegate DELEGATE;

    private EduItemClickListener(ResumeDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static SimpleClickListener create(ResumeDelegate delegate) {
        return new EduItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
    //    final int goodsId = entity.getField(MultipleFields.ID);
//        final GoodsDetailDelegate delegate = GoodsDetailDelegate.create(goodsId);
//        DELEGATE.getSupportDelegate().start(delegate);
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
    }
}
