package com.finance.plutus.model.exchange.dto;

import com.finance.plutus.model.exchange.Currency;
import com.finance.plutus.model.exchange.ExchangeHistory;
import lombok.Builder;
import lombok.Data;

/**
 * Plutus
 * Created by catalin on 08.10.2019
 */
@Data
@Builder
public class ExchangeDto {
    private Long date;
    private Currency currency;
    private Double rate;
    private Integer multiplier;

    public static ExchangeDto from(ExchangeHistory exchangeHistory) {
        return ExchangeDto
                .builder()
                .date(exchangeHistory.getDate() * 24 * 60 * 60 * 1000L)
                .currency(exchangeHistory.getCurrency())
                .rate(exchangeHistory.getRate())
                .multiplier(exchangeHistory.getMultiplier())
                .build();
    }
}
