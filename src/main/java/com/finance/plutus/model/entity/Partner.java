package com.finance.plutus.model.entity;

import lombok.Getter;
import lombok.Setter;

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

  @Id @GeneratedValue private Long id;

  @NotNull
  @Column(nullable = false, name = "created_on")
  private LocalDateTime createdOn;

  @NotNull
  @Column(nullable = false, name = "updated_on")
  private LocalDateTime updatedOn;

  @Email
  @NotBlank
  @Column(nullable = false, unique = true, name = "email")
  private String email;

  @Column(name = "phone")
  private String phone;

  @NotBlank
  @Column(nullable = false, name = "first_name")
  private String firstName;

  @NotBlank
  @Column(nullable = false, name = "last_name")
  private String lastName;

  @NotNull
  @Enumerated(value = EnumType.STRING)
  @Column(nullable = false, name = "partner_type")
  private PartnerType type;

  @NotNull
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(nullable = false, name = "business_id")
  private Business business;

  public String getName() {
    return firstName + " " + lastName;
  }
}