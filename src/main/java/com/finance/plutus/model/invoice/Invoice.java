package com.finance.plutus.model.invoice;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

import com.finance.plutus.model.exchange.Currency;
import com.finance.plutus.model.partner.Partner;
import com.finance.plutus.model.serial.Serial;
import lombok.Getter;
import lombok.Setter;

/**
 * Plutus
 * Created by catalin on 21.09.2019
 */
@Getter
@Setter
@Entity
@Table(name = "invoices")
public class Invoice {

	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	@Column(nullable = false, name = "created_on")
	private Long createdOn;
	@NotNull
	@Column(nullable = false, name = "updated_on")
	private Long updatedOn;
	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private Partner partner;
	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private Serial serial;
	@NotBlank
	@Column(nullable = false, unique = true, name = "serial_name")
	private String name;
	@NotNull
	@Column(nullable = false, name = "date")
	private Long date;
	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
	private Set<InvoiceLine> lines = new HashSet<>();
	@NotNull
	@Column(nullable = false, name = "subtotal")
	private Double subtotal = 0D;
	@NotNull
	@Column(nullable = false, name = "taxes")
	private Double taxes = 0D;
	@NotNull
	@Column(nullable = false, name = "total")
	private Double total = 0D;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, name = "type")
	private Type type;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, name = "status")
	private Status status = Status.DRAFT;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, name = "currency")
	private Currency currency = Currency.RON;

}
