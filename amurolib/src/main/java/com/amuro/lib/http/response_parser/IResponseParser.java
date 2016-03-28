package com.amuro.lib.http.response_parser;

public interface IResponseParser<T>
{
	public T parseResponse(String response, Class<T> clazz);
}
