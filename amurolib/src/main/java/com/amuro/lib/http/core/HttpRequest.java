package com.amuro.lib.http.core;


import android.os.Handler;
import android.text.TextUtils;

import com.amuro.lib.http.DefaultThreadPool;
import com.amuro.lib.http.constants.HttpConstants;
import com.amuro.lib.http.response_parser.BaseEntity;
import com.amuro.lib.http.response_parser.IResponseParser;
import com.amuro.lib.http.response_parser.ResponseParserFactory;
import com.amuro.lib.http.urlParser.URLData;
import com.amuro.lib.utils.LogUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Amuro on 2016/3/8.
 */
public abstract class HttpRequest implements Runnable
{
    public interface OnHttpResponseListener
    {
        public void onSucceed(BaseEntity baseEntity);
        public void onFailed(HttpError error);
    }

    private OnHttpResponseListener httpListener;

    private void notifySucceed(final BaseEntity baseEntity)
    {
        if(httpListener != null)
        {
            handler.post(new Runnable()
            {
                @Override
                public void run()
                {
                    httpListener.onSucceed(baseEntity);
                }
            });
        }
    }

    private void notifyFailed(final HttpError error)
    {
        if(httpListener != null)
        {
            handler.post(new Runnable()
            {
                @Override
                public void run()
                {
                    httpListener.onFailed(error);
                }
            });
        }
    }

    protected HttpURLConnection conn;
    protected Handler handler;
    protected URLData urlData;
    protected Map<String, String> paramMap;

    public HttpRequest(URLData urlData)
    {
        this(urlData, null, null);
    }

    public HttpRequest(URLData urlData, Map<String, String> paramMap, OnHttpResponseListener httpListener)
    {
        this.urlData = urlData;
        this.paramMap = paramMap;
        this.httpListener = httpListener;

        this.handler = new Handler();
    }

    protected abstract String getMethod();
    protected abstract String getUrl();
    protected abstract void sendData() throws Exception;

    @Override
    public void run()
    {
        try
        {
            initConn();
            sendData();
            disposeResponse();
        }
        catch (Exception e)
        {
            notifyFailed(
                    new HttpError(HttpConstants.ERROR_CODE_LOCAL,
                            "local error: " + e.getClass() + " --- " + e.getMessage())
            );
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

    protected void disposeResponse() throws Exception
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

            parseResponse(sb.toString());

        }
        else
        {
            notifyFailed(
                    new HttpError(responseCode, "Server error")
            );
        }
    }

    protected void parseResponse(String response) throws Exception
    {
        IResponseParser<BaseEntity> parser =
                ResponseParserFactory.getResponseParser(urlData.getResponseType());
        BaseEntity baseEntity = parser.parseResponse(response, BaseEntity.class);

        if(baseEntity != null)
        {
            notifySucceed(baseEntity);
        }
        else
        {
            notifyFailed(new HttpError(
                    HttpConstants.ERROR_CODE_LOCAL, "unable to parse response"));
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
