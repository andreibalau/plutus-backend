package com.finance.plutus.model.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Plutus
 * Created by catalin on 21.09.2019
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {
	@Id
	@GeneratedValue
	private Long id;
	@Version
	@Column(nullable = false)
	private Long version;
	@NotNull
	@Column(nullable = false)
	private Long createdOn;
	@NotBlank
	@Column(nullable = false)
	private String name;
	@NotBlank
	@Column(nullable = false)
	private String uom;
	@NotNull
	@Column(nullable = false)
	private Double price;
}
