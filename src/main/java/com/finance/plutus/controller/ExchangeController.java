package com.finance.plutus.controller;

import java.util.List;

import com.finance.plutus.api.ExchangeApi;
import com.finance.plutus.model.exchange.dto.ExchangeDto;
import com.finance.plutus.service.exchange.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * Plutus
 * Created by catalin on 08.10.2019
 */
@RestController
@RequiredArgsConstructor
public class ExchangeController implements ExchangeApi {

    private final ExchangeService exchangeService;

    @Override
    public List<ExchangeDto> findAllByDate(String date) {
        return exchangeService.findAllByDate(date);
    }

}
