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
public class PzshAdapter extends RecyclerView.Adapter<PzshAdapter.ViewHolder> {
    private Context context;
    private List<MainGoodsBean.ResultBean.PzshBean.CommodityListBeanX> pzshBean = new ArrayList<>();

    public PzshAdapter(Context context, List<MainGoodsBean.ResultBean.PzshBean.CommodityListBeanX> pzsh) {
        this.context = context;
        this.pzshBean = pzsh;
    }


    @NonNull
    @Override
    public PzshAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.pzsh_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PzshAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.pzshImage.setImageURI(pzshBean.get(i).getMasterPic());
        viewHolder.pzshTitle.setText(pzshBean.get(i).getCommodityName());
        viewHolder.pzshPrice.setText("￥"+pzshBean.get(i).getPrice()+".0");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(pzshBean.get(i).getCommodityId()+"");
                context.startActivity(new Intent(context, CommodityDetailsActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return pzshBean.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView pzshImage;
        private final TextView pzshTitle;
        private final TextView pzshPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pzshImage = itemView.findViewById(R.id.pzsh_image);
            pzshTitle = itemView.findViewById(R.id.pzsh_title);
            pzshPrice = itemView.findViewById(R.id.pzsh_price);
        }
    }
}
