package com.finance.plutus.model.address.dto;

import com.finance.plutus.model.address.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

	public static CountryDto from(Country country) {
		return new CountryDto(country.getId(), country.getName());
	}
}
