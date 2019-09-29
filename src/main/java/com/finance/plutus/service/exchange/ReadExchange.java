package com.finance.plutus.service.exchange;

import lombok.RequiredArgsConstructor;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.annotation.PostConstruct;

/**
 * Plutus
 * Created by catalin on 28.09.2019
 */
@Component
@RequiredArgsConstructor
public class ReadExchange {

	private final HttpClient httpClient;

	@PostConstruct
	public void read() {
		BnrService bnrService = httpClient.getInstance().create(BnrService.class);
		bnrService.getRates().enqueue(new Callback<ResponseBody>() {
			@Override
			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
				System.out.println(response);
			}

			@Override
			public void onFailure(Call<ResponseBody> call, Throwable throwable) {
				throwable.printStackTrace();
			}
		});
	}

}
