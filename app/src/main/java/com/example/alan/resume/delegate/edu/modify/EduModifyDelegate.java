package com.example.alan.resume.delegate.edu.modify;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.alan.resume.R;
import com.example.alan.resume.application.Resume;
import com.example.alan.resume.base.ResumeDelegate;
import com.example.alan.resume.database.DatabaseManager;
import com.example.alan.resume.database.EduOpenHelper;
import com.example.alan.resume.delegate.edu.EduDelegate;
import com.example.alan.resume.delegate.edu.detail.EduBean;
import com.example.alan.resume.delegate.edu.detail.EduInfoAdapter;
import com.example.alan.resume.delegate.edu.detail.IEduInfoClickListener;
import com.example.alan.resume.entity.EduInfo;
import com.example.alan.resume.loading.LatteLoader;
import com.example.alan.resume.picker.DataPickerDialog;
import com.example.alan.resume.picker.DatePickerDialog;
import com.example.alan.resume.picker.DateUtil;
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
    private Dialog dateDialog, chooseDialog;
    private List<String> listSchool, listSchoolType, listPro;



    @OnClick({R.id.ict_edu_modify_back, R.id.tv_edu_info_modify_save})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.ict_edu_modify_back:
                start(EduDelegate.getInstance(),SINGLETASK);
                break;
            case R.id.tv_edu_info_modify_save:
                saveInfo();
                EduOpenHelper.getInstance().update(eduInfo);
                LatteLoader.showLoading(getContext());
                Resume.getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        EduDelegate.getInstance().refresh();
                     //   HomeDelegate.getInstance().refresh(1);
                        start(new EduDelegate(),SINGLETASK);
                        LatteLoader.stopLoading();
                    }
                },1000);
                break;
            default:
                break;
        }
    }

    private void saveInfo() {
        eduInfo.setMStartTime(beanList.get(0).getmContext());
        eduInfo.setMEndTime(beanList.get(1).getmContext());
        eduInfo.setMSchool(beanList.get(2).getmContext());
        eduInfo.setMSchoolType(beanList.get(3).getmContext());
        eduInfo.setMPro(beanList.get(4).getmContext());
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
        listSchool = new ArrayList<>();
        for (String str : getContext().getResources().getStringArray(R.array.schools)) {
            listSchool.add(str);
        }

        listSchoolType = new ArrayList<>();
        for (String str : getContext().getResources().getStringArray(R.array.school_type)) {
            listSchoolType.add(str);
        }

        listPro = new ArrayList<>();
        for (String str : getContext().getResources().getStringArray(R.array.pro)) {
            listPro.add(str);
        }
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
        switch (position) {
            case 0:
                showDateDialog(DateUtil.getDateForString("2012-09-01"), 0);
                break;
            case 1:
                showDateDialog(DateUtil.getDateForString("2016-07-01"), 1);
                break;
            case 2:
                showChooseDialog(listSchool, 2);
                break;
            case 3:
                showChooseDialog(listSchoolType, 3);
                break;
            case 4:
                showChooseDialog(listPro, 4);
                break;
            default:
                break;
        }

    }


    private void showDateDialog(List<Integer> date, final int position) {
        DatePickerDialog.Builder builder = new DatePickerDialog.Builder(getContext());
        builder.setOnDateSelectedListener(new DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int[] dates) {
                String date = dates[0] + "-" + (dates[1] > 9 ? dates[1] : ("0" + dates[1])) + "-"
                        + (dates[2] > 9 ? dates[2] : ("0" + dates[2]));

                beanList.get(position).setmContext(date);
                adapter.notifyItemChanged(position);
            }

            @Override
            public void onCancel() {

            }
        })

                .setSelectYear(date.get(0) - 1)
                .setSelectMonth(date.get(1) - 1)
                .setSelectDay(date.get(2) - 1);

        builder.setMaxYear(DateUtil.getYear());
        builder.setMaxMonth(DateUtil.getDateForString(DateUtil.getToday()).get(1));
        builder.setMaxDay(DateUtil.getDateForString(DateUtil.getToday()).get(2));
        dateDialog = builder.create();
        dateDialog.show();
    }

    private void showChooseDialog(List<String> list, final int position) {
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(getContext());
        chooseDialog = builder.setData(list).setSelection(1).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int p) {
                        beanList.get(position).setmContext(itemValue);
                        adapter.notifyItemChanged(position);
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }
}
