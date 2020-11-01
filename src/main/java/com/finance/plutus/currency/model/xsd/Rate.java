package com.finance.plutus.currency.model.xsd;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/** Plutus Created by Catalin on 10/5/2020 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "",
    propOrder = {"value"})
public class Rate {

  @XmlValue private Double value;

  @XmlAttribute(name = "currency", required = true)
  private String currency;

  @XmlAttribute(name = "multiplier")
  private Integer multiplier;
}
