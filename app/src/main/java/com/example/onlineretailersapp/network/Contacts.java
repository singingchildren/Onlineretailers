package com.example.onlineretailersapp.network;

import java.net.URL;

import retrofit2.http.POST;

/**
 * author:${张四佟}
 * date:2019/2/12 14:10
 * description
 */
public class Contacts {
    //主要路径
    public static final String BASE_URL = "http://172.17.8.100/small/";
    //登录
    public static final String LOGIN_URL = "user/v1/login";
    //注册
    public static final String REGISTER_URL = "user/v1/register";

    public static final String GOODS_URL = "commodity/v1/commodityList";
    //轮播图
    public static final String BANNER_URL = "commodity/v1/bannerShow";
    //圈子列表
    public static final String  CIRCLE_LIST_URL = "circle/v1/findCircleList";
    //商品详情
    public static final String COMMODITY_DETAILS = "commodity/v1/findCommodityDetailsById";
    //圈子点赞
    public static final String ADDGREAT_URL = "circle/verify/v1/addCircleGreat";
    //取消点赞
    public static final String CENENLGREAT_URL = "circle/verify/v1/cancelCircleGreat";
    //加入购物车
    public static final String ADDTOCART_URL = "order/verify/v1/syncShoppingCart";
    //加入购物车
    public static final String QU_CART_URL = "order/verify/v1/findShoppingCart";
    //一级列表
    public static final String ONE_LIST_URL = "commodity/v1/findFirstCategory";
    //二级列表
    public static final String TWO_LIST_URL = "commodity/v1/findSecondCategory";
    //关键字搜索
    public static final String SEARCH_KEY_URL = "commodity/v1/findCommodityByKeyword";
}
