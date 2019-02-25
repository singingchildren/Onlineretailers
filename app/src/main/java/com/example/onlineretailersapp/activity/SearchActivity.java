package com.example.onlineretailersapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineretailersapp.R;
import com.example.onlineretailersapp.bean.SearchKayBean;
import com.example.onlineretailersapp.network.Contacts;
import com.example.onlineretailersapp.prsenter.IPrsenterImpl;
import com.example.onlineretailersapp.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity<T> extends AppCompatActivity implements IView<T> {

    @BindView(R.id.searchRecy)
    XRecyclerView searchRecy;
    @BindView(R.id.backImg)
    ImageView backImg;
    @BindView(R.id.edit_ss_search)
    EditText editSsSearch;
    @BindView(R.id.text_ss_search)
    TextView textSsSearch;
    private String keyWord;
    private IPrsenterImpl iPrsenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        Map<String,String> map = new HashMap<>();
        map.put("keyword",keyWord);
        map.put("page","1");
        map.put("count","10");

        iPrsenter = new IPrsenterImpl(this);
        if(!keyWord.isEmpty()){
            iPrsenter.startRequest(Contacts.SEARCH_KEY_URL,map,SearchKayBean.class,13,null,null);
            Toast.makeText(this,"查询成功",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"输入点东西鸭",Toast.LENGTH_SHORT).show();
        }

    }

    @Subscribe(sticky = true)
    public void onEventMainThread(String editSsStr) {
        this.keyWord = editSsStr;
    }


    @Override
    public void onSueecss(T data) {
        if (data instanceof SearchKayBean){

        }
    }

    @Override
    public void onError(T error) {

    }

    @OnClick(R.id.text_ss_search)
    public void onViewClicked() {

    }
}
