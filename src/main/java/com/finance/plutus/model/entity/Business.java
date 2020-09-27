package com.finance.plutus.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;

/** Plutus Created by catalin on 7/1/2020 */
@Getter
@Setter
@Entity
@Table(name = "businesses")
public class Business {

  @Id @NonNull private String id;

  @NonNull
  @Column(name = "name", nullable = false)
  private String name;

  @NonNull
  @Column(name = "vat", nullable = false)
  private String vat;

  @Nullable
  @Column(name = "vat_in_vies")
  private String vies;

  @NonNull
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

  @NonNull
  @Column(name = "address", nullable = false)
  private String address;

  @NonNull
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @NonNull
  @Column(name = "created_on", nullable = false)
  private LocalDateTime createdOn;

  @NonNull
  @Column(name = "updated_on", nullable = false)
  private LocalDateTime updatedOn;
}
