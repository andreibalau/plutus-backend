package com.finance.plutus.model.dto;

import com.finance.plutus.model.entity.Business;
import com.finance.plutus.model.entity.Partner;
import com.finance.plutus.model.entity.PartnerType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/** Plutus Created by catalin on 7/2/2020 */
@Getter
@Setter
public class PartnerDto {
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private PartnerType type;
  private String phone;
  private LocalDateTime createdOn;
  private LocalDateTime updatedOn;
  private BusinessDto business;

  public static PartnerDto mapFromEntity(Partner partner) {
    PartnerDto partnerDto = new PartnerDto();
    partnerDto.setId(partner.getId());
    partnerDto.setCreatedOn(partner.getCreatedOn());
    partnerDto.setUpdatedOn(partner.getUpdatedOn());
    partnerDto.setEmail(partner.getEmail());
    partnerDto.setPhone(partner.getPhone());
    partnerDto.setType(partner.getType());
    partnerDto.setFirstName(partner.getFirstName());
    partnerDto.setLastName(partner.getLastName());

    Business business = partner.getBusiness();
    BusinessDto businessDto = new BusinessDto();
    businessDto.setCui(business.getCui());
    businessDto.setIban(business.getIban());
    businessDto.setName(business.getName());
    businessDto.setRegCom(business.getRegCom());
    businessDto.setType(business.getType());

    Address address = business.getAddress();
    AddressDto addressDto = new AddressDto();
    addressDto.setCity(address.getCity());
    addressDto.setName(address.getName());
    addressDto.setZip(address.getZip());
    CountyDto countyDto = new CountyDto();
    countyDto.setId(address.getCounty().getId());
    countyDto.setName(address.getCounty().getName());
    addressDto.setCounty(countyDto);

    businessDto.setAddress(addressDto);

    partnerDto.setBusiness(businessDto);
    return partnerDto;
  }
}
