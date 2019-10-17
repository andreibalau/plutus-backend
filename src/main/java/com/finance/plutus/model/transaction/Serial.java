package com.finance.plutus.model.transaction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
public class Serial {
	@Id
	@GeneratedValue
	private Long id;
	@Version
	@Column(nullable = false, name = "version")
	private Long version;
	@NotNull
	@Column(nullable = false, name = "created_on")
	private Long createdOn;
	@NotBlank
	@Column(nullable = false, name = "name")
	private String name;
	@NotNull
	@Column(nullable = false, name = "number")
	private Long number;
	@NotNull
	@Column(nullable = false, name = "max")
	private Long max;
	@NotNull
	@Column(nullable = false, name = "min")
	private Long min;
}
