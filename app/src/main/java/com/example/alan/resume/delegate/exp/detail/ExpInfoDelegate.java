package com.example.alan.resume.delegate.exp.detail;

import android.app.Dialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.alan.resume.R;
import com.example.alan.resume.application.Resume;
import com.example.alan.resume.base.ResumeDelegate;
import com.example.alan.resume.database.ExpOpenHelper;
import com.example.alan.resume.delegate.edu.detail.IEduInfoClickListener;
import com.example.alan.resume.delegate.exp.ExpDelegate;
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
 * Modify Date : 2018/2/5
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class ExpInfoDelegate extends ResumeDelegate {

    @BindView(R.id.ryc_exp_add)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_exp_info_save)
    AppCompatTextView mTvSaveInfo;

    @OnClick({R.id.tv_exp_info_save,R.id.itv_exp_cancel})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_exp_info_save:
                if (isConfirm()){
                    ExpOpenHelper.getInstance().insert(expBeanList);
                    LatteLoader.showLoading(getContext());
                    Resume.getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // need to fix

                            start(new ExpDelegate(),SINGLETASK);
                            LatteLoader.stopLoading();
                        }
                    },1000);
                }else {
                    Toast.makeText(getContext(),"信息未填全",Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.itv_exp_cancel:

                start(ExpDelegate.getInstance(),SINGLETASK);
                break;
            default:
                break;
        }
    }

    private boolean isConfirm() {
        for (ExpBean bean:expBeanList){
            if ("".equals(bean.getmContext())){
                return false;
            }
        }

        return true;
    }

    private List<ExpBean> expBeanList = new ArrayList<>();
    private Dialog dateDialog, chooseDialog;
    private ExpInfoAdapter adapter;
    private List<String> listSchool, listSchoolType, listPro;

    @Override
    public Object getLayout() {
        return R.layout.delegate_exp_info;
    }

    @Override
    public void onBindView() {
        initData();
        initRecyclerView();
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
                .withContext("")
                .build();
        ExpBean endTime = ExpBean.builder()
                .setItemType(ItemType.DETAIL_INFO)
                .withId(1)
                .withTitle("离职时间")
                .withContext("")
                .build();
        ExpBean school = ExpBean.builder()
                .setItemType(ItemType.DETAIL_INFO)
                .withId(2)
                .withTitle("公司")
                .withContext("")
                .build();
        ExpBean schoolType = ExpBean.builder()
                .setItemType(ItemType.DETAIL_INFO)
                .withId(3)
                .withTitle("职位")
                .withContext("")
                .build();
        ExpBean pro = ExpBean.builder()
                .setItemType(ItemType.DETAIL_INFO)
                .withId(4)
                .withTitle("工作描述")
                .withContext("")
                .build();
        expBeanList.add(startTime);
        expBeanList.add(endTime);
        expBeanList.add(school);
        expBeanList.add(schoolType);
        expBeanList.add(pro);

    }

    private void initRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        adapter = new ExpInfoAdapter(expBeanList);
        mRecyclerView.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 5));
        mRecyclerView.setAdapter(adapter);

        adapter.setInfoClickListener(new IEduInfoClickListener() {
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
        });

    }

    private void showDateDialog(List<Integer> date, final int position) {
        DatePickerDialog.Builder builder = new DatePickerDialog.Builder(getContext());
        builder.setOnDateSelectedListener(new DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int[] dates) {
                String date = dates[0] + "-" + (dates[1] > 9 ? dates[1] : ("0" + dates[1])) + "-"
                        + (dates[2] > 9 ? dates[2] : ("0" + dates[2]));
                expBeanList.get(position).setmContext(date);
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
                        expBeanList.get(position).setmContext(itemValue);
                        adapter.notifyItemChanged(position);
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }
}
