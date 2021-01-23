package com.finance.plutus.user;

import com.finance.plutus.app.payload.PlutusResponse;
import com.finance.plutus.user.dto.BusinessDto;
import org.springframework.security.oauth2.jwt.Jwt;

/** Plutus Created by Catalin on 11/1/2020 */
public interface UserFacadeService {

  PlutusResponse<BusinessDto> getBusiness(Jwt jwt);
}
