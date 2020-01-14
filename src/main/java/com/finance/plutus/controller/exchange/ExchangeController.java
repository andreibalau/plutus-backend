package com.finance.plutus.controller.exchange;

import java.util.List;

import com.finance.plutus.model.exchange.dto.ExchangeDto;
import com.finance.plutus.service.exchange.ExchangeService;
import com.finance.plutus.util.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Plutus
 * Created by catalin on 08.10.2019
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(Api.EXCHANGES)
public class ExchangeController {

    private final ExchangeService exchangeService;

    @GetMapping
    public List<ExchangeDto> findAll() {
        return exchangeService.findAll();
    }

    @GetMapping(Api.EXCHANGES + "/{date}")
    public List<ExchangeDto> findAllByDate(@PathVariable String date) {
        return exchangeService.findAllByDate(date);
    }

}
