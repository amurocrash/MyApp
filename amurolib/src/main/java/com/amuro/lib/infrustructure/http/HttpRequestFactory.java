package com.amuro.lib.infrustructure.http;

import com.amuro.lib.infrustructure.http_async.urlParser.URLData;

import java.util.Map;

/**
 * Created by Amuro on 2016/3/8.
 */
public class HttpRequestFactory
{
    public static HttpRequest getRequest(
            URLData urlData, Map<String, String> paramMap)
    {
        int method = urlData.getMethod();

        if(method == URLData.GET)
        {
            return new HttpRequestGet(urlData, paramMap);
        }
        else if(method == URLData.POST)
        {
            return new HttpRequestPost(urlData, paramMap);
        }
        else
        {
            return null;
        }

    }
}
