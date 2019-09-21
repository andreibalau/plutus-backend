package com.finance.plutus.model.invoice;

import com.finance.plutus.model.partner.Partner;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * Created by catalin on 21.09.2019
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Invoice {
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
	private Partner vendor;
	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private Partner client;
	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private Serial serial;
	@NotNull
	@Column(nullable = false)
	private Long date;
	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
	private Set<Line> lines;
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
