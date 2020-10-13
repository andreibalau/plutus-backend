package com.finance.plutus.service.user.impl;

import com.finance.plutus.model.dto.BusinessDto;
import com.finance.plutus.model.entity.Business;
import com.finance.plutus.repository.BusinessRepository;
import com.finance.plutus.service.user.FindBusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/** Plutus Created by Catalin on 10/13/2020 */
@Service
@RequiredArgsConstructor
public class FindBusinessServiceImpl implements FindBusinessService {

  private final BusinessRepository businessRepository;

  @Override
  public BusinessDto getDto() {
    Business business = getEntity();
    return BusinessDto.mapFromEntity(business);
  }

  @Override
  public Business getEntity() {
    return businessRepository.findAll().stream().findFirst().orElse(createBusiness());
  }

  private Business createBusiness() {
    Business business = new Business();
    business.setCreatedOn(LocalDateTime.now(ZoneOffset.UTC));
    business.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
    return business;
  }
}
