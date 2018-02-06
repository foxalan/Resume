package com.example.alan.resume.delegate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.alan.resume.R;
import com.example.alan.resume.base.ResumeDelegate;
import com.example.alan.resume.delegate.detail.UserDetailDelegate;
import com.example.alan.resume.delegate.edu.EduDelegate;
import com.example.alan.resume.delegate.home.HomeDataConverter;
import com.example.alan.resume.delegate.home.IHeadClickListener;
import com.example.alan.resume.delegate.exp.ExpDelegate;
import com.example.alan.resume.recycler.ItemType;
import com.example.alan.resume.recycler.MultipleItemEntity;
import com.example.alan.resume.recycler.MultipleRecyclerAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Function :
 * Modify Date : 2018/2/1
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class HomeDelegate extends ResumeDelegate implements IHeadClickListener {

    @BindView(R.id.ryc_resume)
    RecyclerView mRecyclerView;


    @Override
    public Object getLayout() {
        return R.layout.delegate_home;
    }

    @Override
    public void onBindView() {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final List<MultipleItemEntity> data = new HomeDataConverter().convert();
        MultipleRecyclerAdapter adapter = MultipleRecyclerAdapter.create(data);
        mRecyclerView.setAdapter(adapter);
        adapter.setHeadClickListener(this);
    }

    @Override
    public void onHeadClick(int itemType) {
        switch (itemType) {
            case ItemType.USER:
                start(new UserDetailDelegate());
                break;
            case ItemType.PROJECT:
                break;
            case ItemType.EDUCATION:
                start(new EduDelegate(), SINGLETASK);
                break;
            case ItemType.EXPERIENCE:
                start(new ExpDelegate(),SINGLETASK);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onBackPressedSupport() {

        return super.onBackPressedSupport();
    }
}
