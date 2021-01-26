package com.finance.plutus.country;

import com.finance.plutus.app.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/** Plutus Created by catalin on 7/1/2020 */
@Getter
@Setter
@Entity
@Table(name = "countries")
public class Country extends BaseModel {

  @Id @NotBlank private String code;

  @NotBlank
  @Column(name = "name", nullable = false)
  private String name;
}
