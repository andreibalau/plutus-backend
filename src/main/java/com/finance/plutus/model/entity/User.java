package com.finance.plutus.model.entity;

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

import lombok.Getter;
import lombok.Setter;

/** Plutus Created by catalin on 7/1/2020 */
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

  @Id @GeneratedValue private Long id;

  @NotNull
  @Column(nullable = false, name = "created_on")
  private LocalDateTime createdOn;

  @NotNull
  @Column(nullable = false, name = "updated_on")
  private LocalDateTime updatedOn;

  @NotBlank
  @Column(nullable = false, name = "password")
  private String password;

  @Email
  @NotBlank
  @Column(nullable = false, unique = true, name = "email")
  private String email;

  @Column(name = "phone")
  private String phone;

  @NotBlank
  @Column(nullable = false, name = "firstname")
  private String firstName;

  @NotBlank
  @Column(nullable = false, name = "lastname")
  private String lastName;

  @NotNull
  @OneToOne
  @JoinColumn(nullable = false, name = "business_id")
  private Business business;

  @NotNull
  @Enumerated(value = EnumType.STRING)
  @Column(nullable = false, name = "user_role")
  private UserRole role = UserRole.USER;
}
