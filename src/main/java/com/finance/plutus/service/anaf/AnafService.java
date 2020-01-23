package com.finance.plutus.service.anaf;

import java.util.List;

import com.finance.plutus.model.anaf.InfoRequestDto;
import com.finance.plutus.model.anaf.InfoResponseDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Plutus
 * Created by catalin on 1/20/2020
 */
public interface AnafService {
	@Headers("Content-Type: application/json")
	@POST("/PlatitorTvaRest/api/v4/ws/tva")
	Call<InfoResponseDto> findInfo(@Body List<InfoRequestDto> infoRequestDtoList);
}
