package com.amuro.lib.http.response_parser;

import com.alibaba.fastjson.JSON;

public class JsonParser<T> implements IResponseParser<T>
{
	@Override
	public T parseResponse(String response, Class<T> clazz)
	{
		return JSON.parseObject(response, clazz);
	}

}
