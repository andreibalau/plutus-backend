package com.finance.plutus.model.serial;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * Plutus
 * Created by catalin on 21.09.2019
 */
@Getter
@Setter
@Entity
@Table(name = "serials")
public class Serial {

	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	@Column(nullable = false, name = "created_on")
	private Long createdOn;
	@NotNull
	@Column(nullable = false, name = "updated_on")
	private Long updatedOn;
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
