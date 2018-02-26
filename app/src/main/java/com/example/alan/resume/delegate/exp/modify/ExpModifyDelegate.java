package com.example.alan.resume.delegate.exp.modify;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.alan.resume.R;
import com.example.alan.resume.application.Resume;
import com.example.alan.resume.base.ResumeDelegate;
import com.example.alan.resume.database.DatabaseManager;
import com.example.alan.resume.database.ExpOpenHelper;
import com.example.alan.resume.delegate.edu.detail.IEduInfoClickListener;
import com.example.alan.resume.delegate.exp.ExpDelegate;
import com.example.alan.resume.delegate.exp.des.ExpDesDelegate;
import com.example.alan.resume.delegate.exp.detail.ExpBean;
import com.example.alan.resume.delegate.exp.detail.ExpInfoAdapter;
import com.example.alan.resume.entity.ExpInfo;
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
 * Modify Date : 2018/2/6
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class ExpModifyDelegate extends ResumeDelegate implements IEduInfoClickListener {

    private ExpInfoAdapter adapter;

    @BindView(R.id.ryc_edu_modify)
    RecyclerView mRecyclerView;
    private ExpInfo expInfo;
    private List<ExpBean> beanList = new ArrayList<>();
    private Dialog dateDialog, chooseDialog;
    private List<String> listSchool, listSchoolType, listPro;

    @OnClick({R.id.ict_modify_back_exp, R.id.tv_exp_info_modify})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.ict_modify_back_exp:
                start(new ExpDelegate(),SINGLETASK);
                break;
            case R.id.tv_exp_info_modify:
                saveInfo();
                ExpOpenHelper.getInstance().update(expInfo);
                LatteLoader.showLoading(getContext());
                Resume.getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        start(new ExpDelegate(),SINGLETASK);
                        LatteLoader.stopLoading();
                    }
                },1000);
                break;
            default:
        }
    }

    private void saveInfo() {

        expInfo.setStartTime(beanList.get(0).getmContext());
        expInfo.setEndTime(beanList.get(1).getmContext());
        expInfo.setCompany(beanList.get(2).getmContext());
        expInfo.setJob(beanList.get(3).getmContext());
        expInfo.setWorkDes(beanList.get(4).getmContext());

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = ExpDelegate.getInstance().getArguments();
        long id = bundle.getLong("exp_id");
        expInfo = DatabaseManager.getInstance().getExpDao().load(id);
        Log.e("huiye",expInfo.toString());
        initData();
    }

    private void initData() {

        listSchool = new ArrayList<>();
        for (String str : getContext().getResources().getStringArray(R.array.company)) {
            listSchool.add(str);
        }

        listSchoolType = new ArrayList<>();
        for (String str : getContext().getResources().getStringArray(R.array.job)) {
            listSchoolType.add(str);
        }

        listPro = new ArrayList<>();
        for (String str : getContext().getResources().getStringArray(R.array.jobdes)) {
            listPro.add(str);
        }

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
                .setItemType(ItemType.EXP_INFO_DES)
                .withId(4)
                .withTitle("工作描述")
                .withContext(expInfo.getWorkDes())
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
                start(new ExpDesDelegate(),SINGLETASK);
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
