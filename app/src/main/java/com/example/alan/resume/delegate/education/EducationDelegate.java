package com.example.alan.resume.delegate.education;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.alan.resume.R;
import com.example.alan.resume.base.ResumeDelegate;
import com.example.alan.resume.delegate.education.detail.EduInfoDelegate;
import com.example.alan.resume.recycler.MultipleItemEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Function :
 * Modify Date : 2018/2/5
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class EducationDelegate extends ResumeDelegate {

    @BindView(R.id.ryc_edu)
    RecyclerView mRecyclerView;

    @OnClick({R.id.tv_education_add})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_education_add:
                start(new EduInfoDelegate(), SINGLETASK);
                break;
            default:
                break;
        }
    }

    @Override
    public Object getLayout() {
        return R.layout.delegate_education_detail;
    }

    @Override
    public void onBindView() {

        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final List<MultipleItemEntity> data = new EduDetailConvert().convert();
        EduDetailAdapter adapter = new EduDetailAdapter(data);
        mRecyclerView.setAdapter(adapter);
        //    mRecyclerView.addOnItemTouchListener();

    }
}
