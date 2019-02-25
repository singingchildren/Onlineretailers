package com.example.onlineretailersapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.onlineretailersapp.R;
import com.example.onlineretailersapp.bean.BannerBean;
import com.example.onlineretailersapp.bean.MainGoodsBean;
import com.example.onlineretailersapp.home_adapter.MlssAdapter;
import com.example.onlineretailersapp.home_adapter.PzshAdapter;
import com.example.onlineretailersapp.home_adapter.RxxpAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import recycler.coverflow.RecyclerCoverFlow;

/**
 * author:${张四佟}
 * date:2019/2/14 18:07
 * description
 */
public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.ViewHolder> {

    private final int ITEMCOUNT = 4;
    private final int BANNERTYPE = 0;
    private final int RXXPTYPE = 1;
    private final int MLSSTYPE = 2;
    private final int PZSHTYPE = 3;
    private Context context;

    private MainGoodsBean.ResultBean list;
    private List<BannerBean.ResultBean> bannerData = new ArrayList<>();
    private List<String> bannerList;

    public HomePageAdapter(FragmentActivity activity) {
        this.context = activity;
    }

    public void setGoodsData(MainGoodsBean.ResultBean result) {
        this.list = result;
        notifyDataSetChanged();
    }

    public void setBannerData(List<BannerBean.ResultBean> bannerData) {
        this.bannerData = bannerData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomePageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 0) {
            View view = LayoutInflater.from(context).inflate(R.layout.activity_banner, viewGroup, false);
            return new ViewHolder(view);
        } else if (i == 1) {
            View view = LayoutInflater.from(context).inflate(R.layout.fragment_rxxp, viewGroup, false);
            return new ViewHolder(view);
        } else if (i == 2) {
            View view = LayoutInflater.from(context).inflate(R.layout.fragment_mlss, viewGroup, false);
            return new ViewHolder(view);
        } else if (i == 3) {
            View view = LayoutInflater.from(context).inflate(R.layout.fragment_pzsh, viewGroup, false);
            return new ViewHolder(view);

        }
        Log.e("111", "onCreateViewHolder: ");
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull HomePageAdapter.ViewHolder viewHolder, int i) {
        int type = getItemViewType(i);
        switch (type) {
            case 0:
                bannerList = new ArrayList<>();
                for (int j = 0; j < bannerData.size(); j++) {
                    bannerList.add(bannerData.get(j).getImageUrl());
                }
                viewHolder.banner.setPages(bannerList, new MZHolderCreator<BannerViewHolder>() {
                    @Override
                    public BannerViewHolder createViewHolder() {
                        return new BannerViewHolder();
                    }
                });
                break;
            case 1:
                List<MainGoodsBean.ResultBean.RxxpBean.CommodityListBean> rxxpList = list.getRxxp().get(0).getCommodityList();
                viewHolder.rxxpRecy.setAdapter(new RxxpAdapter(context, rxxpList));
                viewHolder.rxxpRecy.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
                /**/
                break;
            case 2:
                List<MainGoodsBean.ResultBean.MlssBean.CommodityListBeanXX> mlssList = list.getMlss().get(0).getCommodityList();
                viewHolder.mlssRecy.setAdapter(new MlssAdapter(context, mlssList));
                viewHolder.mlssRecy.setLayoutManager(new LinearLayoutManager(context));
                break;
            case 3:
                List<MainGoodsBean.ResultBean.PzshBean.CommodityListBeanX> pzshList = list.getPzsh().get(0).getCommodityList();
                viewHolder.pzshRecy.setAdapter(new PzshAdapter(context, pzshList));
                viewHolder.pzshRecy.setLayoutManager(new GridLayoutManager(context,2));
                break;
        }
        Log.e("111", "onBindViewHolder: " );
    }

    @Override
    public int getItemViewType(int position) {
        int entryType = BANNERTYPE;
        switch (position%ITEMCOUNT){
            case RXXPTYPE:
                entryType = RXXPTYPE;
                break;
            case MLSSTYPE:
                entryType = MLSSTYPE;
                break;
            case PZSHTYPE:
                entryType = PZSHTYPE;
                break;
        }
        Log.e("111", "getItemViewType: ");
        return entryType;
    }

    @Override
    public int getItemCount() {
        return ITEMCOUNT;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private final RecyclerView rxxpRecy,pzshRecy,mlssRecy;
        private final MZBannerView banner;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rxxpRecy = itemView.findViewById(R.id.rxxp_Recy);
            pzshRecy = itemView.findViewById(R.id.pzsh_Recy);
            mlssRecy = itemView.findViewById(R.id.mlss_Recy);
            banner = itemView.findViewById(R.id.bannert);
        }
    }

    public class BannerViewHolder implements MZViewHolder<String> {
        private SimpleDraweeView mImageView;
        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.banner_img_layout,null);
            mImageView = view.findViewById(R.id.banner_img);
            return view;
        }

        @Override
        public void onBind(Context context, int i, String s) {
            mImageView.setImageURI(s);
        }
    }
}
