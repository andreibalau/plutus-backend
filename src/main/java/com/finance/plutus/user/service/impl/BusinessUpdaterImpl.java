package com.finance.plutus.user.service.impl;

import com.finance.plutus.bank.service.BankFinder;
import com.finance.plutus.user.model.Business;
import com.finance.plutus.user.model.BusinessDto;
import com.finance.plutus.user.model.User;
import com.finance.plutus.user.repository.BusinessRepository;
import com.finance.plutus.user.service.BusinessUpdater;
import com.finance.plutus.user.service.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class BusinessUpdaterImpl implements BusinessUpdater {

  private final BankFinder bankFinder;
  private final UserFinder userFinder;
  private final BusinessRepository businessRepository;

  @Override
  @Transactional
  public void update(UUID userId, BusinessDto businessDto) {
    User user = userFinder.findById(userId);
    Business business = userFinder.getBusiness();
    business.setName(businessDto.getName());
    business.setEmail(businessDto.getEmail());
    business.setPhone(businessDto.getPhone());
    business.setVat(businessDto.getVat());
    business.setVies(businessDto.getVies());
    business.setWebsite(businessDto.getWebsite());
    business.setAddress(businessDto.getAddress());
    business.setBank(bankFinder.findById(businessDto.getBankId()));
    business.setBankAccount(businessDto.getBankAccount());
    business.setCommercialRegistry(businessDto.getCommercialRegistry());
    business.setUser(user);
    businessRepository.save(business);
  }
}
