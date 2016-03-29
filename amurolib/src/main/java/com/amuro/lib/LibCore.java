package com.amuro.lib;

import android.content.Context;

import com.amuro.lib.infrustructure.http_async.urlParser.URLDataInitiator;
import com.amuro.lib.utils.SharedPreferManager;

/**
 * Created by Amuro on 2016/3/8.
 */
public class LibCore
{
    private static LibCore instance = new LibCore();

    private LibCore()
    {

    }

    public static LibCore getInstance()
    {
        return instance;
    }

    public void init(Context context)
    {
        URLDataInitiator.init(context);
        SharedPreferManager.getInstance().init(context);
    }
}
