package com.finance.plutus.service.partner.impl;

import com.finance.plutus.exception.EntityNotFoundException;
import com.finance.plutus.model.dto.AddressDto;
import com.finance.plutus.model.dto.BusinessDto;
import com.finance.plutus.model.dto.CountyDto;
import com.finance.plutus.model.dto.PartnerDto;
import com.finance.plutus.model.dto.PreviewPartnerDto;
import com.finance.plutus.model.entity.Address;
import com.finance.plutus.model.entity.Business;
import com.finance.plutus.model.entity.County;
import com.finance.plutus.model.entity.Partner;
import com.finance.plutus.repository.PartnerRepository;
import com.finance.plutus.service.partner.FindPartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/** Plutus Created by catalin on 7/2/2020 */
@Service
@RequiredArgsConstructor
public class FindPartnerServiceImpl implements FindPartnerService {

  private final PartnerRepository partnerRepository;

  @Override
  public PartnerDto findDtoById(Long id) {
    Partner partner = findById(id);
    return map(partner);
  }

  @Override
  public Partner findById(Long id) {
    return partnerRepository.findById(id).orElseThrow(() ->
            new EntityNotFoundException("partner"));
  }

  @Override
  public List<PreviewPartnerDto> findAllByPage(int page, int size) {
    return partnerRepository.findAll(PageRequest.of(page, size)).stream()
        .map(p -> new PreviewPartnerDto(p.getId(), p.getName(), p.getEmail()))
        .collect(Collectors.toList());
  }

  private PartnerDto map(Partner partner) {
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
