package com.example.onlineretailersapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onlineretailersapp.R;
import com.example.onlineretailersapp.bean.CircleBean;
import com.example.onlineretailersapp.weight.SpacingItemDecoration;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * author:${张四佟}
 * date:2019/2/19 13:35
 * description
 */
public class CircleAdapter extends RecyclerView.Adapter<CircleAdapter.ViewHolder>{
    private Context context;
    private List<CircleBean.ResultBean> beanList;
    private boolean isGreat = true;
    public CircleAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<CircleBean.ResultBean> data) {
        if (data!=null){
            this.beanList = data;
            notifyDataSetChanged();
        }
    }
    public void addData(List<CircleBean.ResultBean> list) {
        if (beanList!=null){
            this.beanList.addAll(list);
            notifyDataSetChanged();
        }

    }

    @NonNull
    @Override
    public CircleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.circle_item,null);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final CircleAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.titleImg.setImageURI(beanList.get(i).getHeadPic());
        viewHolder.userName.setText(beanList.get(i).getNickName());
        viewHolder.contentText.setText(beanList.get(i).getContent());
        viewHolder.zannum.setText(beanList.get(i).getGreatNum()+"");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
        viewHolder.fabuDate.setText(simpleDateFormat.format(beanList.get(i).getCreateTime()));
        String[] images = beanList.get(i).getImage().split(",");
        //int imageCount = (int)(Math.random()*9)+1;
        int imageCount = images.length;
        int colNum;//列数
        if (imageCount == 1){
            colNum = 1;
        }else if (imageCount == 2||imageCount == 4){
            colNum = 2;
        }else {
            colNum = 3;
        }
        viewHolder.imageAdapter.clear();//清空
            /*for (int j = 0; j <imageCount ; j++) {
                viewHolder.imageAdapter.addAll(Arrays.asList(images));
            }*/
        viewHolder.imageAdapter.addAll(Arrays.asList(images));
        viewHolder.gridLayoutManager.setSpanCount(colNum);//设置列数
        viewHolder.imageAdapter.notifyDataSetChanged();
        viewHolder.cancelPoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDianZan!=null){
                    isDianZan.cancelPoints(beanList.get(i).getId(),i);
                    if(isGreat){
                        int zanAdd = beanList.get(i).getGreatNum();
                        zanAdd++;
                        beanList.get(i).setGreatNum(zanAdd);
                        viewHolder.fabulous.setVisibility(View.VISIBLE);
                        isGreat = false;
                    }
                    notifyItemChanged(i,"");
                }
            }
        });
        viewHolder.fabulous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDianZan!=null){
                    isDianZan.addGrzan(beanList.get(i).getId(),i);
                    if(!isGreat){
                        int zanAdd = beanList.get(i).getGreatNum();
                        zanAdd--;
                        beanList.get(i).setGreatNum(zanAdd);
                        viewHolder.fabulous.setVisibility(View.GONE);
                        isGreat = true;
                    }
                    notifyItemChanged(i,"");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private final SimpleDraweeView titleImg;
        private final TextView userName;
        private final TextView fabuDate;
        private final RecyclerView gridView;
        GridLayoutManager gridLayoutManager;
        private final ImageAdapter imageAdapter;
        private final TextView contentText,zannum;
        private final ImageView cancelPoints;
        private final ImageView fabulous;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gridView = itemView.findViewById(R.id.grid_view);
            titleImg = itemView.findViewById(R.id.title_img);
            userName = itemView.findViewById(R.id.user_name);
            fabuDate = itemView.findViewById(R.id.fabu_date);
            contentText = itemView.findViewById(R.id.content_text);
            cancelPoints = itemView.findViewById(R.id.cancel_points);
            fabulous = itemView.findViewById(R.id.fabulous);
            zannum = itemView.findViewById(R.id.zannum);

            imageAdapter = new ImageAdapter();
            int space = context.getResources().getDimensionPixelSize(R.dimen.dp_10);;//图片间距
            gridLayoutManager = new GridLayoutManager(context,3);
            gridView.addItemDecoration(new SpacingItemDecoration(space));
            gridView.setLayoutManager(gridLayoutManager);
            gridView.setAdapter(imageAdapter);
        }
    }

    public interface IsDianZan{
        void cancelPoints(int qId,int i);
        void addGrzan(int qId,int j);
    }

    public IsDianZan isDianZan;

    public void setIsDianZan(IsDianZan isDianZan) {
        this.isDianZan = isDianZan;
    }
}
