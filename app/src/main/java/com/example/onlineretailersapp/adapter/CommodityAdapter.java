package com.example.onlineretailersapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.onlineretailersapp.R;
import com.example.onlineretailersapp.activity.CommodityDetailsActivity;
import com.example.onlineretailersapp.bean.CommodityDetailsBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * author:${张四佟}
 * date:2019/2/21 13:43
 * description
 */
public class CommodityAdapter extends RecyclerView.Adapter<CommodityAdapter.ViewHolder>{
    private Context contextcontext;
    private CommodityDetailsBean.ResultBean resultBeans;
    public <T> CommodityAdapter(CommodityDetailsActivity<T> tCommodityDetailsActivity) {
        this.contextcontext = tCommodityDetailsActivity;
    }
    public void setData(CommodityDetailsBean.ResultBean data) {
        this.resultBeans = data;
    }

    @NonNull
    @Override
    public CommodityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (i == 0) {
            View view = LayoutInflater.from(contextcontext).inflate(R.layout.commodity_buju_goods, viewGroup, false);
            return new ViewHolder(view);
        } else if (i == 1) {
            View view = LayoutInflater.from(contextcontext).inflate(R.layout.commodity_buju_details, viewGroup, false);
            return new ViewHolder(view);
        } else if (i == 2) {
            View view = LayoutInflater.from(contextcontext).inflate(R.layout.commodity_buju_webview, viewGroup, false);
            return new ViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CommodityAdapter.ViewHolder viewHolder, int i) {
        int type = getItemViewType(i);
        switch (type) {
            case 0:
                String[] image = resultBeans.getPicture().split(",");
                List<String> images = new ArrayList<>();
                for (int j = 0; j < image.length; j++) {
                    images.add(image[j]);
                }
                viewHolder.detailsFly.setImagesUrl(images);
                viewHolder.detailsPrice.setText("￥:"+resultBeans.getPrice());
                viewHolder.detailsSold.setText("已售"+resultBeans.getSaleNum()+"件");
                viewHolder.detailsIntroduce.setText(resultBeans.getCategoryName());
                viewHolder.detailsWeight.setText("重量  "+resultBeans.getWeight());
                break;
            case 1:
                viewHolder.web.loadDataWithBaseURL(null,"<html><head><title> 欢迎您 </title></head>" +
                        "<body><h2>"+resultBeans.getDescribe()+"</h2></body></html>","text/html" , "utf-8", null);
                WebSettings settings = viewHolder.web.getSettings();
                settings.setJavaScriptEnabled(true);

                break;
            case 2:
                viewHolder.addToCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(addToCart!=null){
                            addToCart.clickAdd(resultBeans.getCommodityId());
                        }
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        int entryType = 0;
        switch (position%3){
            case 1:
                entryType = 1;
                break;
            case 2:
                entryType = 2;
                break;
        }
        return entryType;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final WebView web;
        private final FlyBanner detailsFly;
        private final TextView detailsPrice,detailsSold,detailsIntroduce,detailsWeight;
        private final LinearLayout addToCart;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            web = itemView.findViewById(R.id.web);
            detailsFly = itemView.findViewById(R.id.details_fly);
            detailsPrice = itemView.findViewById(R.id.details_price);
            detailsSold = itemView.findViewById(R.id.details_sold);
            detailsIntroduce = itemView.findViewById(R.id.details_introduce);
            detailsWeight = itemView.findViewById(R.id.details_weight);
            addToCart = itemView.findViewById(R.id.add_to_cart);
        }
    }

    public interface AddToCart{
        void clickAdd(int goodsId);
    }

    public AddToCart addToCart;

    public void setAddToCart(AddToCart addToCart) {
        this.addToCart = addToCart;
    }
}
