package com.amuro.lib.utils;

import android.content.Context;

/**
 * Created by Amuro on 2016/3/25.
 */
public class DisplayUtils
{
    public static int getScreenWidth(Context context)
    {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context)
    {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
}
