package com.example.onlineretailersapp.model;

import android.os.Handler;
import android.os.Message;

import com.example.onlineretailersapp.bean.AddCartBean;
import com.example.onlineretailersapp.bean.AddGreat;
import com.example.onlineretailersapp.bean.BannerBean;
import com.example.onlineretailersapp.bean.CircleBean;
import com.example.onlineretailersapp.bean.CommodityDetailsBean;
import com.example.onlineretailersapp.bean.LoginBean;
import com.example.onlineretailersapp.bean.MainGoodsBean;
import com.example.onlineretailersapp.bean.OneListBean;
import com.example.onlineretailersapp.bean.QxGreat;
import com.example.onlineretailersapp.bean.RegisterBean;
import com.example.onlineretailersapp.bean.SearchKayBean;
import com.example.onlineretailersapp.bean.ShowCarBean;
import com.example.onlineretailersapp.bean.TwoListBean;
import com.example.onlineretailersapp.utils.MyCllBack;
import com.example.onlineretailersapp.utils.RetrofitUtil;
import com.google.gson.Gson;

import java.util.Map;

/**
 * author:${张四佟}
 * date:2019/2/12 15:12
 * description
 */
public class ModelImpl implements Model{
    /*
    * type
    * 1.post
    *
    * */
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            handler.sendMessageDelayed(msg,1000);
            return false;
        }
    });

    @Override
    public void getData(final String mPath, final Map<String, String> map, Class clazz, int type, final String userId, final String sessionId, final MyCllBack myCllBack) {
        switch (type){
            case 1:
                getLogin(mPath,map,userId,sessionId,myCllBack);
                break;
            case 2:
                getRegister(mPath,map,userId,sessionId,myCllBack);
                break;
            case 3:
                getMainGoods(mPath,map,userId,sessionId,myCllBack);
                break;
            case 4:
                getBanner(mPath,map,userId,sessionId,myCllBack);
                break;
            case 5:
                getCircleList(mPath,map,userId,sessionId,myCllBack);
                break;
            case 6:
                getCommdityDetails(mPath,map,userId,sessionId,myCllBack);
                break;
            case 7:
                getAddGreat(mPath,map,userId,sessionId,myCllBack);
                break;
            case 8:
                getQxGreat(mPath,map,userId,sessionId,myCllBack);
                break;
            case 9:
                getAddToCart(mPath,map,userId,sessionId,myCllBack);
                break;
            case 10:
                getShowCart(mPath,map,userId,sessionId,myCllBack);
                break;
            case 11:
                getOneList(mPath,map,userId,sessionId,myCllBack);
                break;
            case 12:
                getTwoList(mPath,map,userId,sessionId,myCllBack);
                break;
            case 13:
                getSearchKey(mPath,map,userId,sessionId,myCllBack);
                break;
        }
    }

    private void getSearchKey(String mPath, Map<String, String> map, String userId, String sessionId, final MyCllBack myCllBack) {
        RetrofitUtil.getInstance().getAll(mPath,map,null,null).setHttpListener(new RetrofitUtil.HttpListener() {
            @Override
            public void onSuccess(String objStr) {
                if (myCllBack!=null){
                    SearchKayBean searchKayBean = new Gson().fromJson(objStr,SearchKayBean.class);
                    myCllBack.setData(searchKayBean);
                }else{
                    //Nothing to do
                }
            }
            @Override
            public void onError(String error) {
            }
        });
    }

    private void getTwoList(String mPath, Map<String, String> map, String userId, String sessionId, final MyCllBack myCllBack) {
        RetrofitUtil.getInstance().getAll(mPath,map,null,null).setHttpListener(new RetrofitUtil.HttpListener() {
            @Override
            public void onSuccess(String objStr) {
                if (myCllBack!=null){
                    TwoListBean twoListBean = new Gson().fromJson(objStr,TwoListBean.class);
                    myCllBack.setData(twoListBean);
                }else{
                    //Nothing to do
                }
            }
            @Override
            public void onError(String error) {
            }
        });
    }


    private void getOneList(String mPath, Map<String, String> map, String userId, String sessionId, final MyCllBack myCllBack) {
        RetrofitUtil.getInstance().get(mPath).setHttpListener(new RetrofitUtil.HttpListener() {
            @Override
            public void onSuccess(String objStr) {
                if (myCllBack!=null){
                    OneListBean oneListBean = new Gson().fromJson(objStr,OneListBean.class);
                    myCllBack.setData(oneListBean);
                }else{
                    //Nothing to do
                }
            }
            @Override
            public void onError(String error) {
            }
        });
    }

    private void getShowCart(String mPath, Map<String, String> map, String userId, String sessionId, final MyCllBack myCllBack) {
        RetrofitUtil.getInstance().cartGet(mPath,userId,sessionId).setHttpListener(new RetrofitUtil.HttpListener() {
            @Override
            public void onSuccess(String objStr) {
                if (myCllBack!=null){
                    ShowCarBean showCarBean = new Gson().fromJson(objStr,ShowCarBean.class);
                    myCllBack.setData(showCarBean);
                }else{
                    //Nothing to do
                }
            }
            @Override
            public void onError(String error) {
            }
        });
    }

    private void getAddToCart(String mPath, Map<String, String> map, String userId, String sessionId, final MyCllBack myCllBack) {
        RetrofitUtil.getInstance().put(mPath,map,userId,sessionId).setHttpListener(new RetrofitUtil.HttpListener() {
            @Override
            public void onSuccess(String objStr) {
                if (myCllBack!=null){
                    AddCartBean addCartBean = new Gson().fromJson(objStr,AddCartBean.class);
                    myCllBack.setData(addCartBean);
                }else{
                    //Nothing to do
                }
            }
            @Override
            public void onError(String error) {
            }
        });
    }

    private void getQxGreat(String mPath, Map<String, String> map, String userId, String sessionId, final MyCllBack myCllBack) {
        RetrofitUtil.getInstance().delete(mPath,map,userId,sessionId).setHttpListener(new RetrofitUtil.HttpListener() {
            @Override
            public void onSuccess(String objStr) {
                if (myCllBack!=null){
                    QxGreat qxGreat = new Gson().fromJson(objStr,QxGreat.class);
                    myCllBack.setData(qxGreat);
                }else{
                    //Nothing to do
                }
            }
            @Override
            public void onError(String error) {
            }
        });
    }

    private void getAddGreat(String mPath, Map<String, String> map, String userId, String sessionId, final MyCllBack myCllBack) {
        RetrofitUtil.getInstance().post(mPath,map,userId,sessionId).setHttpListener(new RetrofitUtil.HttpListener() {
            @Override
            public void onSuccess(String objStr) {
                if (myCllBack!=null){
                    AddGreat addGreat = new Gson().fromJson(objStr,AddGreat.class);
                    myCllBack.setData(addGreat);
                }else{
                    //Nothing to do
                }
            }
            @Override
            public void onError(String error) {
            }
        });
    }

    private void getCommdityDetails(String mPath, Map<String, String> map, String userId, String sessionId, final MyCllBack myCllBack) {
        RetrofitUtil.getInstance().getAll(mPath,map,userId,sessionId).setHttpListener(new RetrofitUtil.HttpListener() {
            @Override
            public void onSuccess(String objStr) {
                if (myCllBack!=null){
                    CommodityDetailsBean detailsBean = new Gson().fromJson(objStr,CommodityDetailsBean.class);
                    myCllBack.setData(detailsBean);
                }else{
                    //Nothing to do
                }
            }
            @Override
            public void onError(String error) {
            }
        });
    }

    private void getCircleList(String mPath, Map<String, String> map, String userId, String sessionId, final MyCllBack myCllBack) {
        RetrofitUtil.getInstance().getAll(mPath,map,userId,sessionId).setHttpListener(new RetrofitUtil.HttpListener() {
            @Override
            public void onSuccess(String objStr) {
                if (myCllBack!=null){
                    CircleBean circleBean = new Gson().fromJson(objStr,CircleBean.class);
                    myCllBack.setData(circleBean);
                }else{
                    //Nothing to do
                }
            }
            @Override
            public void onError(String error) {
            }
        });
    }

    private void getBanner(String mPath, Map<String, String> map, String userId, String sessionId, final MyCllBack myCllBack) {
        RetrofitUtil.getInstance().get(mPath).setHttpListener(new RetrofitUtil.HttpListener() {
            @Override
            public void onSuccess(String objStr) {
                if (myCllBack!=null){
                    BannerBean bannerBean = new Gson().fromJson(objStr,BannerBean.class);
                    myCllBack.setData(bannerBean);
                }else{
                    //Nothing to do
                }
            }
            @Override
            public void onError(String error) {
            }
        });
    }

    private void getLogin(String mPath, Map<String, String> map, String userId, String sessionId, final MyCllBack myCllBack) {
        RetrofitUtil.getInstance().post(mPath,map,userId,sessionId).setHttpListener(new RetrofitUtil.HttpListener() {

            @Override
            public void onSuccess(String objStr) {
                if (myCllBack!=null){
                    LoginBean loginBean = new Gson().fromJson(objStr,LoginBean.class);
                    myCllBack.setData(loginBean);
                }else{
                    //Nothing to do
                }
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void getRegister(String mPath, Map<String, String> map, String userId, String sessionId, final MyCllBack myCllBack) {
        RetrofitUtil.getInstance().post(mPath,map,userId,sessionId).setHttpListener(new RetrofitUtil.HttpListener() {

            @Override
            public void onSuccess(String objStr) {
                if (myCllBack!=null){
                    RegisterBean registerBean = new Gson().fromJson(objStr,RegisterBean.class);
                    myCllBack.setData(registerBean);
                }else{
                    //Nothing to do
                }
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void getMainGoods(String mPath, Map<String, String> map, String userId, String sessionId, final MyCllBack myCllBack) {
        RetrofitUtil.getInstance().get(mPath).setHttpListener(new RetrofitUtil.HttpListener() {
            @Override
            public void onSuccess(String objStr) {
                if (myCllBack!=null){
                    MainGoodsBean mainGoodsBean = new Gson().fromJson(objStr,MainGoodsBean.class);
                    myCllBack.setData(mainGoodsBean);
                }else{
                    //Nothing to do
                }
            }
            @Override
            public void onError(String error) {

            }
        });
    }
}
