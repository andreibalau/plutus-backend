package com.finance.plutus.model.exchange.dto;

import com.finance.plutus.model.exchange.ExchangeHistory;
import lombok.Data;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.finance.plutus.util.DateTimeUtils.DATE_FORMAT;
import static com.finance.plutus.util.DateTimeUtils.parse;

/**
 * Plutus
 * Created by catalin on 30.09.2019
 */
@Data
@Root(name = "DataSet", strict = false)
public class ExchangeRoot {
    @Element(name = "Header")
    private ExchangeHeader exchangeHeader;
    @Element(name = "Body")
    private ExchangeBody exchangeBody;
    @Attribute(name = "schemaLocation")
    private String schemaLocation;

    public List<ExchangeHistory> exchangeHistory() {
        List<ExchangeHistory> exchangeHistoryList = new ArrayList<>();
        String date = exchangeHeader.getDate();
        exchangeBody
                .getExchangeRateList()
                .forEach(exchangeRate -> {
                    exchangeHistoryList.add(ExchangeHistory
                            .builder()
                            .date(parse(date, DATE_FORMAT).toEpochDay())
                            .currency(exchangeRate.getCurrency())
                            .multiplier(Integer.parseInt(exchangeRate.getMultiplier()))
                            .rate(exchangeRate.getValue())
                            .build());
                });
        return exchangeHistoryList;
    }
}