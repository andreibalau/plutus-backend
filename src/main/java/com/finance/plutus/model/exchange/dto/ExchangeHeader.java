package com.finance.plutus.model.exchange.dto;

import lombok.Getter;
import lombok.Setter;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Plutus
 * Created by catalin on 30.09.2019
 */
@Getter
@Setter
@Root(name = "Header", strict = false)
public class ExchangeHeader {
    @Element(name = "Publisher")
    private String publisher;
    @Element(name = "PublishingDate")
    private String date;
    @Element(name = "MessageType")
    private String messageType;
}
