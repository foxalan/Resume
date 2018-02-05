package com.example.alan.resume.delegate.education;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.alan.resume.R;
import com.example.alan.resume.base.ResumeDelegate;

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

    @OnClick({R.id.tv_education_save})
    void onClick(View view){
        switch (view.getId()){
            case R.id.tv_education_save:

                break;
                default:
        }
    }
    @Override
    public Object getLayout() {
        return R.layout.delegate_education_detail;
    }

    @Override
    public void onBindView() {

    }
}
