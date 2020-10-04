package com.finance.plutus.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/** Plutus Created by catalin on 7/1/2020 */
@Getter
@Setter
@Entity
@Table(name = "partners")
public class Partner {

  @Id @NotBlank private String id;

  @NotBlank
  @Column(name = "name", nullable = false)
  private String name;

  @Email
  @Nullable
  @Column(name = "email", unique = true)
  private String email;

  @Nullable
  @Column(name = "phone")
  private String phone;

  @Nullable
  @Column(name = "vat")
  private String vat;

  @Nullable
  @Column(name = "commercial_registry")
  private String commercialRegistry;

  @Nullable
  @Column(name = "address")
  private String address;

  @Nullable
  @Column(name = "term_in_days")
  private Integer termInDays;

  @Nullable
  @Column(name = "bank_account")
  private String bankAccount;

  @Nullable
  @ManyToOne
  @JoinColumn(name = "bank_id")
  private Bank bank;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "country_id", nullable = false)
  private Country country;

  @NotNull
  @Enumerated(value = EnumType.STRING)
  @Column(name = "business_type", nullable = false)
  private BusinessType businessType = BusinessType.INDIVIDUAL;

  @NotNull
  @Enumerated(value = EnumType.STRING)
  @Column(name = "partner_type", nullable = false)
  private PartnerType type;

  @NotNull
  @Column(name = "created_on", nullable = false)
  private LocalDateTime createdOn;

  @NotNull
  @Column(name = "updated_on", nullable = false)
  private LocalDateTime updatedOn;
}
