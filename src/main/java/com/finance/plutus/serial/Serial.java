package com.finance.plutus.serial;

import com.finance.plutus.app.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/** Plutus Created by catalin on 9/7/2020 */
@Getter
@Setter
@Entity
@Table(name = "serials")
public class Serial extends BaseModel {

  @Id @GeneratedValue private UUID id;

  @NotBlank
  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @NotNull
  @Column(name = "start_number", nullable = false)
  private Integer startNumber;

  @NotNull
  @Column(name = "current_number", nullable = false)
  private Integer currentNumber;

  @NotNull
  @Column(name = "next_number", nullable = false)
  private Integer nextNumber;
}
