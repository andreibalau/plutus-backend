package com.finance.plutus.service.user.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.finance.plutus.model.dto.PreviewCountyDto;
import com.finance.plutus.model.entity.Country;
import com.finance.plutus.model.entity.County;
import com.finance.plutus.repository.CountyRepository;
import com.finance.plutus.service.user.FindCountyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Plutus Created by Catalin on 7/29/2020 */
@Service
@RequiredArgsConstructor
public class FindCountyServiceImpl implements FindCountyService {

  private final CountyRepository countyRepository;

  @Override
  public List<PreviewCountyDto> findAll() {
    createDummyCounties();
    return countyRepository.findAll().stream().map(this::map).collect(Collectors.toList());
  }

  private PreviewCountyDto map(County county) {
    PreviewCountyDto previewCountyDto = new PreviewCountyDto();
    previewCountyDto.setId(county.getId());
    previewCountyDto.setName(county.getName());
    previewCountyDto.setAbbreviation(county.getAbbreviation());
    return previewCountyDto;
  }

  private void createDummyCounties() {
    County county = new County();
    Country country = new Country();
    country.setCreatedOn(LocalDateTime.now());
    country.setUpdatedOn(LocalDateTime.now());
    country.setName("Romania");
    county.setCountry(country);
    county.setCreatedOn(LocalDateTime.now());
    county.setUpdatedOn(LocalDateTime.now());
    county.setAbbreviation("PH");
    county.setName("Prahova");
    countyRepository.save(county);
  }
}
