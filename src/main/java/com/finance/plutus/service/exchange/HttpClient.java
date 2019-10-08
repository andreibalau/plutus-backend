package com.finance.plutus.service.exchange;

import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Plutus
 * Created by catalin on 28.09.2019
 */
@Service
public class HttpClient {

	private static final String BNR_ROOT = "https://www.bnr.ro";
	private static Retrofit retrofit;

	public synchronized Retrofit getInstance() {
		if (retrofit == null) {
			retrofit = new Retrofit
					.Builder()
					.baseUrl(BNR_ROOT)
					.addConverterFactory(SimpleXmlConverterFactory.create())
					.build();
		}
		return retrofit;
	}

}