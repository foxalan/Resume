package com.example.alan.resume.delegate.exp;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.alan.resume.R;
import com.example.alan.resume.base.ResumeDelegate;
import com.example.alan.resume.delegate.HomeDelegate;
import com.example.alan.resume.delegate.exp.detail.ExpInfoDelegate;
import com.example.alan.resume.delegate.exp.modify.ExpModifyDelegate;
import com.example.alan.resume.recycler.BaseDecoration;
import com.example.alan.resume.recycler.MultipleItemEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Function :
 * Modify Date : 2018/2/6
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class ExpDelegate extends ResumeDelegate implements IExpInfoClickListener{


    @BindView(R.id.ryc_exp)
    RecyclerView mRecyclerView;

    private static ExpDelegate expDelegate = new ExpDelegate();

    public static ExpDelegate getInstance() {

        if (expDelegate == null) {
            expDelegate = new ExpDelegate();
        }
        return expDelegate;
    }

    @OnClick({R.id.tv_exp_add,R.id.icon_exp_back})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_exp_add:
                start(new ExpInfoDelegate());
                break;
            case R.id.icon_exp_back:
                start(new HomeDelegate(),SINGLETOP);

                break;
            default:
                break;
        }
    }

    @Override
    public Object getLayout() {
        return R.layout.delegate_exp_detail;
    }

    @Override
    public void onBindView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 5));
        final List<MultipleItemEntity> data = new ExpDetailConvert().convert();
        ExpDetailAdapter adapter = new ExpDetailAdapter(data);
        mRecyclerView.setAdapter(adapter);

        adapter.setInfoClickListener(this);
    }



    @Override
    public void onItemClick(long id) {
        Bundle bundle = new Bundle();
        bundle.putLong("exp_id",id);

        expDelegate.setArguments(bundle);
        start(new ExpModifyDelegate());
    }
}
