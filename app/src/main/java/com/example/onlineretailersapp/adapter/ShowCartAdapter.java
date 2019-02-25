package com.example.onlineretailersapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.onlineretailersapp.R;
import com.example.onlineretailersapp.bean.ShowCarBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * author:${张四佟}
 * date:2019/2/22 19:17
 * description
 */
public class ShowCartAdapter extends RecyclerView.Adapter<ShowCartAdapter.ViewHolder> {
    private Context context;
    private List<ShowCarBean.ResultBean> resultBeans = new ArrayList<>();

    public ShowCartAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<ShowCarBean.ResultBean> data) {
        this.resultBeans = data;
        //notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShowCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.show_cart_item,null);
        return new ShowCartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowCartAdapter.ViewHolder viewHolder, int i) {
        viewHolder.cartImg.setImageURI(resultBeans.get(i).getPic());
        viewHolder.cartTitle.setText(resultBeans.get(i).getCommodityName());
        viewHolder.cartPrice.setText("￥:"+resultBeans.get(i).getPrice()+".00");
    }

    @Override
    public int getItemCount() {
        int size = resultBeans.size();
        /*if (size>=1){
            size++;
        }*/
        return size;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView cartImg;
        private final TextView cartTitle;
        private final TextView cartPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cartImg = itemView.findViewById(R.id.cart_img);
            cartTitle = itemView.findViewById(R.id.cart_title);
            cartPrice = itemView.findViewById(R.id.cart_price);

        }
    }
}
