package com.finance.plutus.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/** Plutus Created by catalin on 7/2/2020 */
@Getter
@Setter
public class UserProfileDto {
  private String firstName;
  private String lastName;
  private String email;
  private String phone;
  private LocalDateTime createdOn;
  private LocalDateTime updatedOn;
}
