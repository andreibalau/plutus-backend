package com.finance.plutus.model.transaction.invoice;

import com.finance.plutus.model.product.Product;
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
 * Created by catalin on 21.09.2019
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class InvoiceLine {
	@Id
	@GeneratedValue
	private Long id;
	@Version
	@Column(nullable = false)
	private Long version;
	@NotNull
	@Column(nullable = false)
	private Long createdOn;
	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private Invoice invoice;
	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private Product product;
	@NotBlank
	@Column(nullable = false)
	private String uom;
	@NotNull
	@Column(nullable = false)
	private Double quantity;
	@NotNull
	@Column(nullable = false)
	private Double price;
	@NotNull
	@Column(nullable = false)
	private Double subtotal;
	@NotNull
	@Column(nullable = false)
	private Double taxes;
	@NotNull
	@Column(nullable = false)
	private Double total;
}
