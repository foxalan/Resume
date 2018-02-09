package com.example.alan.resume.delegate.detail;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.example.alan.resume.R;
import com.example.alan.resume.application.Resume;
import com.example.alan.resume.base.ResumeDelegate;
import com.example.alan.resume.callback.CallbackManager;
import com.example.alan.resume.callback.CallbackType;
import com.example.alan.resume.callback.IGlobalCallback;
import com.example.alan.resume.database.DatabaseManager;
import com.example.alan.resume.delegate.HomeDelegate;
import com.example.alan.resume.entity.UserInfo;
import com.example.alan.resume.loading.LatteLoader;

import java.io.File;
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
    @BindView(R.id.iv_user_icon)
    AppCompatImageView mIvUser;

    String mStrUserName;
    String mStrUserPhone;
    String mStrUserAge;
    String mStrUserExp;
    String mIconPath = null;

    @OnClick({R.id.bt_save_user_info, R.id.iv_user_icon})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_save_user_info:
                if (checkForm()) {
                    //todo
                    UserInfo info = UserInfo.builder()
                            .setId(0)
                            .setPicPath(mIconPath)
                            .setName(mStrUserName)
                            .setAge(Integer.parseInt(mStrUserAge))
                            .setExperience(mStrUserExp)
                            .setPhone(mStrUserPhone)
                            .build();
                    DatabaseManager.getInstance().getUseInfoDao().update(info);
                    LatteLoader.showLoading(getContext());
                    Resume.getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            start(new HomeDelegate(),SINGLETASK);
                            LatteLoader.stopLoading();
                        }
                    },1000);
                }
                break;
            case R.id.iv_user_icon:
                CallbackManager.getInstance()
                        .addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                            @Override
                            public void executeCallback(Uri args) {
                                String sdcardDir = Environment.getExternalStorageDirectory().getPath();
                                String dir = sdcardDir + "/" + "crop_image" + "/";
                                File file = new File(dir);
                                int size = file.list().length;
                                mIconPath = dir + file.list()[size - 1];
                                Glide.with(getContext())
                                        .load(args)
                                        .into(mIvUser);
                            }
                        });
                startCameraWithCheck();
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
                Glide.with(getContext()).load(new File(userInfoList.get(0).getMPicPath())).into(mIvUser);
            }
        }
    }

    private int phoneCount = 11;

    boolean checkForm() {
        boolean isPass = true;
        mStrUserAge = mInputUserAge.getText().toString();
        mStrUserName = mInputUserName.getText().toString();
        mStrUserPhone = mInputUserPhone.getText().toString();
        mStrUserExp = mInputUserExp.getText().toString();

        if (mStrUserName.isEmpty()) {
            mInputUserName.setError("请输入Name");
            isPass = false;
        } else {
            mInputUserName.setError(null);
        }

        if (mStrUserPhone.isEmpty() || mStrUserPhone.length() != phoneCount) {
            mInputUserPhone.setError("请输入正确的电话号码");
            isPass = false;
        } else {
            mInputUserPhone.setError(null);
        }

        if (mStrUserAge.isEmpty()) {
            mInputUserAge.setError("请输入年龄");
            isPass = false;
        } else {
            mInputUserAge.setError(null);
        }

        if (mStrUserExp.isEmpty()) {
            mInputUserExp.setError("请输入Exp");
            isPass = false;
        } else {
            mInputUserExp.setError(null);
        }

        return isPass;

    }
}
