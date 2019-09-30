package com.finance.plutus.model.exchange;

import lombok.*;

import javax.persistence.*;
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
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = { "date", "currency" })
})
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
