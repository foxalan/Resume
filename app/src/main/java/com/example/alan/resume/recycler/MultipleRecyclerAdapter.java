package com.example.alan.resume.recycler;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.example.alan.resume.R;
import com.example.alan.resume.application.Resume;
import com.example.alan.resume.delegate.edu.item.EduItemAdapter;
import com.example.alan.resume.delegate.exp.item.ExpItemAdapter;
import com.example.alan.resume.delegate.home.IHeadClickListener;
import com.example.alan.resume.delegate.pro.item.ProItemAdapter;
import com.example.alan.resume.entity.EduInfo;
import com.example.alan.resume.entity.ExpInfo;
import com.example.alan.resume.entity.ProInfo;

import java.io.File;
import java.util.List;

/**
 * @author Alan
 */

public class MultipleRecyclerAdapter extends
        BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder> {

    private ExpItemAdapter expItemAdapter;
    private EduItemAdapter eduInfoAdapter;

    private static final RequestOptions RECYCLER_OPTIONS =
            new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate();

    protected MultipleRecyclerAdapter(List<MultipleItemEntity> data) {
        super(data);
        init();
    }

    private IHeadClickListener iHeadClickListener;

    public void setHeadClickListener(IHeadClickListener iHeadClickListener) {
        this.iHeadClickListener = iHeadClickListener;
    }

    public static MultipleRecyclerAdapter create(List<MultipleItemEntity> data) {
        return new MultipleRecyclerAdapter(data);
    }

    public static MultipleRecyclerAdapter create(DataConverter converter) {
        return new MultipleRecyclerAdapter(converter.convert());
    }


    private void init() {
        //设置不同的item布局
        addItemType(ItemType.USER, R.layout.item_multiple_user);
        addItemType(ItemType.EXPERIENCE, R.layout.item_multiple_exp);
        addItemType(ItemType.PROJECT, R.layout.item_multiple_pro);
        addItemType(ItemType.EDUCATION, R.layout.item_multiple_edu);

        openLoadAnimation();
        //多次执行动画
        isFirstOnly(false);
    }

    @Override
    protected MultipleViewHolder createBaseViewHolder(View view) {
        return MultipleViewHolder.create(view);
    }

    @Override
    protected void convert(final MultipleViewHolder holder, MultipleItemEntity entity) {

        View view = holder.getView(R.id.rl_selected);
        AppCompatTextView mTvHead = holder.getView(R.id.tv_head);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iHeadClickListener != null) {
                    iHeadClickListener.onHeadClick(holder.getItemViewType());
                }
            }
        });

        switch (holder.getItemViewType()) {
            case ItemType.USER:
                mTvHead.setText("基本信息");
                String name = entity.getField(MultipleFields.NAME);
                int age = entity.getField(MultipleFields.AGE);
                String phone = entity.getField(MultipleFields.PHONE);
                String pic = entity.getField(MultipleFields.PIC);
                AppCompatImageView imageView = holder.getView(R.id.iv_user);
                if (!"".equals(pic) && pic != null) {

                    Glide.with(Resume.getApplicationContext())
                            .load(new File(pic))
                            .into(imageView);
                }
                holder.setText(R.id.tv_user_name, name);
                holder.setText(R.id.tv_user_age, String.valueOf(age));
                holder.setText(R.id.tv_user_phone, phone);
                break;
            case ItemType.PROJECT:
                mTvHead.setText("项目经验");
                RecyclerView recyclerView1 = holder.getView(R.id.ryc_pro_item);
                List<ProInfo> proInfoList = entity.getField(MultipleFields.PRO_ALL);
                Log.e("tang", proInfoList.get(0).toString() + "------------");
                ProItemAdapter adapter = new ProItemAdapter(proInfoList, Resume.getApplicationContext());
                recyclerView1.setLayoutManager(new LinearLayoutManager(Resume.getApplicationContext()));
                recyclerView1.setAdapter(adapter);
                break;
            case ItemType.EDUCATION:
                mTvHead.setText("教育经历");
                RecyclerView recyclerView = holder.getView(R.id.ryc_edu_item);
                List<EduInfo> eduInfoList = entity.getField(MultipleFields.EDU_ALL);
                eduInfoAdapter = new EduItemAdapter(eduInfoList, Resume.getApplicationContext());
                recyclerView.setLayoutManager(new LinearLayoutManager(Resume.getApplicationContext()));
                recyclerView.setAdapter(eduInfoAdapter);
                break;
            case ItemType.EXPERIENCE:
                mTvHead.setText("工作经验");
                RecyclerView mRecyclerView = holder.getView(R.id.ryc_exp_item);
                List<ExpInfo> expInfoList = entity.getField(MultipleFields.EXP_ALL);
                expItemAdapter = new ExpItemAdapter(expInfoList, Resume.getApplicationContext());
                mRecyclerView.setLayoutManager(new LinearLayoutManager(Resume.getApplicationContext()));
                mRecyclerView.setAdapter(expItemAdapter);
                break;
            default:
                break;
        }
    }


}
