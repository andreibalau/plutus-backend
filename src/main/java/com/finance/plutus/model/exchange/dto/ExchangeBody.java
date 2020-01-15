package com.finance.plutus.model.exchange.dto;

import java.util.List;

import com.finance.plutus.model.exchange.Currency;
import lombok.Getter;
import lombok.Setter;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

/**
 * Plutus
 * Created by catalin on 30.09.2019
 */
@Getter
@Setter
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
