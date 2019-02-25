package com.example.onlineretailersapp.mainfragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineretailersapp.R;
import com.example.onlineretailersapp.activity.SearchActivity;
import com.example.onlineretailersapp.adapter.HomePageAdapter;
import com.example.onlineretailersapp.adapter.OneListAdapter;
import com.example.onlineretailersapp.adapter.TwoListAdapter;
import com.example.onlineretailersapp.base.BaseFragment;
import com.example.onlineretailersapp.bean.BannerBean;
import com.example.onlineretailersapp.bean.MainGoodsBean;
import com.example.onlineretailersapp.bean.OneListBean;
import com.example.onlineretailersapp.bean.TwoListBean;
import com.example.onlineretailersapp.network.Contacts;
import com.example.onlineretailersapp.prsenter.IPrsenterImpl;
import com.example.onlineretailersapp.utils.IsHaveNetWork;
import com.example.onlineretailersapp.view.IView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePageFragment<T> extends BaseFragment implements IView<T>,View.OnClickListener{

    private RecyclerView mainRecy,firstLevelListRecy,twoLevelListRecy;
    private HomePageAdapter homePageAdapter;
    private IPrsenterImpl iPrsenter;
    private ImageView menuImg;
    private EditText editSs;
    private TextView textSs;
    private TextView textSs1;
    private boolean oneIsShow = true;
    private LinearLayout li;
    private OneListAdapter oneListAdapter;

    @Override
    protected View getLayoutId(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_home_page,null);
    }

    @Override
    protected void findViewById(View getid) {
        mainRecy = getid.findViewById(R.id.main_Recy);
        editSs = getid.findViewById(R.id.edit_ss);
        textSs = getid.findViewById(R.id.text_ss);
        menuImg = getid.findViewById(R.id.menuImg);
        li = getid.findViewById(R.id.li);
        firstLevelListRecy = getid.findViewById(R.id.first_level_list_recy);
        twoLevelListRecy = getid.findViewById(R.id.two_level_list_recy);
        homePageAdapter = new HomePageAdapter(getActivity());
        iPrsenter = new IPrsenterImpl(this);
        if(IsHaveNetWork.isNetworkConnected(getContext())){
            iPrsenter.startRequest(Contacts.BANNER_URL, null,BannerBean.class,4,null,null);

        }else{
            Toast.makeText(getContext(), "亲 是不是没有网了呢", Toast.LENGTH_SHORT).show();
        }

        menuImg.setOnClickListener(this);

    }

    private void oneListItemClick() {
        oneListAdapter.setClickItemListenner(new OneListAdapter.ClickItemListenner() {
            @Override
            public void clickItem(String id) {
                if (id==null){
                    twoLevelListRecy.setVisibility(View.GONE);
                }else{
                    twoLevelListRecy.setVisibility(View.VISIBLE);
                    Map<String,String> map = new HashMap<>();
                    map.put("firstCategoryId",id+"");
                    iPrsenter.startRequest(Contacts.TWO_LIST_URL, map,TwoListBean.class,12,null,null);
                }


            }
        });
    }

    @Override
    public void onSueecss(T data) {
        if (data instanceof BannerBean) {
            BannerBean bannerBean = (BannerBean) data;
            homePageAdapter.setBannerData(bannerBean.getResult());
            iPrsenter.startRequest(Contacts.GOODS_URL,null,MainGoodsBean.class,3,null,null);

        }else if (data instanceof MainGoodsBean){
            MainGoodsBean goodsBean = (MainGoodsBean) data;
            homePageAdapter.setGoodsData(goodsBean.getResult());
            mainRecy.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            mainRecy.setAdapter(homePageAdapter);
        }else if (data instanceof OneListBean){
            OneListBean oneListBean = (OneListBean) data;
            oneListAdapter = new OneListAdapter(getContext(),oneListBean.getResult());
            firstLevelListRecy.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            firstLevelListRecy.setAdapter(oneListAdapter);
            oneListItemClick();
        }else if (data instanceof TwoListBean){
            TwoListBean twoListBean = (TwoListBean) data;
            TwoListAdapter twoListAdapter = new TwoListAdapter(getContext(),twoListBean.getResult());
            twoLevelListRecy.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            twoLevelListRecy.setAdapter(twoListAdapter);
        }


    }

    @Override
    public void onError(T error) {

    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menuImg:
                if (oneIsShow){
                    oneIsShow = false;
                    iPrsenter.startRequest(Contacts.ONE_LIST_URL, null,OneListBean.class,11,null,null);
                    li.setVisibility(View.VISIBLE);
                }else{
                    li.setVisibility(View.GONE);
                    oneIsShow = true;
                    twoLevelListRecy.setVisibility(View.GONE);
                }
                break;
            case R.id.text_ss:
                String editSsStr = editSs.getText().toString().trim();
                EventBus.getDefault().postSticky(editSsStr+"");
                startActivity(new Intent(getContext(), SearchActivity.class));
                break;
        }
    }
}
