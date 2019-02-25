package com.example.onlineretailersapp.home_adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.onlineretailersapp.R;
import com.example.onlineretailersapp.activity.CommodityDetailsActivity;
import com.example.onlineretailersapp.bean.MainGoodsBean;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * author:${张四佟}
 * date:2019/2/14 20:08
 * description
 */
public class MlssAdapter extends RecyclerView.Adapter<MlssAdapter.ViewHolder> {
    private Context context;
    private List<MainGoodsBean.ResultBean.MlssBean.CommodityListBeanXX> mlssBean = new ArrayList<>();

    public MlssAdapter(Context context, List<MainGoodsBean.ResultBean.MlssBean.CommodityListBeanXX> mlss) {
        this.context = context;
        this.mlssBean = mlss;
    }


    @NonNull
    @Override
    public MlssAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.mlss_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MlssAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.mlssImage.setImageURI(mlssBean.get(i).getMasterPic());
        viewHolder.mlssTitle.setText(mlssBean.get(i).getCommodityName());
        viewHolder.mlssPrice.setText("￥"+mlssBean.get(i).getPrice()+".0");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(mlssBean.get(i).getCommodityId()+"");
                context.startActivity(new Intent(context, CommodityDetailsActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlssBean.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView mlssImage;
        private final TextView mlssTitle;
        private final TextView mlssPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mlssImage = itemView.findViewById(R.id.mlss_image);
            mlssTitle = itemView.findViewById(R.id.mlss_title);
            mlssPrice = itemView.findViewById(R.id.mlss_price);
        }
    }
}
