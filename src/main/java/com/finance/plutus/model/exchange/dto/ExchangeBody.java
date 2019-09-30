package com.finance.plutus.model.exchange.dto;

import com.finance.plutus.model.exchange.Currency;
import lombok.Data;
import org.simpleframework.xml.*;

import java.util.List;

/**
 * Plutus
 * Created by catalin on 30.09.2019
 */
@Data
@Root(name = "Body", strict = false)
public class ExchangeBody {
    @Element(name = "Subject")
    private String subject;
    @Element(name = "OrigCurrency")
    private Currency originalCurrency;
    @ElementList(name = "Rate", inline = true)
    @Path(value = "Cube")
    private List<ExchangeRate> exchangeRateList;
    @Path(value = "Cube")
    @Attribute(name = "date")
    private String date;
}
