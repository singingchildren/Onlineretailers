package com.example.onlineretailersapp.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineretailersapp.R;

import static android.content.ContentValues.TAG;

/**
 * author:${张四佟}
 * date:2019/2/14 8:51
 * description
 */
public abstract class BaseFragment extends Fragment {
    // 标志位，标志已经初始化完成，因为setUserVisibleHint是在onCreateView之前调用的，
    // 在视图未初始化的时候，在lazyLoad当中就使用的话，就会有空指针的异常
    private boolean isPrepared;
    //标志当前页面是否可见
    private boolean isVisible;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return init(inflater, container);
    }

    private View init(LayoutInflater inflater, ViewGroup container) {
        View getid = getLayoutId(inflater, container);
        if (getid != null) {
            findViewById(getid);

        }
        return getid;
    }


    protected abstract View getLayoutId(LayoutInflater inflater, ViewGroup container);

    protected abstract void findViewById(View getid);


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isPrepared = true;
        lazyLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //懒加载
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected void lazyLoad() {
        if (!isVisible || !isPrepared) {
            return;
        }
        //getData();//数据请求
    }

    protected void onInvisible() {
    }
}















    /*private boolean isViewPrepared; // 标识fragment视图已经初始化完毕
    private boolean hasFetchData; // 标识已经触发过懒加载数据

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isViewPrepared = true;
        return init(inflater, container);
    }

    private View init(LayoutInflater inflater, ViewGroup container) {
        View getid = getLayoutId(inflater, container);
        if (getid != null) {
            findViewById(getid);
            lazyFetchDataIfPrepared();
        }
        return getid;
    }

    protected abstract View getLayoutId(LayoutInflater inflater, ViewGroup container);

    protected abstract void findViewById(View getid);

        *//**
 * 懒加载的方式获取数据，仅在满足fragment可见和视图已经准备好的时候调用一次
 *//*
    protected void lazyFetchData() {
        Log.v(TAG, getClass().getName() + "------>lazyFetchData");
    }

    private void lazyFetchDataIfPrepared() {
        // 用户可见fragment && 没有加载过数据 && 视图已经准备完毕
        if (getUserVisibleHint() && !hasFetchData && isViewPrepared) {
            hasFetchData = true; //已加载过数据
            lazyFetchData();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.v(TAG, getClass().getName() + "------>isVisibleToUser = " +               isVisibleToUser);
        if (isVisibleToUser) {//当当前为显示页面时
            lazyFetchDataIfPrepared();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hasFetchData = false;
        isViewPrepared = false;
    }*/

