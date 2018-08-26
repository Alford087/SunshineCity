package com.cc.sunshine.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cc.sunshine.core.base.Logger;

/**
 * Created by tc on 2016/1/26.
 */
public abstract class BaseFragment extends Fragment {

    protected Activity act;

    @Override
    public void onAttach(Activity activity) {
        Logger.logd(getClass().getSimpleName(), "onAttach");
        super.onAttach(activity);
        this.act = activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.logd(getClass().getSimpleName(), "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Logger.logd(getClass().getSimpleName(), "onViewCreated");
    }

    @Override
    public void onResume() {
        Logger.logd(getClass().getSimpleName(), "onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Logger.logd(getClass().getSimpleName(), "onPause");
        super.onPause();

    }

    @Override
    public void onStart() {
        Logger.logd(getClass().getSimpleName(), "onStart");
        super.onStart();
    }

    @Override
    public void onStop() {
        Logger.logd(getClass().getSimpleName(), "onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Logger.logd(getClass().getSimpleName(), "onDestroyView");
        super.onDestroyView();
    }

    public void initData(Bundle savedInstanceState) {

    }

    /**
     * fragment 名称
     *
     * @return
     */
    @StringRes
    public int getFragmentNameRes() {
        return 0;
    }
}
