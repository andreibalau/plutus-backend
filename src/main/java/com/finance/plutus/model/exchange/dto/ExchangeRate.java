package com.finance.plutus.model.exchange.dto;

import com.finance.plutus.model.exchange.Currency;
import lombok.Getter;
import lombok.Setter;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

/**
 * Plutus
 * Created by catalin on 30.09.2019
 */
@Getter
@Setter
@Root(name = "Rate", strict = false)
public class ExchangeRate {
    @Text
    private Double value;
    @Attribute(name = "currency")
    private Currency currency;
    @Attribute(name = "multiplier", required = false, empty = "1")
    private String multiplier;
}
