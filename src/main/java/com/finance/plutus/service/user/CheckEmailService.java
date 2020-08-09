package com.finance.plutus.service.user;

import org.springframework.lang.NonNull;

/** Plutus Created by catalin on 7/1/2020 */
public interface CheckEmailService {
  void checkUserEmailExistence(@NonNull String email);

  void checkPartnerEmailExistence(@NonNull String email);
}
