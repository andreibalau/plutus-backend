package com.finance.plutus.service.http;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

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
				.client(createClient())
//				.addConverterFactory(SimpleXmlConverterFactory.create())
				.addConverterFactory(JacksonConverterFactory.create())
				.build();
	}

	private synchronized OkHttpClient createClient() {
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		return new OkHttpClient.Builder().addInterceptor(interceptor).build();
	}

}
