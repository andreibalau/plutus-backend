package com.finance.plutus.partner.service;

import com.finance.plutus.partner.model.Partner;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

/** Plutus Created by Catalin on 11/1/2020 */
public interface PartnerFinder {
  Partner findById(UUID id);

  List<Partner> findAll(PageRequest page);

  long count();
}
