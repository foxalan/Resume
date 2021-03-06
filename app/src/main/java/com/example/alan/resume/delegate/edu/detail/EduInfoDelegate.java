package com.example.alan.resume.delegate.edu.detail;

import android.app.Dialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.alan.resume.R;
import com.example.alan.resume.application.Resume;
import com.example.alan.resume.base.ResumeDelegate;
import com.example.alan.resume.database.EduOpenHelper;
import com.example.alan.resume.delegate.edu.EduDelegate;
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
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class EduInfoDelegate extends ResumeDelegate {

    @BindView(R.id.ryc_edu_add)
    RecyclerView mRecyclerView;

    private List<EduBean> eduBeanList = new ArrayList<>();
    private Dialog dateDialog, chooseDialog;
    private EduInfoAdapter adapter;
    private List<String> listSchool, listSchoolType, listPro;

    @OnClick({R.id.ict_edu_item_back, R.id.tv_edu_info_save})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.ict_edu_item_back:
                start(EduDelegate.getInstance(),SINGLETASK);
                break;
            case R.id.tv_edu_info_save:
                if (isConfirm()){
                    EduOpenHelper.getInstance().insert(eduBeanList);
                    LatteLoader.showLoading(getContext());
                    Resume.getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                        // need to fix

                            start(new EduDelegate(),SINGLETASK);
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
        for (EduBean bean:eduBeanList){
            if ("".equals(bean.getmContext())){
                return false;
            }
        }

        return true;
    }

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
                .withContext("")
                .build();
        EduBean endTime = EduBean.builder()
                .setItemType(ItemType.DETAIL_INFO)
                .withId(1)
                .withTitle("毕业时间")
                .withContext("")
                .build();
        EduBean school = EduBean.builder()
                .setItemType(ItemType.DETAIL_INFO)
                .withId(2)
                .withTitle("学校")
                .withContext("")
                .build();
        EduBean schoolType = EduBean.builder()
                .setItemType(ItemType.DETAIL_INFO)
                .withId(3)
                .withTitle("学历")
                .withContext("")
                .build();
        EduBean pro = EduBean.builder()
                .setItemType(ItemType.DETAIL_INFO)
                .withId(4)
                .withTitle("专业")
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
        adapter = new EduInfoAdapter(eduBeanList);
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
                eduBeanList.get(position).setmContext(date);
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
                        eduBeanList.get(position).setmContext(itemValue);
                        adapter.notifyItemChanged(position);
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }


}
