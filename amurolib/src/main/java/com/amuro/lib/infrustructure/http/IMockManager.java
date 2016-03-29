package com.amuro.lib.infrustructure.http;

import com.amuro.lib.infrustructure.http_async.response_parser.BaseEntity;

import java.util.Map;

/**
 * Created by Amuro on 2016/3/9.
 */
public interface IMockManager
{
    void setParams(Map<String, String> paramMap);
    BaseEntity getResponse();
    int getResponseTime();
}
