package com.finance.plutus.model.transaction;

import com.finance.plutus.model.partner.Partner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Transaction {
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
	@Column(nullable = false, name = "date")
	private Long date;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, name = "type")
	private Type type;
	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private Partner vendor;
	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private Partner client;
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
	@OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
	private Set<TransactionLine> lines;
	@Builder.Default
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, name = "status")
	private Status status = Status.DRAFT;
	@Builder.Default
	@NotNull
	@Column(nullable = false, name = "can_be_deducted")
	private Boolean canBeDeducted = false;
}
