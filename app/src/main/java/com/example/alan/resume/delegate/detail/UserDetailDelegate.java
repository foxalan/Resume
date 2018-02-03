package com.example.alan.resume.delegate.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;

import com.example.alan.resume.R;
import com.example.alan.resume.base.ResumeDelegate;
import com.example.alan.resume.database.DatabaseManager;
import com.example.alan.resume.entity.UserInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Function :
 * Modify Date : 2018/2/3
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class UserDetailDelegate extends ResumeDelegate {

    @BindView(R.id.et_user_name)
    TextInputEditText mInputUserName;
    @BindView(R.id.et_user_age)
    TextInputEditText mInputUserAge;
    @BindView(R.id.et_user_phone)
    TextInputEditText mInputUserPhone;
    @BindView(R.id.et_user_exp)
    TextInputEditText mInputUserExp;
    @BindView(R.id.bt_save_user_info)
    Button mButtonSave;

    @OnClick({R.id.bt_save_user_info})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_save_user_info:
                break;
            default:
                break;
        }

    }


    @Override
    public Object getLayout() {
        return R.layout.delegate_user_detail;
    }

    @Override
    public void onBindView() {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        List<UserInfo> userInfoList = DatabaseManager.getInstance().getUseInfoDao().loadAll();
        if (userInfoList != null) {
            if (userInfoList.size() != 0) {
                mInputUserName.setText(userInfoList.get(0).getMName());
                mInputUserPhone.setText(userInfoList.get(0).getMPhone());
                mInputUserAge.setText(String.valueOf(userInfoList.get(0).getMAge()));
                mInputUserExp.setText(userInfoList.get(0).getMExperience());
            }
        }

    }
}
