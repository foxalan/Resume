package com.example.alan.resume.delegate.exp.modify;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.alan.resume.R;
import com.example.alan.resume.base.ResumeDelegate;
import com.example.alan.resume.database.DatabaseManager;
import com.example.alan.resume.delegate.edu.detail.IEduInfoClickListener;
import com.example.alan.resume.delegate.exp.ExpDelegate;
import com.example.alan.resume.delegate.exp.detail.ExpBean;
import com.example.alan.resume.delegate.exp.detail.ExpInfoAdapter;
import com.example.alan.resume.entity.ExpInfo;
import com.example.alan.resume.recycler.BaseDecoration;
import com.example.alan.resume.recycler.ItemType;

import java.util.ArrayList;
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

public class ExpModifyDelegate extends ResumeDelegate implements IEduInfoClickListener{

    private ExpInfoAdapter adapter;

    @BindView(R.id.ryc_edu_modify)
    RecyclerView mRecyclerView;
    private ExpInfo expInfo;
    private List<ExpBean> beanList = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = ExpDelegate.getInstance().getArguments();
        long id = bundle.getLong("exp_id");
        expInfo = DatabaseManager.getInstance().getExpDao().load(id);
        initData();
    }

    private void initData() {
        ExpBean startTime = ExpBean.builder()
                .setItemType(ItemType.DETAIL_INFO)
                .withId(0)
                .withTitle("入职时间")
                .withContext(expInfo.getStartTime())
                .build();

        ExpBean endTime = ExpBean.builder()
                .setItemType(ItemType.DETAIL_INFO)
                .withId(1)
                .withTitle("离职时间")
                .withContext(expInfo.getEndTime())
                .build();

        ExpBean company = ExpBean.builder()
                .setItemType(ItemType.DETAIL_INFO)
                .withId(2)
                .withTitle("公司名称")
                .withContext(expInfo.getCompany())
                .build();

        ExpBean job = ExpBean.builder()
                .setItemType(ItemType.DETAIL_INFO)
                .withId(3)
                .withTitle("职位")
                .withContext(expInfo.getJob())
                .build();

        ExpBean des = ExpBean.builder()
                .setItemType(ItemType.DETAIL_INFO)
                .withId(4)
                .withTitle("工作描述")
                .withContext(expInfo.getCompany())
                .build();

        beanList.add(startTime);
        beanList.add(endTime);
        beanList.add(company);
        beanList.add(job);
        beanList.add(des);
    }

    @Override
    public Object getLayout() {
        return R.layout.delegate_exp_modify;
    }

    @Override
    public void onBindView() {

        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        adapter = new ExpInfoAdapter(beanList);
        mRecyclerView.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 5));
        mRecyclerView.setAdapter(adapter);
        adapter.setInfoClickListener(this);
    }


    @Override
    public void onItemClick(int position) {

    }
}
