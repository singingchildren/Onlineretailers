package com.example.onlineretailersapp.mainfragment;


import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.onlineretailersapp.R;
import com.example.onlineretailersapp.adapter.CircleAdapter;
import com.example.onlineretailersapp.base.BaseFragment;
import com.example.onlineretailersapp.bean.AddGreat;
import com.example.onlineretailersapp.bean.CircleBean;
import com.example.onlineretailersapp.bean.QxGreat;
import com.example.onlineretailersapp.network.Contacts;
import com.example.onlineretailersapp.prsenter.IPrsenterImpl;
import com.example.onlineretailersapp.utils.SPFUtil;
import com.example.onlineretailersapp.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class CircleFragment<T> extends BaseFragment implements IView<T>,XRecyclerView.LoadingListener {

    private XRecyclerView circleRecy;
    private IPrsenterImpl iPrsenter;
   // int count = 10;
    int page = 1;
    private List<CircleBean.ResultBean> beanList = new ArrayList<>();
    private CircleAdapter circleAdapter;
    private String userId;
    private String sessionId;
    private Map<String, String> map;
    private int i,j;
    @Override
    protected View getLayoutId(LayoutInflater inflater, ViewGroup container) {
        SPFUtil spfUtil = new SPFUtil();
        userId = spfUtil.getString(getContext(), "userId", "");
        sessionId = spfUtil.getString(getContext(), "sessionId", "");

        return inflater.inflate(R.layout.fragment_circle, null);
    }

    @Override
    protected void findViewById(View getid) {
        circleRecy = getid.findViewById(R.id.circleRecy);
        iPrsenter = new IPrsenterImpl(this);
        circleAdapter = new CircleAdapter(getContext());
        circleRecy.setLoadingListener(this);
        loadData();

        circleAdapter.setIsDianZan(new CircleAdapter.IsDianZan() {
            @Override
            public void cancelPoints(int qId,int i) {
                i = i;
                Map<String,String> idMap = new HashMap<>();
                idMap.put("circleId",qId+"");
                iPrsenter.startRequest(Contacts.ADDGREAT_URL, idMap, AddGreat.class, 7, userId, sessionId);
            }

            @Override
            public void addGrzan(int qId,int j) {
                j = j;
                Map<String,String> idMap = new HashMap<>();
                idMap.put("circleId",qId+"");
                iPrsenter.startRequest(Contacts.CENENLGREAT_URL, idMap, AddGreat.class, 8, userId, sessionId);
            }
        });
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();

    }

    @Override
    public void onSueecss(T data) {
        if (data instanceof CircleBean) {
            final CircleBean circleBean = (CircleBean) data;
            if (page==1){
                beanList.addAll(circleBean.getResult());
                circleAdapter.setData(beanList);
                circleRecy.setLayoutManager(new LinearLayoutManager(getContext()));
                circleRecy.setAdapter(circleAdapter);
            }else{
                circleAdapter.addData(circleBean.getResult());
            }
            page++;
            /*if (circleBean.getResult().size()<10){
                circleRecy.setLoadingMoreEnabled(false);
            }*/


        }/*else if(data instanceof AddGreat){
            AddGreat addGreat = (AddGreat) data;
            if (addGreat.getStatus().equals("0000")){
                //circleAdapter.notifyItemChanged(i);
            }
        }else if(data instanceof QxGreat){
            QxGreat qxGreat = (QxGreat) data;
            if (qxGreat.getStatus().equals("0000")){
               // circleAdapter.notifyItemChanged(j);
            }
        }*/

    }



    @Override
    public void onError(T error) {

    }
    private void loadData() {
        map = new HashMap<>();
        map.put("page", page+"");
        map.put("count","10");
        iPrsenter.startRequest(Contacts.CIRCLE_LIST_URL, map, CircleBean.class, 5, userId, sessionId);
    }


    @Override
    public void onRefresh() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                beanList.clear();
                page=1;
                loadData();
                circleRecy.refreshComplete();
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadData();
                circleRecy.loadMoreComplete();
            }
        }, 2000);
    }
}
