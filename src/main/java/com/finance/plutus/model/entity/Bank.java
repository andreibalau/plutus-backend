package com.finance.plutus.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/** Plutus Created by Catalin on 9/27/2020 */
@Getter
@Setter
@Entity
@Table(name = "banks")
public class Bank {

  @Id @NonNull private String id;

  @NonNull
  @Column(name = "name", nullable = false)
  private String name;

  @NonNull
  @Column(name = "created_on", nullable = false)
  private LocalDateTime createdOn;

  @NonNull
  @Column(name = "updated_on", nullable = false)
  private LocalDateTime updatedOn;
}
