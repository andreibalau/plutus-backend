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
	@Column(nullable = false, name = "version")
	private Long version;
	@NotNull
	@Column(nullable = false, name = "date")
	private Long date;
	@NotNull
	@Column(nullable = false, name = "currency")
	@Enumerated(EnumType.STRING)
	private Currency currency;
	@NotNull
	@Column(nullable = false, name = "rate")
	private Double rate;
	@NotNull
	@Column(nullable = false, name = "multiplier")
	@Builder.Default
	private Integer multiplier = 1;

}
