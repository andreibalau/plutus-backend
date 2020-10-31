package com.finance.plutus.old.model.entity;

import com.finance.plutus.bank.model.Bank;
import com.finance.plutus.country.model.Country;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

/** Plutus Created by catalin on 7/1/2020 */
@Getter
@Setter
@Entity
@Table(name = "partners")
public class Partner {

  @Id @GeneratedValue private UUID id;

  @NotBlank
  @Column(name = "name", nullable = false)
  private String name;

  @Email
  @Nullable
  @Column(name = "email")
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
