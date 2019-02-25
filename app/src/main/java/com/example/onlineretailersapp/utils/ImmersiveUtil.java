package com.example.onlineretailersapp.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;

import crossoverone.statuslib.StatusUtil;

/**
 * author:${张四佟}
 * date:2019/2/20 18:40
 * description
 */
public class ImmersiveUtil {

    public static void setStatusColor(Context context) {
        StatusUtil.setUseStatusBarColor((Activity) context, Color.parseColor("#00000000"));
    }

    public static void setSystemInvadeBlack(Context context) {
        // 第二个参数是是否沉浸,第三个参数是状态栏字体是否为黑色。
        StatusUtil.setSystemStatus((Activity) context, true, true);
    }
}
