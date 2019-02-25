package com.example.onlineretailersapp.network;

import com.example.onlineretailersapp.bean.LoginBean;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * author:${张四佟}
 * date:2019/2/12 14:07
 * description
 */
public interface MyApiService {
    @POST
    Observable<ResponseBody> post(@Url String url, @QueryMap Map<String,String> map, @Header("userId") String userId, @Header("sessionId")String sessionId);

    @GET
    Observable<ResponseBody> get(@Url String url);

    @GET
    Observable<ResponseBody> getAll(@Url String url,@QueryMap Map<String,String> map, @Header("userId") String userId, @Header("sessionId")String sessionId);

    @GET
    Observable<ResponseBody> cartGet(@Url String url, @Header("userId") String userId, @Header("sessionId")String sessionId);

    @DELETE
    Observable<ResponseBody> delete(@Url String url, @QueryMap Map<String,String> map, @Header("userId") String userId, @Header("sessionId")String sessionId);

    @PUT
    Observable<ResponseBody> put(@Url String url, @QueryMap Map<String,String> map, @Header("userId") String userId, @Header("sessionId")String sessionId);

}
