package com.finance.plutus.bank;

import com.finance.plutus.app.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

/** Plutus Created by Catalin on 9/27/2020 */
@Getter
@Setter
@Entity
@Table(name = "banks")
public class Bank extends BaseModel {

  @Id @GeneratedValue private UUID id;

  @NotBlank
  @Column(name = "name", nullable = false)
  private String name;
}
