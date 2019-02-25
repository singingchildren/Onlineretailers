package com.example.onlineretailersapp.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineretailersapp.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * author:${张四佟}
 * date:2019/2/20 8:48
 * description
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyHodler>  {

    private List<Object> mList = new ArrayList<>();
    private int sign;//0:普通点击，1自定义

    public void addAll(List<String> list){
        mList.addAll(list);
    }

    @NonNull
    @Override
    public ImageAdapter.MyHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(), R.layout.circle_image_item,null);
        return new MyHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.MyHodler myHodler, int i) {
        String imageUrl = (String) mList.get(i);
        if (imageUrl.contains("http:")) {//加载http
            myHodler.image.setImageURI(Uri.parse(imageUrl));
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyHodler extends RecyclerView.ViewHolder {
        SimpleDraweeView image;
        public MyHodler(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.circle_image);
        }
    }

    public void clear() {
        mList.clear();
    }
}
