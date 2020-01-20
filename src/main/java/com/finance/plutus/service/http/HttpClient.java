package com.finance.plutus.service.http;

import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Plutus
 * Created by catalin on 28.09.2019
 */
@Service
public class HttpClient {

	public synchronized Retrofit getInstance(String url) {
		return new Retrofit
				.Builder()
				.baseUrl(url)
				.addConverterFactory(SimpleXmlConverterFactory.create())
				.build();
	}

}
