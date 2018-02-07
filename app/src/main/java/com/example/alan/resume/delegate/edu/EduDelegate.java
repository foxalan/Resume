package com.example.alan.resume.delegate.edu;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.alan.resume.R;
import com.example.alan.resume.base.ResumeDelegate;
import com.example.alan.resume.delegate.HomeDelegate;
import com.example.alan.resume.delegate.edu.detail.EduInfoDelegate;
import com.example.alan.resume.delegate.edu.modify.EduModifyDelegate;
import com.example.alan.resume.recycler.BaseDecoration;
import com.example.alan.resume.recycler.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Function :
 * Modify Date : 2018/2/5
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class EduDelegate extends ResumeDelegate implements IEduModifyClickListener {

    @BindView(R.id.ryc_edu)
    RecyclerView mRecyclerView;
    List<MultipleItemEntity> data = new ArrayList<>();

    private static EduDelegate eduDelegate = new EduDelegate();

    public static EduDelegate getInstance() {
        if (eduDelegate == null) {
            eduDelegate = new EduDelegate();
        }
        return eduDelegate;
    }

    @OnClick({R.id.tv_education_add, R.id.ict_edu_back})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_education_add:
                start(new EduInfoDelegate(),SINGLETASK);
                break;
            case R.id.ict_edu_back:

                start(HomeDelegate.getInstance(),STANDARD);
            //    HomeDelegate.getInstance().refresh(1);
                break;
            default:
                break;
        }
    }

    @Override
    public Object getLayout() {
        return R.layout.delegate_edu_detail;
    }

    public  EduDetailAdapter adapter;

    @Override
    public void onBindView() {

        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 5));
        data = new EduDetailConvert().convert();
        adapter = new EduDetailAdapter(data);
        mRecyclerView.setAdapter(adapter);
        adapter.setInfoClickListener(this);
    }

    public void refresh() {
        data.clear();
        data = new EduDetailConvert().convert();
        if (adapter == null){
            adapter = new EduDetailAdapter(data);
        }
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onItemClick(long id) {
        Bundle bundle = new Bundle();
        bundle.putLong("edu_id", id);
        eduDelegate.setArguments(bundle);
        start(new EduModifyDelegate());
    }
}
