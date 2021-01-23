package com.finance.plutus.currency.xsd;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/** Plutus Created by Catalin on 10/5/2020 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "",
    propOrder = {"subject", "description", "origCurrency", "cubes"})
public class Body {

  @XmlElement(name = "Subject", namespace = "http://www.bnr.ro/xsd", required = true)
  private String subject;

  @XmlElement(name = "Description", namespace = "http://www.bnr.ro/xsd")
  private String description;

  @XmlElement(name = "OrigCurrency", namespace = "http://www.bnr.ro/xsd", required = true)
  private String origCurrency;

  @XmlElement(name = "Cube", namespace = "http://www.bnr.ro/xsd", required = true)
  private List<Cube> cubes;
}
