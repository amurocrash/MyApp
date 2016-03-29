package com.amuro.lib.infrustructure.http_async.response_parser;

import com.amuro.lib.infrustructure.http_async.urlParser.URLData;

public class ResponseParserFactory<T>
{

	public static <T> IResponseParser<T> getResponseParser(int responseType)
	{
		switch(responseType)
		{
		case URLData.JSON:
			return new JsonParser<T>();
			
		default :
			return null;
		}
		
	}
}
