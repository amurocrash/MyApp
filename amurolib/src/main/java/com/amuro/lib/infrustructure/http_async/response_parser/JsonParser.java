package com.amuro.lib.infrustructure.http_async.response_parser;

import com.alibaba.fastjson.JSON;

public class JsonParser<T> implements IResponseParser<T>
{
	@Override
	public T parseResponse(String response)
	{
		return (T)JSON.parseObject(response);
	}

}
