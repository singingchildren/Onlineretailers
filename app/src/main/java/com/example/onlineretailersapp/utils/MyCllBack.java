package com.example.onlineretailersapp.utils;

/**
 * author:${张四佟}
 * date:2019/2/12 15:01
 * description
 */
public interface MyCllBack<T> {
    void setData(T data);
    void setError(T error);
}
