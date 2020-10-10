package com.finance.plutus.model.xsd;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/** Plutus Created by Catalin on 10/5/2020 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "LT_Header",
    namespace = "http://www.bnr.ro/xsd",
    propOrder = {"publisher", "publishingDate", "messageType"})
public class Header {

  @XmlElement(name = "Publisher", namespace = "http://www.bnr.ro/xsd", required = true)
  private String publisher;

  @XmlElement(name = "PublishingDate", namespace = "http://www.bnr.ro/xsd", required = true)
  @XmlSchemaType(name = "date")
  private XMLGregorianCalendar publishingDate;

  @XmlElement(name = "MessageType", namespace = "http://www.bnr.ro/xsd", required = true)
  private String messageType;
}
