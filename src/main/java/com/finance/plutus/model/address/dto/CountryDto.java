package com.finance.plutus.model.address.dto;

import com.finance.plutus.model.address.Country;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Getter
@Setter
public class CountryDto {
	@NotNull
	private Long id;
	@NotBlank
	private String name;

	public Country toCountry() {
		return Country
				.builder()
				.id(id)
				.name(name)
				.build();
	}
}
