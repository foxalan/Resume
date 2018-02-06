package com.example.alan.resume.delegate.exp.modify;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.alan.resume.R;
import com.example.alan.resume.base.ResumeDelegate;

/**
 * Function :
 * Modify Date : 2018/2/6
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class ExpModifyDelegate extends ResumeDelegate {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        long id = bundle.getLong("exp_id");
        Log.e("tang","id==="+id);
    }

    @Override
    public Object getLayout() {
        return R.layout.delegate_exp_modify;
    }

    @Override
    public void onBindView() {

    }
}
