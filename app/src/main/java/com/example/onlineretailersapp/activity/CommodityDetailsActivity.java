package com.example.onlineretailersapp.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.onlineretailersapp.R;
import com.example.onlineretailersapp.adapter.CommodityAdapter;
import com.example.onlineretailersapp.adapter.ShowCartAdapter;
import com.example.onlineretailersapp.bean.AddCartBean;
import com.example.onlineretailersapp.bean.CommodityDetailsBean;
import com.example.onlineretailersapp.network.Contacts;
import com.example.onlineretailersapp.prsenter.IPrsenterImpl;
import com.example.onlineretailersapp.utils.ImmersiveUtil;
import com.example.onlineretailersapp.utils.SPFUtil;
import com.example.onlineretailersapp.view.IView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommodityDetailsActivity<T> extends AppCompatActivity implements IView<T> {


    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.commodity_recy)
    RecyclerView commodityRecy;
    private String uid;
    private boolean isClick = false;
    private CommodityAdapter commodityAdapter;
    private IPrsenterImpl iPrsenter;
    private String userId;
    private String sessionId;
    private ArrayList<Integer> goodsIdList = new ArrayList<>();
    private int idIsTong = 1;
    private ShowCartAdapter showCartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_details);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        ImmersiveUtil.setStatusColor(this);
        ImmersiveUtil.setSystemInvadeBlack(this);
        showCartAdapter = new ShowCartAdapter(this);
        commodityAdapter = new CommodityAdapter(this);
        SPFUtil spfUtil = new SPFUtil();
        userId = spfUtil.getString(this, "userId", "");
        sessionId = spfUtil.getString(this, "sessionId", "");
        tabMother();
        Map<String, String> map = new HashMap<>();
        map.put("commodityId", uid);
        iPrsenter = new IPrsenterImpl(this);
        iPrsenter.startRequest(Contacts.COMMODITY_DETAILS, map, CommodityDetailsBean.class, 6, null, null);

        commodityAdapter.setAddToCart(new CommodityAdapter.AddToCart() {
            @Override
            public void clickAdd(int goodsId) {
                goodsIdList.add(goodsId);
                JSONArray jsonArray = new JSONArray();
                for (int i = 0; i < goodsIdList.size(); i++) {
                   /* if (goodsId == goodsIdList.get(i)){
                        idIsTong++;
                    }else{*/
                    try {
                        JSONObject object = new JSONObject();
                        object.put("commodityId", goodsIdList.get(i));
                        object.put("count", idIsTong);
                        jsonArray.put(object);
                        //Toast.makeText(CommodityDetailsActivity.this,jsonArray.toString(),Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //}
                }
                Map<String, String> addMap = new HashMap<>();
                addMap.put("data", jsonArray.toString());
                iPrsenter.startRequest(Contacts.ADDTOCART_URL, addMap, AddCartBean.class, 9, userId, sessionId);
                //showCartAdapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void onSueecss(T data) {
        if (data instanceof CommodityDetailsBean) {
            CommodityDetailsBean detailsBean = (CommodityDetailsBean) data;
            commodityAdapter.setData(detailsBean.getResult());
            commodityRecy.setAdapter(commodityAdapter);
            commodityRecy.setLayoutManager(new LinearLayoutManager(this));
        }
        if (data instanceof AddCartBean) {
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(T error) {

    }

    @Subscribe(sticky = true)
    public void onEventMainThread(String uid) {
        this.uid = uid;
    }

    private void tabMother() {
        for (int i = 0; i < 3; i++) {
            String text = null;
            switch (i) {
                case 0:
                    text = "商品";
                    break;
                case 1:
                    text = "详情";
                    break;
                case 2:
                    text = "评论";
                    break;
            }

            SpannableStringBuilder textC = new SpannableStringBuilder(text);
            textC.setSpan(new ForegroundColorSpan(Color.BLACK), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            textC.setSpan(new AbsoluteSizeSpan(30, true), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tabLayout.addTab(tabLayout.newTab().setText(textC), i, i == 0);
        }
        commodityRecy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.i("zkd", "[TablayoutWithRecyclerActivity][onScrollStateChanged]==> State : " + newState);
                if (newState == 0) {
                    isClick = false;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!isClick) {
                    LinearLayoutManager l = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int firstPosition = l.findFirstVisibleItemPosition();
                    int lastPosition = l.findLastVisibleItemPosition();
                    int allItems = l.getItemCount();
                    tabLayout.setScrollPosition(firstPosition, 0f, true);
                    Log.i("zkd", "[TablayoutWithRecyclerActivity][onScrollStateChanged]==> firstPosition : " + firstPosition + ",\nlastPosition:" + lastPosition + ",\nall:" + allItems);
                }
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(final TabLayout.Tab tab) {

                if (tab.getPosition() == 0) {
                    LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(CommodityDetailsActivity.this);
                    commodityRecy.setLayoutManager(mLinearLayoutManager);
                    mLinearLayoutManager.scrollToPositionWithOffset(0, 0);
                } else if (tab.getPosition() == 1) {
                    LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(CommodityDetailsActivity.this);
                    commodityRecy.setLayoutManager(mLinearLayoutManager);
                    mLinearLayoutManager.scrollToPositionWithOffset(1, 1);
                } else if (tab.getPosition() == 2) {
                    LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(CommodityDetailsActivity.this);
                    commodityRecy.setLayoutManager(mLinearLayoutManager);
                    mLinearLayoutManager.scrollToPositionWithOffset(2, 2);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @OnClick(R.id.back_btn)
    public void onViewClicked() {
        finish();
    }
}
