package com.finance.plutus.service.impl;

import java.time.LocalDateTime;

import com.finance.plutus.model.dto.CreateAddressDto;
import com.finance.plutus.model.dto.CreateBusinessDto;
import com.finance.plutus.model.dto.CreatePartnerDto;
import com.finance.plutus.model.entity.Address;
import com.finance.plutus.model.entity.Business;
import com.finance.plutus.model.entity.County;
import com.finance.plutus.model.entity.Partner;
import com.finance.plutus.repository.CountyRepository;
import com.finance.plutus.repository.PartnerRepository;
import com.finance.plutus.service.CheckEmailService;
import com.finance.plutus.service.CreatePartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Plutus Created by catalin on 7/2/2020 */
@Service
@RequiredArgsConstructor
public class CreatePartnerServiceImpl implements CreatePartnerService {

  private final PartnerRepository partnerRepository;
  private final CountyRepository countyRepository;
  private final CheckEmailService checkEmailService;

  @Override
  public Long create(CreatePartnerDto createPartnerDto, CreateBusinessDto createBusinessDto) {
    checkEmailService.checkPartnerEmailExistence(createPartnerDto.getEmail());
    Business business = createBusiness(createBusinessDto);
    Partner partner = createPartner(createPartnerDto, business);
    partnerRepository.save(partner);
    return partner.getId();
  }

  private Partner createPartner(CreatePartnerDto createPartnerDto, Business business) {
    Partner partner = new Partner();
    partner.setBusiness(business);
    partner.setCreatedOn(LocalDateTime.now());
    partner.setUpdatedOn(LocalDateTime.now());
    partner.setEmail(createPartnerDto.getEmail());
    partner.setFirstName(createPartnerDto.getFirstName());
    partner.setLastName(createPartnerDto.getLastName());
    partner.setPhone(createPartnerDto.getPhone());
    partner.setType(createPartnerDto.getType());
    return partner;
  }

  private Business createBusiness(CreateBusinessDto createBusinessDto) {
    Address address = createAddress(createBusinessDto.getCreateAddressDto());
    Business business = new Business();
    business.setCreatedOn(LocalDateTime.now());
    business.setUpdatedOn(LocalDateTime.now());
    business.setCui(createBusinessDto.getCui());
    business.setType(createBusinessDto.getType());
    business.setIban(createBusinessDto.getIban());
    business.setName(createBusinessDto.getName());
    business.setRegCom(createBusinessDto.getRegCom());
    business.setAddress(address);
    return business;
  }

  private Address createAddress(CreateAddressDto createAddressDto) {
    County county = countyRepository.getOne(createAddressDto.getCountyId());
    Address address = new Address();
    address.setCity(createAddressDto.getCity());
    address.setCounty(county);
    address.setName(createAddressDto.getName());
    address.setZip(createAddressDto.getZip());
    return address;
  }
}
