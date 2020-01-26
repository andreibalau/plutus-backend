package com.finance.plutus.model.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * Plutus
 * Created by catalin on 21.09.2019
 */
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	@Column(nullable = false, name = "created_on")
	private Long createdOn;
	@NotNull
	@Column(nullable = false, name = "updated_on")
	private Long updatedOn;
	@NotBlank
	@Column(nullable = false, name = "name")
	private String name;
	@Column(name = "uom")
	private String uom;
	@NotNull
	@Column(nullable = false, name = "unit_price")
	private Double unitPrice;
	@NotNull
	@Column(nullable = false, name = "tva")
	private Double tva;
	@NotNull
	@Column(nullable = false, name = "total_price")
	private Double totalPrice;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, name = "type")
	private Type type = Type.PRODUCT;

}
