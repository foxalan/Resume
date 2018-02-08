package com.example.alan.resume.delegate.pro;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.alan.resume.R;
import com.example.alan.resume.base.ResumeDelegate;
import com.example.alan.resume.recycler.BaseDecoration;
import com.example.alan.resume.recycler.MultipleItemEntity;

import java.util.List;

import butterknife.BindView;

/**
 * Function :
 * Modify Date : 2018/2/8
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class ProDelegate extends ResumeDelegate implements IProInfoClickListener{

    @BindView(R.id.ryc_pro)
    RecyclerView mRecyclerView;

    private List<MultipleItemEntity> data;
    private ProDetailAdapter adapter;

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
    }

    @Override
    public void onItemClick(long id) {

    }
}
