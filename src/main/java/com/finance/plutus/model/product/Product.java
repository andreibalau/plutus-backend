package com.finance.plutus.model.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Plutus
 * Created by catalin on 21.09.2019
 */
@Getter
@Setter
@Entity
public class Product {

	@Id
	@GeneratedValue
	private Long id;
	@Version
	@Column(nullable = false, name = "version")
	private Long version;
	@NotNull
	@Column(nullable = false, name = "created_on")
	private Long createdOn;
	@NotBlank
	@Column(nullable = false, name = "name")
	private String name;
	@NotBlank
	@Column(nullable = false, name = "uom")
	private String uom;
	@NotNull
	@Column(nullable = false, name = "price")
	private Double price;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, name = "type")
	@Builder.Default
	private Type type = Type.PRODUCT;

}
