package com.finance.plutus.service.partner.impl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.finance.plutus.model.dto.UpdateAddressDto;
import com.finance.plutus.model.dto.UpdateBusinessDto;
import com.finance.plutus.model.dto.UpdatePartnerDto;
import com.finance.plutus.model.entity.Address;
import com.finance.plutus.model.entity.Business;
import com.finance.plutus.model.entity.County;
import com.finance.plutus.model.entity.Partner;
import com.finance.plutus.repository.CountyRepository;
import com.finance.plutus.repository.PartnerRepository;
import com.finance.plutus.service.partner.FindPartnerService;
import com.finance.plutus.service.partner.UpdatePartnerService;
import com.finance.plutus.service.user.CheckEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Plutus Created by Catalin on 8/8/2020 */
@Service
@RequiredArgsConstructor
public class UpdatePartnerServiceImpl implements UpdatePartnerService {

  private final FindPartnerService findPartnerService;
  private final PartnerRepository partnerRepository;
  private final CheckEmailService checkEmailService;
  private final CountyRepository countyRepository;

  @Override
  @Transactional
  public void update(
      Long id, UpdatePartnerDto updatePartnerDto, UpdateBusinessDto updateBusinessDto) {
    Partner partner = findPartnerService.findById(id);
    checkEmailService.checkPartnerEmailExistence(updatePartnerDto.getEmail());
    updatePartner(partner, updatePartnerDto, updateBusinessDto);
  }

  private void updatePartner(
      Partner partner, UpdatePartnerDto updatePartnerDto, UpdateBusinessDto updateBusinessDto) {
    Business business = updateBusiness(partner.getBusiness(), updateBusinessDto);
    partner.setBusiness(business);
    partner.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    partner.setEmail(updatePartnerDto.getEmail());
    partner.setFirstName(updatePartnerDto.getFirstName());
    partner.setLastName(updatePartnerDto.getLastName());
    partner.setPhone(updatePartnerDto.getPhone());
    partner.setType(updatePartnerDto.getType());
    partnerRepository.save(partner);
  }

  private Business updateBusiness(Business business, UpdateBusinessDto updateBusinessDto) {
    Address address = updateAddress(business.getAddress(), updateBusinessDto.getAddress());
    business.setAddress(address);
    business.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    business.setCui(updateBusinessDto.getCui());
    business.setType(updateBusinessDto.getType());
    business.setIban(updateBusinessDto.getIban());
    business.setName(updateBusinessDto.getName());
    business.setRegCom(updateBusinessDto.getRegCom());
    return business;
  }

  private Address updateAddress(Address address, UpdateAddressDto updateAddressDto) {
    County county = countyRepository.getOne(updateAddressDto.getCountyId());
    address.setCounty(county);
    address.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    address.setCity(updateAddressDto.getCity());
    address.setName(updateAddressDto.getName());
    address.setZip(updateAddressDto.getZip());
    return address;
  }
}
