package com.amuro.lib.infrustructure.http;

import android.text.TextUtils;

import com.amuro.lib.infrustructure.http_async.constants.HttpConstants;
import com.amuro.lib.infrustructure.http_async.response_parser.BaseEntity;
import com.amuro.lib.infrustructure.http_async.response_parser.IResponseParser;
import com.amuro.lib.infrustructure.http_async.response_parser.ResponseParserFactory;
import com.amuro.lib.infrustructure.http_async.urlParser.URLData;
import com.amuro.lib.utils.LogUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by user on 2016/3/29.
 */
public abstract class HttpRequest
{
    protected HttpURLConnection conn;
    protected URLData urlData;
    protected Map<String, String> paramMap;

    public HttpRequest(URLData urlData)
    {
        this(urlData, null);
    }

    public HttpRequest(URLData urlData, Map<String, String> paramMap)
    {
        this.urlData = urlData;
        this.paramMap = paramMap;
    }

    protected abstract String getMethod();
    protected abstract String getUrl();
    protected abstract void sendData() throws Exception;

    public BaseEntity launch()
    {
        try
        {
            initConn();
            sendData();
            return disposeResponse();
        }
        catch (Exception e)
        {
            BaseEntity be =
                    new BaseEntity(false, HttpConstants.ERROR_CODE_LOCAL, e.getMessage(), "");
            return be;
        }
        finally
        {
            conn.disconnect();
        }
    }

    protected void initConn() throws Exception
    {
        URL url = new URL(getUrl());
        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(getMethod());
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setReadTimeout(HttpConstants.DEFAULT_READ_TIMEOUT);
        conn.setConnectTimeout(HttpConstants.DEFAULT_CONNECT_TIMEOUT);
    }

    protected String generateParam()
    {
        if(paramMap == null || paramMap.size() == 0)
        {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, String> entry : paramMap.entrySet())
        {
            if(!TextUtils.isEmpty(sb))
            {
                sb.append("&");
            }

            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
        }

        return sb.toString();
    }

    protected BaseEntity disposeResponse() throws Exception
    {
        int responseCode = conn.getResponseCode();
        if (responseCode == 200)
        {
            InputStream in = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            StringBuilder sb = new StringBuilder();
            int len;
            char[] buffer = new char[1024];
            while ((len = br.read(buffer)) > 0)
            {
                sb.append(buffer, 0, len);
            }

            LogUtils.e("response: " + sb.toString());
            in.close();

            return parseResponse(sb.toString());

        }
        else
        {
            BaseEntity be = new BaseEntity(false, responseCode, "", "");
            return be;
        }
    }

    protected BaseEntity parseResponse(String response) throws Exception
    {
        IResponseParser<BaseEntity> parser =
                ResponseParserFactory.getResponseParser(urlData.getResponseType());
        BaseEntity baseEntity = parser.parseResponse(response, BaseEntity.class);

        if(baseEntity != null)
        {
            BaseEntity be = new BaseEntity(false, HttpConstants.ERROR_CODE_LOCAL, "", "");
            return be;
        }
        else
        {
            return baseEntity;
        }
    }

    public void abort()
    {
        if(conn != null)
        {
            conn.disconnect();
        }
    }
}
