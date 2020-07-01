package com.finance.plutus.service;

import org.springframework.lang.NonNull;

/** Plutus Created by catalin on 7/1/2020 */
public interface CheckEmailService {
  boolean exists(@NonNull String email);
}
