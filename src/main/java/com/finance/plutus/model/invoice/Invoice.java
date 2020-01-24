package com.finance.plutus.model.invoice;

import com.finance.plutus.model.partner.Partner;
import com.finance.plutus.model.serial.Serial;
import lombok.Getter;
import lombok.Setter;

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
	private Partner vendor;
	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private Partner client;
	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private Serial serial;
	@NotBlank
	@Column(nullable = false, unique = true, name = "serial_name")
	private String serialName;
	@NotNull
	@Column(nullable = false, name = "date")
	private Long date;
	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
	private Set<InvoiceLine> lines = new HashSet<>();
	@NotNull
	@Column(nullable = false, name = "subtotal")
	private Double subtotal;
	@NotNull
	@Column(nullable = false, name = "taxes")
	private Double taxes;
	@NotNull
	@Column(nullable = false, name = "total")
	private Double total;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, name = "type")
	private Type type;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, name = "status")
	private Status status = Status.DRAFT;

}
