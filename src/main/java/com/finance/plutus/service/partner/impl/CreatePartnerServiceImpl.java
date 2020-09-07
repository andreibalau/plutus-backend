package com.finance.plutus.service.partner.impl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.finance.plutus.model.dto.CreateAddressDto;
import com.finance.plutus.model.dto.CreateBusinessDto;
import com.finance.plutus.model.dto.CreatePartnerDto;
import com.finance.plutus.model.entity.Address;
import com.finance.plutus.model.entity.Business;
import com.finance.plutus.model.entity.County;
import com.finance.plutus.model.entity.Partner;
import com.finance.plutus.repository.CountyRepository;
import com.finance.plutus.repository.PartnerRepository;
import com.finance.plutus.service.partner.CreatePartnerService;
import com.finance.plutus.service.user.CheckEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Plutus Created by catalin on 7/2/2020 */
@Service
@RequiredArgsConstructor
public class CreatePartnerServiceImpl implements CreatePartnerService {

  private final PartnerRepository partnerRepository;
  private final CountyRepository countyRepository;
  private final CheckEmailService checkEmailService;

  @Override
  @Transactional
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
    partner.setCreatedOn(LocalDateTime.now(ZoneOffset.UTC));
    partner.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    partner.setEmail(createPartnerDto.getEmail());
    partner.setFirstName(createPartnerDto.getFirstName());
    partner.setLastName(createPartnerDto.getLastName());
    partner.setPhone(createPartnerDto.getPhone());
    partner.setType(createPartnerDto.getType());
    return partner;
  }

  private Business createBusiness(CreateBusinessDto createBusinessDto) {
    Address address = createAddress(createBusinessDto.getAddress());
    Business business = new Business();
    business.setCreatedOn(LocalDateTime.now(ZoneOffset.UTC));
    business.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
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
    address.setCreatedOn(LocalDateTime.now(ZoneOffset.UTC));
    address.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    address.setCity(createAddressDto.getCity());
    address.setCounty(county);
    address.setName(createAddressDto.getName());
    address.setZip(createAddressDto.getZip());
    return address;
  }
}
