package com.finance.plutus.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/** Plutus Created by catalin on 7/1/2020 */
@Getter
@Setter
@Entity
@Table(name = "businesses")
public class Business {

  @Id @GeneratedValue private Long id;

  @NotNull
  @Column(nullable = false, name = "created_on")
  private LocalDateTime createdOn;

  @NotNull
  @Column(nullable = false, name = "updated_on")
  private LocalDateTime updatedOn;

  @NotBlank
  @Column(nullable = false, name = "name")
  private String name;

  @NotNull
  @Enumerated(value = EnumType.STRING)
  @Column(nullable = false, name = "business_type")
  private BusinessType type;

  @NotBlank
  @Column(nullable = false, name = "iban")
  private String iban;

  @NotBlank
  @Column(nullable = false, name = "cui")
  private String cui;

  @Column(name = "reg_com")
  private String regCom;

  @NotNull
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(nullable = false, name = "address_id")
  private Address address;
}
