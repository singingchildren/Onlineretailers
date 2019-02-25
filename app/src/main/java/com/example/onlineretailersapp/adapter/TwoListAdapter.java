package com.example.onlineretailersapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.onlineretailersapp.R;
import com.example.onlineretailersapp.bean.OneListBean;
import com.example.onlineretailersapp.bean.TwoListBean;

import java.util.List;

/**
 * author:${张四佟}
 * date:2019/2/24 20:01
 * description
 */
public class TwoListAdapter extends RecyclerView.Adapter<TwoListAdapter.ViewHolder> {

    private Context context;
    private List<TwoListBean.ResultBean> beans;
    public TwoListAdapter(Context context, List<TwoListBean.ResultBean> result) {
        this.context = context;
        this.beans = result;
    }

    @NonNull
    @Override
    public TwoListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.two_list_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TwoListAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.textTwo.setText(" "+beans.get(i).getName()+" ");
       /* viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickItemListenner!=null){
                    clickItemListenner.clickItem(beans.get(i).getId()+"");
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return beans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textTwo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTwo = itemView.findViewById(R.id.texttwo);
        }
    }

    public interface ClickItemListenner{
        void clickItem(String id);
    }

   /* public ClickItemListenner clickItemListenner;

    public void setClickItemListenner(ClickItemListenner clickItemListenner) {
        this.clickItemListenner = clickItemListenner;
    }

    public void setContext(Context context) {
        this.context = context;
    }*/
}
