package com.finance.plutus.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/** Plutus Created by catalin on 9/7/2020 */
@Getter
@Setter
@Entity
@Table(name = "serials")
public class Serial {

  @Id @NonNull private String id;

  @NonNull
  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @NonNull
  @Column(name = "start_number", nullable = false)
  private Integer startNumber;

  @NonNull
  @Column(name = "current_number", nullable = false)
  private Integer currentNumber;

  @NonNull
  @Column(name = "next_number", nullable = false)
  private Integer nextNumber;

  @NonNull
  @Column(name = "created_on", nullable = false)
  private LocalDateTime createdOn;

  @NonNull
  @Column(name = "updated_on", nullable = false)
  private LocalDateTime updatedOn;
}
