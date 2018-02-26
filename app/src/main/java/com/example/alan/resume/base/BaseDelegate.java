package com.example.alan.resume.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Function :
 * Modify Date : 2018/2/1
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public abstract class BaseDelegate extends SwipeBackFragment {

    /**
     * 得到布局
     *
     * @return
     */
    public abstract Object getLayout();

    /**
     * 處理事件
     */
    public abstract void onBindView();

    public Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView;

        if (getLayout() instanceof Integer) {
            rootView = inflater.inflate((Integer) getLayout(), container, false);
        } else if (getLayout() instanceof View) {
            rootView = (View) getLayout();
        } else {
            throw new RuntimeException("the rootView type is error");
        }

        if (rootView != null) {
            unbinder = ButterKnife.bind(this, rootView);
            onBindView();
        }

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
