package com.finance.plutus.currency.xsd;

import com.finance.plutus.currency.Currency;
import com.finance.plutus.currency.CurrencyRateDto;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** Plutus Created by Catalin on 10/5/2020 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "",
    propOrder = {"header", "body"})
@XmlRootElement(name = "DataSet", namespace = "http://www.bnr.ro/xsd")
public class DataSet {

  @XmlElement(name = "Header", namespace = "http://www.bnr.ro/xsd", required = true)
  private Header header;

  @XmlElement(name = "Body", namespace = "http://www.bnr.ro/xsd", required = true)
  private Body body;

  public List<CurrencyRateDto> mapCurrencyRatesDto() {
    List<CurrencyRateDto> currencyRateDtoList = new ArrayList<>();
    for (Cube cube : body.getCubes()) {
      XMLGregorianCalendar calendar = cube.getDate();
      LocalDate date = LocalDate.of(calendar.getYear(), calendar.getMonth(), calendar.getDay());
      currencyRateDtoList.addAll(
          cube.getRates().stream()
              .filter(rate -> rate.getCurrency().equals("EUR") || rate.getCurrency().equals("USD"))
              .map(
                  rate ->
                      CurrencyRateDto.builder()
                          .rate(rate.getValue())
                          .currency(Currency.valueOf(rate.getCurrency()))
                          .date(date)
                          .build())
              .collect(Collectors.toList()));
    }
    return currencyRateDtoList;
  }
}
