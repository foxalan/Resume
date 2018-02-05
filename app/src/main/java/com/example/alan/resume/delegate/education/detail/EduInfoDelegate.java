package com.example.alan.resume.delegate.education.detail;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.alan.resume.R;
import com.example.alan.resume.base.ResumeDelegate;
import com.example.alan.resume.recycler.ItemType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Function :
 * Modify Date : 2018/2/5
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class EduInfoDelegate extends ResumeDelegate {

    @BindView(R.id.ryc_edu_add)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_education_info_save)
    AppCompatTextView mTvSaveInfo;

    private List<EduBean> eduBeanList = new ArrayList<>();

    @Override
    public Object getLayout() {
        return R.layout.delegate_edu_info;
    }

    @Override
    public void onBindView() {
        initData();
        initRecyclerView();
    }

    private void initData() {
        EduBean startTime = EduBean.builder()
                .setItemType(ItemType.EDU_DETAIL_INFO)
                .withId(0)
                .withTitle("start time")
                .withContext("")
                .build();
        EduBean endTime = EduBean.builder()
                .setItemType(ItemType.EDU_DETAIL_INFO)
                .withId(1)
                .withTitle("end time")
                .withContext("")
                .build();
        EduBean school = EduBean.builder()
                .setItemType(ItemType.EDU_DETAIL_INFO)
                .withId(2)
                .withTitle("school")
                .withContext("")
                .build();
        EduBean schoolType = EduBean.builder()
                .setItemType(ItemType.EDU_DETAIL_INFO)
                .withId(3)
                .withTitle("school type")
                .withContext("")
                .build();
        EduBean pro = EduBean.builder()
                .setItemType(ItemType.EDU_DETAIL_INFO)
                .withId(4)
                .withTitle("pro")
                .withContext("")
                .build();
        eduBeanList.add(startTime);
        eduBeanList.add(endTime);
        eduBeanList.add(school);
        eduBeanList.add(schoolType);
        eduBeanList.add(pro);

    }

    private void initRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        EduInfoAdapter adapter = new EduInfoAdapter(eduBeanList);
        mRecyclerView.setAdapter(adapter);
    }
}
