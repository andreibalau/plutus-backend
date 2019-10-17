package com.finance.plutus.model.transaction;

import com.finance.plutus.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class TransactionLine {
	@Id
	@GeneratedValue
	private Long id;
	@Version
	@Column(nullable = false, name = "version")
	private Long version;
	@NotNull
	@Column(nullable = false, name = "created_on")
	private Long createdOn;
	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private Transaction transaction;
	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private Product product;
	@NotBlank
	@Column(nullable = false, name = "uom")
	private String uom;
	@NotNull
	@Column(nullable = false, name = "quantity")
	private Double quantity;
	@NotNull
	@Column(nullable = false, name = "price")
	private Double price;
	@NotNull
	@Column(nullable = false, name = "subtotal")
	private Double subtotal;
	@NotNull
	@Column(nullable = false, name = "taxes")
	private Double taxes;
	@NotNull
	@Column(nullable = false, name = "total")
	private Double total;
}
