package com.finance.plutus.controller.exchange;

import com.finance.plutus.model.exchange.dto.ExchangeDto;
import com.finance.plutus.service.exchange.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Plutus
 * Created by catalin on 08.10.2019
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exchanges")
public class ExchangeController {

    private final ExchangeService exchangeService;

    @GetMapping
    public List<ExchangeDto> findAll() {
        return exchangeService.findAll();
    }

    @GetMapping("/{date}")
    public List<ExchangeDto> findAllByDate(@PathVariable String date) {
        return exchangeService.findAllByDate(date);
    }

}
