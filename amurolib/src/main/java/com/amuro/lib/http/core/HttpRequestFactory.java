package com.amuro.lib.http.core;

import com.amuro.lib.http.urlParser.URLData;
import com.amuro.lib.http.urlParser.URLDataInitiator;

import java.util.Map;

/**
 * Created by Amuro on 2016/3/8.
 */
public class HttpRequestFactory
{
    public static HttpRequest getRequest(
            URLData urlData, Map<String, String> paramMap, HttpRequest.OnHttpResponseListener httpListener)
    {
        int method = urlData.getMethod();

        if(method == URLData.GET)
        {
            return new HttpRequestGet(urlData, paramMap, httpListener);
        }
        else if(method == URLData.POST)
        {
            return new HttpRequestPost(urlData, paramMap, httpListener);
        }
        else
        {
            return null;
        }

    }
}
