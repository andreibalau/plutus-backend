package com.finance.plutus.model.address.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.finance.plutus.model.address.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

	public static CityDto from(City city) {
		return new CityDto(city.getId(), city.getName());
	}
}
