package com.finance.plutus.bank.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

/** Plutus Created by Catalin on 9/27/2020 */
@Getter
@Setter
@Entity
@Table(name = "banks")
public class Bank {

  @Id @GeneratedValue private UUID id;

  @NotBlank
  @Column(name = "name", nullable = false)
  private String name;

  @NotNull
  @Column(name = "created_on", nullable = false)
  private LocalDateTime createdOn;

  @NotNull
  @Column(name = "updated_on", nullable = false)
  private LocalDateTime updatedOn;
}
