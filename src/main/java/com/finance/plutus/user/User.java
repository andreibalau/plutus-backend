package com.finance.plutus.user;

import com.finance.plutus.app.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/** Plutus Created by catalin on 7/1/2020 */
@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseModel {

  @Id @GeneratedValue private UUID id;

  @Email
  @NotBlank
  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @NotNull
  @Enumerated(value = EnumType.STRING)
  @Column(name = "user_role", nullable = false)
  private UserRole role = UserRole.USER;
}
