package com.example.onlineretailersapp.view;

/**
 * author:${张四佟}
 * date:2019/2/12 15:13
 * description
 */
public interface IView<T> {
    void onSueecss(T data);
    void onError(T error);
}
