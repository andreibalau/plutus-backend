package com.finance.plutus.service.user.impl;

import com.finance.plutus.model.dto.BusinessDto;
import com.finance.plutus.model.entity.Business;
import com.finance.plutus.model.entity.User;
import com.finance.plutus.repository.BusinessRepository;
import com.finance.plutus.service.bank.FindBankService;
import com.finance.plutus.service.user.FindBusinessService;
import com.finance.plutus.service.user.FindUserService;
import com.finance.plutus.service.user.UpdateBusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/** Plutus Created by Catalin on 10/13/2020 */
@Service
@RequiredArgsConstructor
public class UpdateBusinessServiceImpl implements UpdateBusinessService {

  private final FindBankService findBankService;
  private final FindBusinessService findBusinessService;
  private final BusinessRepository businessRepository;
  private final FindUserService findUserService;

  @Override
  @Transactional
  public void update(UUID userId, BusinessDto businessDto) {
    User user = findUserService.findById(userId);
    Business business = findBusinessService.getEntity();
    business.setName(businessDto.getName());
    business.setEmail(businessDto.getEmail());
    business.setPhone(businessDto.getPhone());
    business.setVat(businessDto.getVat());
    business.setVies(businessDto.getVies());
    business.setWebsite(businessDto.getWebsite());
    business.setAddress(businessDto.getAddress());
    business.setBank(findBankService.findEntityById(businessDto.getBankId()));
    business.setBankAccount(businessDto.getBankAccount());
    business.setCommercialRegistry(businessDto.getCommercialRegistry());
    business.setUser(user);
    businessRepository.save(business);
  }
}
