package com.finance.plutus.service.anaf;

import com.finance.plutus.model.anaf.InfoRequestDto;
import com.finance.plutus.model.anaf.InfoResponseDto;
import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Plutus
 * Created by catalin on 1/20/2020
 */
public interface AnafService {
	@POST("/PlatitorTvaRest/api/v4/ws/tva")
	Call<InfoResponseDto> findInfo(InfoRequestDto infoRequestDto);
}
