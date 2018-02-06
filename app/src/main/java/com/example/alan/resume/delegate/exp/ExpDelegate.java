package com.example.alan.resume.delegate.exp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.alan.resume.R;
import com.example.alan.resume.base.ResumeDelegate;
import com.example.alan.resume.recycler.MultipleItemEntity;

import java.util.List;

import butterknife.BindView;

/**
 * Function :
 * Modify Date : 2018/2/6
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class ExpDelegate extends ResumeDelegate {


    @BindView(R.id.ryc_exp)
    RecyclerView mRecyclerView;

    @Override
    public Object getLayout() {
        return R.layout.delegate_exp_detail;
    }

    @Override
    public void onBindView() {

        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final List<MultipleItemEntity> data = new ExpDetailConvert().convert();
        ExpDetailAdapter adapter = new ExpDetailAdapter(data);
        mRecyclerView.setAdapter(adapter);

    }
}
