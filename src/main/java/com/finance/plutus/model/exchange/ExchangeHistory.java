package com.finance.plutus.model.exchange;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

/**
 * Plutus
 * Created by catalin on 28.09.2019
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class ExchangeHistory {

	@Id
	@GeneratedValue
	private Long id;
	@Version
	@Column(nullable = false)
	private Long version;
	@NotNull
	@Column(nullable = false)
	private Long date;
	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Currency currency;
	@NotNull
	@Column(nullable = false)
	private Double rate;
	@NotNull
	@Column(nullable = false)
	@Builder.Default
	private Integer multiplier = 1;

}
