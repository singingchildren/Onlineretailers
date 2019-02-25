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
public class RxxpAdapter extends RecyclerView.Adapter<RxxpAdapter.ViewHolder> {
    private Context context;
    private List<MainGoodsBean.ResultBean.RxxpBean.CommodityListBean> rxxpBeans = new ArrayList<>();

    public RxxpAdapter(Context context, List<MainGoodsBean.ResultBean.RxxpBean.CommodityListBean> rxxp) {
        this.context = context;
        this.rxxpBeans = rxxp;
    }


    @NonNull
    @Override
    public RxxpAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.rxxp_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RxxpAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.rxxp_image.setImageURI(rxxpBeans.get(i).getMasterPic());
        viewHolder.rxxp_title.setText(rxxpBeans.get(i).getCommodityName());
        viewHolder.rxxp_price.setText("￥"+rxxpBeans.get(i).getPrice()+".0");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(rxxpBeans.get(i).getCommodityId()+"");
                context.startActivity(new Intent(context, CommodityDetailsActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return rxxpBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView rxxp_image;
        private final TextView rxxp_title;
        private final TextView rxxp_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rxxp_image = itemView.findViewById(R.id.rxxp_image);
            rxxp_title = itemView.findViewById(R.id.rxxp_title);
            rxxp_price = itemView.findViewById(R.id.rxxp_price);
        }
    }
}
