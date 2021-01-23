package com.finance.plutus.partner.impl;

import com.finance.plutus.app.exception.EntityNotFoundException;
import com.finance.plutus.bank.Bank;
import com.finance.plutus.bank.BankService;
import com.finance.plutus.country.Country;
import com.finance.plutus.country.CountryService;
import com.finance.plutus.partner.Partner;
import com.finance.plutus.partner.PartnerMapper;
import com.finance.plutus.partner.PartnerRepository;
import com.finance.plutus.partner.PartnerService;
import com.finance.plutus.partner.dto.CreatePartnerDto;
import com.finance.plutus.partner.dto.UpdatePartnerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 1/23/2021 */
@Service
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService {

  private final PartnerMapper partnerMapper;
  private final PartnerRepository partnerRepository;
  private final BankService bankService;
  private final CountryService countryService;

  @Override
  @Transactional
  public void delete(UUID id) {
    Partner partner = findById(id);
    partnerRepository.delete(partner);
  }

  @Override
  @Transactional
  public UUID create(CreatePartnerDto createPartnerDto) {
    Partner partner = partnerMapper.mapToEntity(createPartnerDto);
    setBankAndCountryOnPartner(
        partner, createPartnerDto.getBankId(), createPartnerDto.getCountryCode());
    partnerRepository.save(partner);
    return partner.getId();
  }

  @Override
  public Partner findById(UUID id) {
    return partnerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("partner"));
  }

  @Override
  public List<Partner> findAll(int page, int size) {
    return partnerRepository.findAll(PageRequest.of(page, size)).toList();
  }

  @Override
  @Transactional
  public void update(UUID id, UpdatePartnerDto updatePartnerDto) {
    Partner partner = findById(id);
    Partner updatedPartner = partnerMapper.mapToEntity(partner, updatePartnerDto);
    setBankAndCountryOnPartner(
        updatedPartner, updatePartnerDto.getBankId(), updatePartnerDto.getCountryCode());
    partnerRepository.save(updatedPartner);
  }

  private void setBankAndCountryOnPartner(Partner partner, UUID bankId, String countryCode) {
    if (bankId != null) {
      Bank bank = bankService.findById(bankId);
      partner.setBank(bank);
    }
    Country country = countryService.findByCode(countryCode);
    partner.setCountry(country);
  }
}
