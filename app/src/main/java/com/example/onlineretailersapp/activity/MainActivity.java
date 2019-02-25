package com.example.onlineretailersapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.onlineretailersapp.R;
import com.example.onlineretailersapp.mainfragment.CircleFragment;
import com.example.onlineretailersapp.mainfragment.HomePageFragment;
import com.example.onlineretailersapp.mainfragment.MyMessageFragment;
import com.example.onlineretailersapp.mainfragment.OrderFragment;
import com.example.onlineretailersapp.mainfragment.ShoppingCartFragment;
import com.example.onlineretailersapp.utils.ImmersiveUtil;
import com.example.onlineretailersapp.weight.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.main_frame)
    FrameLayout mainFrame;
    @BindView(R.id.home_page_rbtn)
    Button homePageRbtn;
    @BindView(R.id.circle_rbtn)
    Button circleRbtn;
    @BindView(R.id.shopping_cart_rbtn)
    Button shoppingCartRbtn;
    @BindView(R.id.order_rbtn)
    Button orderRbtn;
    @BindView(R.id.my_message_rbtn)
    Button myMessageRbtn;
    private FragmentTransaction transaction;
    private HomePageFragment homePageFragment;
    private CircleFragment circleFragment;
    private ShoppingCartFragment shoppingCartFragment;
    private OrderFragment orderFragment;
    private MyMessageFragment myMessageFragment;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        homePageFragment = new HomePageFragment();
        circleFragment = new CircleFragment();
        shoppingCartFragment = new ShoppingCartFragment();
        orderFragment = new OrderFragment();
        myMessageFragment = new MyMessageFragment();

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.main_frame, homePageFragment);
        ImmersiveUtil.setStatusColor(this);
        ImmersiveUtil.setSystemInvadeBlack(this);
        hideFragment(transaction);
        transaction.show(homePageFragment).commit();;
        oneBtn();

    }

    @OnClick({R.id.home_page_rbtn, R.id.circle_rbtn, R.id.shopping_cart_rbtn, R.id.order_rbtn, R.id.my_message_rbtn})
    public void onViewClicked(View view) {
        transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.home_page_rbtn:
                    hideFragment(transaction);
                    transaction.show(homePageFragment).commit();;
                    homePageRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_home_s));
                    circleRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_circle_n));
                    orderRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_list_n));
                    myMessageRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_my_n));
                break;
            case R.id.circle_rbtn:
                if (!circleFragment.isAdded()){
                    transaction.add(R.id.main_frame, circleFragment);
                    hideFragment(transaction);
                    transaction.show(circleFragment).commit();;
                    homePageRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_home_n));
                    circleRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_circle_s));
                    orderRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_list_n));
                    myMessageRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_my_n));
                }else{
                    hideFragment(transaction);
                    transaction.show(circleFragment).commit();;
                    homePageRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_home_n));
                    circleRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_circle_s));
                    orderRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_list_n));
                    myMessageRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_my_n));
                }
                break;
            case R.id.shopping_cart_rbtn:
                if (!shoppingCartFragment.isAdded()){
                    transaction.add(R.id.main_frame, shoppingCartFragment);
                    hideFragment(transaction);
                    transaction.show(shoppingCartFragment).commit();;
                    homePageRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_home_n));
                    circleRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_circle_n));
                    orderRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_list_n));
                    myMessageRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_my_n));
                }else{
                    hideFragment(transaction);
                    transaction.show(shoppingCartFragment).commit();;
                    homePageRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_home_n));
                    circleRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_circle_n));
                    orderRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_list_n));
                    myMessageRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_my_n));
                }

                break;
            case R.id.order_rbtn:
                if (!orderFragment.isAdded()){
                    transaction.add(R.id.main_frame, orderFragment);
                    hideFragment(transaction);
                    transaction.show(orderFragment).commit();;
                    homePageRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_home_n));
                    circleRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_circle_n));
                    orderRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_list_s));
                    myMessageRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_my_n));
                }else {
                    hideFragment(transaction);
                    transaction.show(orderFragment).commit();;
                    homePageRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_home_n));
                    circleRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_circle_n));
                    orderRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_list_s));
                    myMessageRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_my_n));
                }

                break;
            case R.id.my_message_rbtn:
                if (!myMessageFragment.isAdded()){
                    transaction.add(R.id.main_frame, myMessageFragment);
                    hideFragment(transaction);
                    transaction.show(myMessageFragment).commit();;
                    homePageRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_home_n));
                    circleRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_circle_n));
                    orderRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_list_n));
                    myMessageRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_my_s));
                }else{
                    hideFragment(transaction);
                    transaction.show(myMessageFragment).commit();;
                    homePageRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_home_n));
                    circleRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_circle_n));
                    orderRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_list_n));
                    myMessageRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_my_s));
                }

                break;
        }
    }

    private void hideFragment(FragmentTransaction transaction) {
        transaction.hide(homePageFragment)
                   .hide(circleFragment)
                   .hide(shoppingCartFragment)
                   .hide(orderFragment)
                   .hide(myMessageFragment);
    }

    private void oneBtn() {
        homePageRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_home_s));
        circleRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_circle_n));
        orderRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_list_n));
        myMessageRbtn.setBackground(getResources().getDrawable(R.mipmap.common_tab_btn_my_n));
    }
}
