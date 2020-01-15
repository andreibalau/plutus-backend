package com.finance.plutus.model.exchange.dto;

import com.finance.plutus.model.exchange.Currency;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Plutus
 * Created by catalin on 08.10.2019
 */
@Getter
@Setter
@Builder
public class ExchangeDto {
    private Long date;
    private Currency currency;
    private Double rate;
    private Integer multiplier;
}
