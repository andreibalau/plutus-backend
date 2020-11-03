package com.finance.plutus.user.service;

import com.finance.plutus.user.model.BusinessDto;
import org.springframework.security.oauth2.jwt.Jwt;

/** Plutus Created by Catalin on 11/1/2020 */
public interface UserService {

  BusinessDto getBusiness(Jwt jwt);
}
