package com.amuro.lib.infrustructure.http;

import android.text.TextUtils;

import com.amuro.lib.infrustructure.http_async.urlParser.URLData;
import com.amuro.lib.utils.LogUtils;

import java.util.Map;

/**
 * Created by Amuro on 2016/3/8.
 */
public class HttpRequestGet extends HttpRequest
{
    public HttpRequestGet(URLData urlData)
    {
        super(urlData);
    }

    public HttpRequestGet(URLData urlData, Map<String, String> paramMap)
    {
        super(urlData, paramMap);
    }

    @Override
    protected String getMethod()
    {
        return "GET";
    }

    @Override
    protected String getUrl()
    {
        String url = urlData.getUrl();

        String params = generateParam();

        if(!TextUtils.isEmpty(params))
        {
            url = url + "?" + params;
        }

        LogUtils.e("url -> " + url);
        return url;
    }

    @Override
    protected void sendData() throws Exception
    {

    }
}
