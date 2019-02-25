package com.example.onlineretailersapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.onlineretailersapp.R;
import com.example.onlineretailersapp.bean.CircleBean;
import com.example.onlineretailersapp.bean.OneListBean;

import java.util.List;

/**
 * author:${张四佟}
 * date:2019/2/24 20:01
 * description
 */
public class OneListAdapter extends RecyclerView.Adapter<OneListAdapter.ViewHolder> {

    private Context context;
    private List<OneListBean.ResultBean> beans;
    public OneListAdapter(Context context, List<OneListBean.ResultBean> result) {
        this.context = context;
        this.beans = result;
    }

    @NonNull
    @Override
    public OneListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.one_list_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OneListAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.textOne.setText(" "+beans.get(i).getName()+" ");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickItemListenner!=null){
                    clickItemListenner.clickItem(beans.get(i).getId()+"");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return beans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textOne;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textOne = itemView.findViewById(R.id.textone);
        }
    }

    public interface ClickItemListenner{
        void clickItem(String id);
    }

    public ClickItemListenner clickItemListenner;

    public void setClickItemListenner(ClickItemListenner clickItemListenner) {
        this.clickItemListenner = clickItemListenner;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
