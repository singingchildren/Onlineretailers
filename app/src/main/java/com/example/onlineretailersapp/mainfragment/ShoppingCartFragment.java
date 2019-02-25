package com.example.onlineretailersapp.mainfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineretailersapp.R;
import com.example.onlineretailersapp.adapter.ShowCartAdapter;
import com.example.onlineretailersapp.base.BaseFragment;
import com.example.onlineretailersapp.bean.CircleBean;
import com.example.onlineretailersapp.bean.ShowCarBean;
import com.example.onlineretailersapp.network.Contacts;
import com.example.onlineretailersapp.prsenter.IPrsenterImpl;
import com.example.onlineretailersapp.utils.SPFUtil;
import com.example.onlineretailersapp.view.IView;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartFragment<T> extends BaseFragment implements IView<T>{


    private RecyclerView cartRecy;
    private String userId;
    private String sessionId;
    private IPrsenterImpl iPrsenter;
    private ShowCartAdapter showCartAdapter;
    @Override
    protected View getLayoutId(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_shopping_cart,null);
    }

    @Override
    protected void findViewById(View getid) {
        cartRecy = getid.findViewById(R.id.cartRecy);
        SPFUtil spfUtil = new SPFUtil();
        userId = spfUtil.getString(getContext(), "userId", "");
        sessionId = spfUtil.getString(getContext(), "sessionId", "");
        showCartAdapter = new ShowCartAdapter(getContext());
        iPrsenter = new IPrsenterImpl(this);
        iPrsenter.startRequest(Contacts.QU_CART_URL, null, ShowCarBean.class, 10, userId, sessionId);
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();


    }

    @Override
    public void onSueecss(T data) {
        if (data instanceof ShowCarBean){
            ShowCarBean showCarBean = (ShowCarBean) data;
            int size = showCarBean.getResult().size();
            if (size>=1){

            }else{
                showCartAdapter.setData(showCarBean.getResult());
            }
            cartRecy.setAdapter(showCartAdapter);
            cartRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }

    @Override
    public void onError(T error) {

    }
}
