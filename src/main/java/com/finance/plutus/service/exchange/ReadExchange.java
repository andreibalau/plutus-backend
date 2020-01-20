package com.finance.plutus.service.exchange;

import javax.annotation.PostConstruct;

import com.finance.plutus.repository.exchange.ExchangeRepository;
import com.finance.plutus.service.http.HttpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Plutus
 * Created by catalin on 28.09.2019
 */
@Component
@RequiredArgsConstructor
public class ReadExchange {

	private static final String BNR_ROOT = "https://www.bnr.ro";
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
