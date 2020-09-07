package com.finance.plutus.model.entity;

import javax.persistence.CascadeType;
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

import lombok.Getter;
import lombok.Setter;

/** Plutus Created by catalin on 7/1/2020 */
@Getter
@Setter
@Entity
@Table(name = "counties")
public class County {

  @Id @GeneratedValue private Long id;

  @NotNull
  @Column(nullable = false, name = "created_on")
  private LocalDateTime createdOn;

  @NotNull
  @Column(nullable = false, name = "updated_on")
  private LocalDateTime updatedOn;

  @NotBlank
  @Column(nullable = false, name = "name")
  private String name;

  @NotBlank
  @Column(nullable = false, name = "abbreviation")
  private String abbreviation;

  @NotNull
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(nullable = false, name = "country_id")
  private Country country;
}
