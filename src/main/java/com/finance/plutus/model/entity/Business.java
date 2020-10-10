package com.finance.plutus.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

/** Plutus Created by catalin on 7/1/2020 */
@Getter
@Setter
@Entity
@Table(name = "businesses")
public class Business {

  @Id @GeneratedValue private UUID id;

  @NotBlank
  @Column(name = "name", nullable = false)
  private String name;

  @NotBlank
  @Column(name = "vat", nullable = false)
  private String vat;

  @Nullable
  @Column(name = "vat_in_vies")
  private String vies;

  @NotBlank
  @Column(name = "commercial_registry", nullable = false)
  private String commercialRegistry;

  @Nullable
  @Column(name = "email")
  private String email;

  @Nullable
  @Column(name = "phone")
  private String phone;

  @Nullable
  @Column(name = "website")
  private String website;

  @NotBlank
  @Column(name = "address", nullable = false)
  private String address;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @NotNull
  @Column(name = "created_on", nullable = false)
  private LocalDateTime createdOn;

  @NotNull
  @Column(name = "updated_on", nullable = false)
  private LocalDateTime updatedOn;
}
