package com.finance.plutus.service.anaf;

import java.io.IOException;

import com.finance.plutus.exception.AnafException;
import com.finance.plutus.model.anaf.InfoRequestDto;
import com.finance.plutus.model.anaf.InfoResponseDto;
import com.finance.plutus.service.http.HttpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import retrofit2.Response;

/**
 * Plutus
 * Created by catalin on 1/20/2020
 */
@Service
@RequiredArgsConstructor
public class FindInfoService {

	private static final String ANAF_URL = "https://webservicesp.anaf.ro";
	private final HttpClient httpClient;

	public InfoResponseDto findInfo(InfoRequestDto infoRequestDto) {
		AnafService service = httpClient
				.getInstance(ANAF_URL)
				.create(AnafService.class);
		try {
			Response<InfoResponseDto> response = service.findInfo(infoRequestDto).execute();
			if (response.isSuccessful()) {
				return response.body();
			} else {
				throw new AnafException("Something went wrong calling ANAF servers!", HttpStatus.valueOf(response.code()));
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new AnafException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
