package com.amuro.lib.infrustructure.http_async.response_parser;

public interface IResponseParser<T>
{
	public T parseResponse(String response, Class<T> clazz);
}
