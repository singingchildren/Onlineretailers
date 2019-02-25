package com.example.onlineretailersapp.utils;

import com.example.onlineretailersapp.network.Contacts;
import com.example.onlineretailersapp.network.MyApiService;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author:${张四佟}
 * date:2019/2/12 13:57
 * description
 */
public class RetrofitUtil {

    private final MyApiService myApiService;

    private RetrofitUtil() {
        //日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //初始化okhttp
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .retryOnConnectionFailure(true)
                .build();

        //初始化retrofit 结合两种操作 一个是gson解析 一个是结合rxjava
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Contacts.BASE_URL)
                .client(okHttpClient)
                .build();
        myApiService = retrofit.create(MyApiService.class);
    }

    public static RetrofitUtil getInstance(){
        return RetrofitHolder.UTIL;
    }

    private static class RetrofitHolder{
        private static final RetrofitUtil UTIL = new RetrofitUtil();
    }

    public RetrofitUtil put(String url, Map<String,String> map,String userId,String sessionId){
        myApiService.put(url,map,userId,sessionId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return RetrofitUtil.getInstance();
    }

    public RetrofitUtil post(String url, Map<String,String> map,String userId,String sessionId){
        myApiService.post(url,map,userId,sessionId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return RetrofitUtil.getInstance();
    }

    public RetrofitUtil get(String url){
        myApiService.get(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return RetrofitUtil.getInstance();
    }
    public RetrofitUtil getAll(String url, Map<String,String> map,String userId,String sessionId){
        myApiService.getAll(url,map,userId,sessionId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return RetrofitUtil.getInstance();
    }
    public RetrofitUtil cartGet(String url,String userId,String sessionId){
        myApiService.cartGet(url,userId,sessionId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return RetrofitUtil.getInstance();
    }
    public RetrofitUtil delete(String url, Map<String,String> map,String userId,String sessionId){
        myApiService.delete(url,map,userId,sessionId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return RetrofitUtil.getInstance();
    }


    private Observer observer = new Observer<ResponseBody>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            if(httpListener != null){
                httpListener.onError(e.getMessage());
            }
        }

        @Override
        public void onNext(ResponseBody responseBody) {
            if(httpListener != null){
                try {
                    httpListener.onSuccess(responseBody.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };





    public interface HttpListener{
        void onSuccess(String objStr);
        void onError(String error);
    }

    private HttpListener httpListener;

    public void setHttpListener(HttpListener listener){
        this.httpListener = listener;
    }

}
