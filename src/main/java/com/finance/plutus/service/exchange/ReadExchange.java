package com.finance.plutus.service.exchange;

import javax.annotation.PostConstruct;

import com.finance.plutus.model.exchange.dto.ExchangeRoot;
import com.finance.plutus.repository.exchange.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Plutus
 * Created by catalin on 28.09.2019
 */
@Component
@RequiredArgsConstructor
public class ReadExchange {

	private final HttpClient httpClient;
	private final ExchangeRepository exchangeRepository;

	@PostConstruct
	public void read() {
//		BnrService bnrService = httpClient.getInstance().create(BnrService.class);
//		bnrService.getRates().enqueue(new Callback<ExchangeRoot>() {
//			@Override
//			public void onResponse(Call<ExchangeRoot> call, Response<ExchangeRoot> response) {
//				if (response.isSuccessful() && response.body() != null) {
//					exchangeRepository.saveAll(response.body().exchangeHistory());
//				}
//			}
//
//			@Override
//			public void onFailure(Call<ExchangeRoot> call, Throwable throwable) {
//				throwable.printStackTrace();
//			}
//		});
	}

}
