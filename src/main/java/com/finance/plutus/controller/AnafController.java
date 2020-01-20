package com.finance.plutus.controller;

import com.finance.plutus.api.AnafApi;
import com.finance.plutus.model.anaf.InfoRequestDto;
import com.finance.plutus.model.anaf.InfoResponseDto;
import com.finance.plutus.service.anaf.FindInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * Plutus
 * Created by catalin on 1/20/2020
 */
@RestController
@RequiredArgsConstructor
public class AnafController implements AnafApi {

	private final FindInfoService findInfoService;

	@Override
	public InfoResponseDto find(InfoRequestDto infoRequest) {
		return findInfoService.findInfo(infoRequest);
	}

}
