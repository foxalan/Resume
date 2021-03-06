package com.example.alan.resume.delegate.pro;

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
import com.example.alan.resume.database.ProOpenHelper;
import com.example.alan.resume.delegate.HomeDelegate;
import com.example.alan.resume.delegate.pro.detail.ProInfoDelegate;
import com.example.alan.resume.delegate.pro.modify.ProModifyDelegate;
import com.example.alan.resume.loading.LatteLoader;
import com.example.alan.resume.recycler.BaseDecoration;
import com.example.alan.resume.recycler.MultipleItemEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Function :
 * Modify Date : 2018/2/8
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class ProDelegate extends ResumeDelegate implements IProInfoClickListener {

    @BindView(R.id.ryc_pro)
    RecyclerView mRecyclerView;

    private List<MultipleItemEntity> data;
    private ProDetailAdapter adapter;

    private static ProDelegate proDelegate = new ProDelegate();

    public static ProDelegate getInstance() {
        if (proDelegate == null) {
            proDelegate = new ProDelegate();
        }
        return proDelegate;
    }


    @OnClick({R.id.ict_pro_back, R.id.tv_pro_add})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.ict_pro_back:
                start(new HomeDelegate(),SINGLETASK);
                break;
            case R.id.tv_pro_add:
                start(new ProInfoDelegate(),SINGLETASK);
                break;
            default:
                break;
        }
    }

    @Override
    public void onNewBundle(Bundle args) {
        super.onNewBundle(args);

    }

    private void refresh(){
        data.clear();
        data.addAll(new ProDetailConvert().convert());
        adapter.notifyDataSetChanged();
    }

    @Override
    public Object getLayout() {
        return R.layout.delegate_pro_detail;
    }

    @Override
    public void onBindView() {

        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 5));
        data = new ProDetailConvert().convert();
        adapter = new ProDetailAdapter(data);
        mRecyclerView.setAdapter(adapter);
        adapter.setInfoClickListener(this);
        adapter.setModifyLongClickListerer(new IProModifyLongClickListerer() {
            @Override
            public void onItemClick(long id) {
                DeleteDialog.showDialog();
                DeleteDialog.setPosition(id);
                DeleteDialog.setDeleteItemBack(new IDeleteItemBack() {
                    @Override
                    public void delete(long id) {
                        LatteLoader.showLoading(getContext());
                        ProOpenHelper.getInstance().delete(id);
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
        bundle.putLong("pro_id", id);
        proDelegate.setArguments(bundle);
        start(new ProModifyDelegate());
    }
}
