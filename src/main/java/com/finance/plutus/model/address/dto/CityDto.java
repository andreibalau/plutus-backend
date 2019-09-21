package com.finance.plutus.model.address.dto;

import com.finance.plutus.model.address.City;
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
public class CityDto {
	@NotNull
	private Long id;
	@NotBlank
	private String name;

	public City toCity() {
		return City
				.builder()
				.id(id)
				.name(name)
				.build();
	}
}
