package com.amuro.lib.infrustructure.http;

import com.amuro.lib.infrustructure.http_async.constants.HttpConstants;
import com.amuro.lib.infrustructure.http_async.response_parser.BaseEntity;
import com.amuro.lib.infrustructure.http_async.urlParser.URLData;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Amuro on 2016/3/8.
 */
public class HttpHelper
{
    private HttpRequest request;
    private BaseEntity baseEntity;

    public BaseEntity invoke(URLData urlData)
    {
        return invoke(urlData, null);
    }

    public BaseEntity invoke(URLData urlData, Object bean)
    {
        Map<String, String> paramMap = null;
        if(bean != null)
        {
            try
            {
                paramMap = convertBeanToMap(bean);
            }
            catch (Exception e)
            {
                baseEntity = new BaseEntity(
                        false, HttpConstants.ERROR_CODE_LOCAL, "bean to map error", "");
                return baseEntity;
            }
        }

        return invoke(urlData, paramMap);
    }

    public BaseEntity invoke(URLData urlData, Map<String, String> paramMap)
    {
        baseEntity = null;


        if (doMock(urlData))
        {
            return baseEntity;
        }

        request = HttpRequestFactory.getRequest(
                urlData, paramMap);
        if (request == null)
        {
            baseEntity = new BaseEntity(
                    false,
                    HttpConstants.ERROR_CODE_LOCAL,
                    "Only support get and post",
                    "");
        }
        else
        {
            baseEntity = request.launch();
        }


        return baseEntity;
    }

    private boolean doMock(URLData urlData)
    {
        if(urlData.isMockable())
        {
            try
            {
                final IMockManager manager =
                        (IMockManager) Class.forName(urlData.getMockClass()).newInstance();

                Thread.sleep(manager.getResponseTime() * 1000);
                baseEntity = manager.getResponse();

            }
            catch (Exception e)
            {
                baseEntity = new BaseEntity(
                        false, HttpConstants.ERROR_CODE_LOCAL, "Mock failed", "");
            }

            return true;
        }

        return false;
    }

    private Map<String, String> convertBeanToMap(Object bean)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        Field[] fields = bean.getClass().getDeclaredFields();
        Map<String, String> map = new HashMap<String, String>();
        for(Field field : fields)
        {

            String fieldName = field.getName();
            StringBuffer sb = new StringBuffer();
            sb.append("get");
            sb.append(fieldName.substring(0, 1).toUpperCase());
            sb.append(fieldName.substring(1));

            Method method = bean.getClass().getMethod(sb.toString());
            String param = (String) method.invoke(bean, new Object[]{});

            map.put(fieldName, param);

        }

        return map;
    }

    public void cancel()
    {
        if(request != null)
        {
            request.abort();
        }
    }

//    private boolean doMock(URLData urlData)
//    {
//        if(urlData.isMockable())
//        {
//            try
//            {
//                final IMockManager<T> manager =
//                        (IMockManager) Class.forName(urlData.getMockClass()).newInstance();
//
//                if (manager != null)
//                {
//                    Thread.sleep(manager.getResponseTime() * 1000);
//                    baseEntity = manager.getResponseObject();
//
//
//                }
//                else
//                {
//                    if(listener != null)
//                    {
//                        listener.onFailed(new HttpError(HttpConstants.ERROR_CODE_LOCAL, "mock failed"));
//                    }
//                }
//
//                return true;
//            }
//            catch (Exception e)
//            {
//                if(listener != null)
//                {
//                    listener.onFailed(new HttpError(HttpConstants.ERROR_CODE_LOCAL, "mock failed"));
//                }
//            }
//        }
//
//        return false;
//
//
//    }



//    private com.amuro.lib.infrustructure.http_async.core.HttpRequest.OnHttpResponseListener getOnHttpResponseListener(final URLData urlData, final OnResponseListener<T> listener)
//    {
//        if(listener == null)
//        {
//            return null;
//        }
//
//        com.amuro.lib.infrustructure.http_async.core.HttpRequest.OnHttpResponseListener httpListener = new com.amuro.lib.infrustructure.http_async.core.HttpRequest.OnHttpResponseListener()
//        {
//            @Override
//            public void onSucceed(BaseEntity baseEntity)
//            {
//                if(!baseEntity.isSuccess())
//                {
//                    listener.onFailed(new HttpError(
//                            baseEntity.getErrorCode(), baseEntity.getErrorMessage()
//                    ));
//
//                    return;
//                }
//
//                String json = baseEntity.getResult();
//                IResponseParser<T> parser = new JsonParser<T>();
//                try
//                {
//                    Class<T> clazz = (Class<T>) Class.forName(urlData.getResponseClass());
//                    T t = parser.parseResponse(json, clazz);
//                    listener.onSuccess(t);
//                }
//                catch (Exception e)
//                {
//                    listener.onFailed(
//                            new HttpError(HttpConstants.ERROR_CODE_LOCAL, "Unable to parse your response class"));
//                }
//
//            }
//
//            @Override
//            public void onFailed(HttpError error)
//            {
//                listener.onFailed(error);
//            }
//        };
//
//        return httpListener;
//    }
}




































