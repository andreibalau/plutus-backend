package com.finance.plutus.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;

/** Plutus Created by catalin on 7/1/2020 */
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

  @Id @NonNull private String id;

  @Email
  @NonNull
  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @NonNull
  @Column(name = "password", nullable = false)
  private String password;

  @NonNull
  @Enumerated(value = EnumType.STRING)
  @Column(name = "user_role", nullable = false)
  private UserRole role = UserRole.USER;

  @NonNull
  @Column(name = "created_on", nullable = false)
  private LocalDateTime createdOn;

  @NonNull
  @Column(name = "updated_on", nullable = false)
  private LocalDateTime updatedOn;
}
