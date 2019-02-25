package com.example.onlineretailersapp.mainfragment;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineretailersapp.R;
import com.example.onlineretailersapp.base.BaseFragment;

public class OrderFragment extends BaseFragment {


    @Override
    protected View getLayoutId(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_order,null);
    }

    @Override
    protected void findViewById(View getid) {

    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();


    }
}
