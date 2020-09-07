package com.finance.plutus.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/** Plutus Created by catalin on 9/7/2020 */
@Getter
@Setter
@Entity
@Table(name = "serials")
public class Serial {

  @Id @GeneratedValue private Long id;

  @NotNull
  @Column(nullable = false, name = "created_on")
  private LocalDateTime createdOn;

  @NotNull
  @Column(nullable = false, name = "updated_on")
  private LocalDateTime updatedOn;

  @NotBlank
  @Column(nullable = false, name = "name", unique = true)
  private String name;

  @NotNull
  @Column(nullable = false, name = "start_number")
  private Integer startNumber;

  @NotNull
  @Column(nullable = false, name = "current_number")
  private Integer currentNumber;

  @NotNull
  @Column(nullable = false, name = "next_number")
  private Integer nextNumber;
}
