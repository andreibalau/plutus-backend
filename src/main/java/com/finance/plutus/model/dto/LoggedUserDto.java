package com.finance.plutus.model.dto;

import lombok.*;

/** Plutus Created by catalin on 7/1/2020 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoggedUserDto {
  private String id;
  private String email;
  private String token;
}
