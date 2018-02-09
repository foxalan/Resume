package com.example.alan.resume.delegate.exp;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.alan.resume.R;
import com.example.alan.resume.alert.DeleteDialog;
import com.example.alan.resume.alert.IDeleteItemBack;
import com.example.alan.resume.application.Resume;
import com.example.alan.resume.base.ResumeDelegate;
import com.example.alan.resume.database.ExpOpenHelper;
import com.example.alan.resume.delegate.HomeDelegate;
import com.example.alan.resume.delegate.edu.IEduModifyLongClickListener;
import com.example.alan.resume.delegate.exp.detail.ExpInfoDelegate;
import com.example.alan.resume.delegate.exp.modify.ExpModifyDelegate;
import com.example.alan.resume.loading.LatteLoader;
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
    private List<MultipleItemEntity> data;
    private ExpDetailAdapter adapter;

    @Override
    public void onNewBundle(Bundle args) {
        super.onNewBundle(args);
    }

    private void refresh(){
        data.clear();
        data.addAll(new ExpDetailConvert().convert());
        adapter.notifyDataSetChanged();
    }

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
                start(new ExpInfoDelegate(),SINGLETASK);
                break;
            case R.id.icon_exp_back:
                start(new HomeDelegate(),SINGLETASK);
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
        DeleteDialog.createLoading(getContext());
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 5));
        data = new ExpDetailConvert().convert();
        adapter = new ExpDetailAdapter(data);
        mRecyclerView.setAdapter(adapter);

        adapter.setInfoClickListener(this);
        adapter.setModifyLongClickListener(new IEduModifyLongClickListener() {
            @Override
            public void onItemClick(long id) {
                DeleteDialog.showDialog();
                DeleteDialog.setPosition(id);
                DeleteDialog.setDeleteItemBack(new IDeleteItemBack() {
                    @Override
                    public void delete(long id) {
                        LatteLoader.showLoading(getContext());
                        ExpOpenHelper.getInstance().delete(id);
                        Resume.getHandler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                refresh();
                                LatteLoader.stopLoading();
                            }
                        },1000);
                    }
                });
            }
        });
    }



    @Override
    public void onItemClick(long id) {
        Bundle bundle = new Bundle();
        bundle.putLong("exp_id",id);

        expDelegate.setArguments(bundle);
        start(new ExpModifyDelegate());
    }
}
