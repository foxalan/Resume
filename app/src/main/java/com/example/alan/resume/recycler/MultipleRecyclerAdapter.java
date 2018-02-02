package com.example.alan.resume.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.example.alan.resume.R;
import com.example.alan.resume.delegate.home.IHeadClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alan
 */

public class MultipleRecyclerAdapter extends
        BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder> {

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

    public void refresh(List<MultipleItemEntity> data) {
        getData().clear();
        setNewData(data);
        notifyDataSetChanged();
    }

    private void init() {
        //设置不同的item布局
        addItemType(ItemType.USER, R.layout.item_multiple_user);
        addItemType(ItemType.EXPERIENCE, R.layout.item_multiple_experience);
        addItemType(ItemType.PROJECT, R.layout.item_multiple_project);
        addItemType(ItemType.EDUCATION, R.layout.item_multiple_education);

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
        final String text;
        final String imageUrl;
        final ArrayList<String> bannerImages;
        View view = holder.getView(R.id.rl_selected);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iHeadClickListener!= null){
                    iHeadClickListener.onHeadClick(holder.getItemViewType());
                }
            }
        });

        switch (holder.getItemViewType()) {
            case ItemType.USER:

                String name = entity.getField(MultipleFields.NAME);
                int age = entity.getField(MultipleFields.AGE);
                String phone = entity.getField(MultipleFields.PHONE);
                String pic = entity.getField(MultipleFields.PIC);
                holder.setText(R.id.tv_user_name,name);
                holder.setText(R.id.tv_user_age,String.valueOf(age));
                holder.setText(R.id.tv_user_phone,phone);


                break;
//            case ItemType.IMAGE:
//                imageUrl = entity.getField(MultipleFields.IMAGE_URL);
//                Glide.with(mContext)
//                        .load(imageUrl)
//                        .apply(RECYCLER_OPTIONS)
//                        .into((ImageView) holder.getView(R.id.img_single));
//                break;
//            case ItemType.TEXT_IMAGE:
//                text = entity.getField(MultipleFields.TEXT);
//                imageUrl = entity.getField(MultipleFields.IMAGE_URL);
//                Glide.with(mContext)
//                        .load(imageUrl)
//                        .apply(RECYCLER_OPTIONS)
//                        .into((ImageView) holder.getView(R.id.img_multiple));
//                holder.setText(R.id.tv_multiple, text);
//                break;
//            case ItemType.BANNER:
//                if (!mIsInitBanner) {
//                    bannerImages = entity.getField(MultipleFields.BANNERS);
//                    final ConvenientBanner<String> convenientBanner = holder.getView(R.id.banner_recycler_item);
//                    BannerCreator.setDefault(convenientBanner, bannerImages, this);
//                    mIsInitBanner = true;
//                }
//                break;
            default:
                break;
        }
    }


}
