package com.ccjoin.jmumall.utils;

import android.content.Context;

/**
 * @author liuzhang
 * @date 2016年07月20日 13:45
 */
public class DensityUtil {

    public static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
