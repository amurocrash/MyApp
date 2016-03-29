package com.amuro.lib.infrustructure.http;

import com.amuro.lib.infrustructure.http_async.urlParser.URLData;
import com.amuro.lib.utils.LogUtils;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

/**
 * Created by Amuro on 2016/3/8.
 */
public class HttpRequestPost extends HttpRequest
{
    public HttpRequestPost(URLData urlData)
    {
        super(urlData);
    }

    public HttpRequestPost(URLData urlData, Map<String, String> paramMap)
    {
        super(urlData, paramMap);
    }

    @Override
    protected String getMethod()
    {
        return "POST";
    }

    @Override
    protected String getUrl()
    {
        LogUtils.e("url -> " + urlData.getUrl());
        return urlData.getUrl();
    }

    @Override
    protected void sendData() throws Exception
    {
        String params = generateParam();
        OutputStream out = conn.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
        bw.write(params);
        bw.flush();
        bw.close();
    }
}
