package com.finance.plutus.app;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/** Plutus Created by Catalin on 1/26/2021 */
@Getter
@Setter
@MappedSuperclass
public class BaseModel {

  @NotNull
  @Column(name = "created_on", nullable = false)
  private LocalDateTime createdOn;

  @NotNull
  @Column(name = "updated_on", nullable = false)
  private LocalDateTime updatedOn;
}
