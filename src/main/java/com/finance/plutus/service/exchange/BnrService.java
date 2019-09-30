package com.finance.plutus.service.exchange;

import com.finance.plutus.model.exchange.dto.ExchangeRoot;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Plutus
 * Created by catalin on 28.09.2019
 */
public interface BnrService {
	@GET("/nbrfxrates.xml")
	Call<ExchangeRoot> getRates();
}
