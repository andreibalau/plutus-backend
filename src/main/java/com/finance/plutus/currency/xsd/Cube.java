package com.finance.plutus.currency.xsd;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.List;

/** Plutus Created by Catalin on 10/5/2020 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "LT_Cube",
    namespace = "http://www.bnr.ro/xsd",
    propOrder = {"rates"})
public class Cube {

  @XmlElement(name = "Rate", namespace = "http://www.bnr.ro/xsd", required = true)
  private List<Rate> rates;

  @XmlAttribute(name = "date", required = true)
  @XmlSchemaType(name = "date")
  private XMLGregorianCalendar date;
}
