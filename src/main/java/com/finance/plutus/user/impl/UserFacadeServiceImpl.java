package com.finance.plutus.user.impl;

import com.finance.plutus.app.payload.PlutusResponse;
import com.finance.plutus.user.Business;
import com.finance.plutus.user.BusinessMapper;
import com.finance.plutus.user.UserFacadeService;
import com.finance.plutus.user.UserService;
import com.finance.plutus.user.dto.BusinessDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

/** Plutus Created by Catalin on 11/1/2020 */
@Service
@RequiredArgsConstructor
public class UserFacadeServiceImpl implements UserFacadeService {

  private final BusinessMapper businessMapper;
  private final UserService userService;

  @Override
  public PlutusResponse<BusinessDto> getBusiness(Jwt jwt) {
    String username = jwt.getClaim("username");
    Business business = userService.getBusiness(username);
    BusinessDto businessDto = businessMapper.mapToDto(business);
    return new PlutusResponse<>(businessDto);
  }
}
