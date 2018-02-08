package com.example.alan.resume.delegate.pro.detail;

import android.app.Dialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.alan.resume.R;
import com.example.alan.resume.application.Resume;
import com.example.alan.resume.base.ResumeDelegate;
import com.example.alan.resume.database.ProOpenHelper;
import com.example.alan.resume.delegate.edu.EduDelegate;
import com.example.alan.resume.delegate.pro.ProDelegate;
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
 * Modify Date : 2018/2/8
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class ProInfoDelegate extends ResumeDelegate {


    @BindView(R.id.ryc_pro_add)
    RecyclerView mRecyclerView;

    private List<ProBean> proBeanList = new ArrayList<>();
    private Dialog dateDialog, chooseDialog;
    private ProInfoAdapter adapter;
    private List<String> listSchool, listSchoolType, listPro;

    @OnClick({R.id.ict_pro_item_back, R.id.tv_pro_info_save})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.ict_pro_item_back:
                start(EduDelegate.getInstance(),SINGLETASK);
                break;
            case R.id.tv_pro_info_save:
                if (isConfirm()){
                    ProOpenHelper.getInstance().insert(proBeanList);
                    LatteLoader.showLoading(getContext());
                    Resume.getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // need to fix

                            start(new ProDelegate(),SINGLETASK);
                            LatteLoader.stopLoading();
                        }
                    },1000);
                }else {
                    Toast.makeText(getContext(),"信息未填全",Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    private boolean isConfirm() {
        for (ProBean bean:proBeanList){
            if ("".equals(bean.getmContext())){
                return false;
            }
        }

        return true;
    }

    @Override
    public Object getLayout() {
        return R.layout.delegate_pro_info;
    }

    @Override
    public void onBindView() {
        initData();
        initRecyclerView();
    }

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

        ProBean startTime = ProBean.builder()
                .setItemType(ItemType.DETAIL_INFO)
                .withId(0)
                .withTitle("开始时间")
                .withContext("")
                .build();
        ProBean endTime = ProBean.builder()
                .setItemType(ItemType.DETAIL_INFO)
                .withId(1)
                .withTitle("结束时间")
                .withContext("")
                .build();
        ProBean school = ProBean.builder()
                .setItemType(ItemType.DETAIL_INFO)
                .withId(2)
                .withTitle("项目名称")
                .withContext("")
                .build();
        ProBean schoolType = ProBean.builder()
                .setItemType(ItemType.DETAIL_INFO)
                .withId(3)
                .withTitle("项目描述")
                .withContext("")
                .build();

        proBeanList.add(startTime);
        proBeanList.add(endTime);
        proBeanList.add(school);
        proBeanList.add(schoolType);


    }

    private void initRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        adapter = new ProInfoAdapter(proBeanList);
        mRecyclerView.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 5));
        mRecyclerView.setAdapter(adapter);

        adapter.setInfoPositionClickListener(new IProInfoPositionClickListener() {
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
                proBeanList.get(position).setmContext(date);
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
                        proBeanList.get(position).setmContext(itemValue);
                        adapter.notifyItemChanged(position);
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }
}
