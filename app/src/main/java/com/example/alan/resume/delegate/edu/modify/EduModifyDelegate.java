package com.example.alan.resume.delegate.edu.modify;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.alan.resume.R;
import com.example.alan.resume.base.ResumeDelegate;
import com.example.alan.resume.database.DatabaseManager;
import com.example.alan.resume.delegate.edu.EduDelegate;
import com.example.alan.resume.delegate.edu.detail.EduBean;
import com.example.alan.resume.delegate.edu.detail.EduInfoAdapter;
import com.example.alan.resume.delegate.edu.detail.IEduInfoClickListener;
import com.example.alan.resume.entity.EduInfo;
import com.example.alan.resume.recycler.BaseDecoration;
import com.example.alan.resume.recycler.ItemType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Function :
 * Modify Date : 2018/2/7
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class EduModifyDelegate extends ResumeDelegate implements IEduInfoClickListener {

    @BindView(R.id.ryc_edu_modify)
    RecyclerView mRecyclerView;

    private EduInfo eduInfo;
    private List<EduBean> beanList = new ArrayList<>();
    private EduInfoAdapter adapter;

    @OnClick({R.id.ict_edu_modify_back, R.id.tv_edu_info_modify_save})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.ict_edu_modify_back:
                start(EduDelegate.getInstance(),SINGLETASK);
                break;
            case R.id.tv_edu_info_modify_save:
                break;
            default:
                break;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = EduDelegate.getInstance().getArguments();
        long id = bundle.getLong("edu_id");
        eduInfo = DatabaseManager.getInstance().getEducateInfoDao().load(id);
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        EduBean startTime = EduBean.builder()
                .setItemType(ItemType.DETAIL_INFO)
                .withId(0)
                .withTitle("入学时间")
                .withContext(eduInfo.getMStartTime())
                .build();

        EduBean endTime = EduBean.builder()
                .setItemType(ItemType.DETAIL_INFO)
                .withId(1)
                .withTitle("毕业时间")
                .withContext(eduInfo.getMEndTime())
                .build();

        EduBean company = EduBean.builder()
                .setItemType(ItemType.DETAIL_INFO)
                .withId(2)
                .withTitle("学校")
                .withContext(eduInfo.getMSchool())
                .build();

        EduBean job = EduBean.builder()
                .setItemType(ItemType.DETAIL_INFO)
                .withId(3)
                .withTitle("学历")
                .withContext(eduInfo.getMSchoolType())
                .build();

        EduBean des = EduBean.builder()
                .setItemType(ItemType.DETAIL_INFO)
                .withId(4)
                .withTitle("专业")
                .withContext(eduInfo.getMPro())
                .build();

        beanList.add(startTime);
        beanList.add(endTime);
        beanList.add(company);
        beanList.add(job);
        beanList.add(des);
    }


    @Override
    public Object getLayout() {
        return R.layout.delegate_edu_modify;
    }

    @Override
    public void onBindView() {

        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        adapter = new EduInfoAdapter(beanList);
        mRecyclerView.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 5));
        mRecyclerView.setAdapter(adapter);
        adapter.setInfoClickListener(this);
    }

    @Override
    public void onItemClick(int position) {

    }
}
