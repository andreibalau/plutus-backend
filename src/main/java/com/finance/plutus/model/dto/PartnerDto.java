package com.finance.plutus.model.dto;

import com.finance.plutus.model.entity.PartnerType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/** Plutus Created by catalin on 7/2/2020 */
@Getter
@Setter
public class PartnerDto {
  private Long id;
  private String name;
  private String email;
  private PartnerType type;
  private String phone;
  private LocalDateTime createdOn;
  private LocalDateTime updatedOn;
}
