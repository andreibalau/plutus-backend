package com.finance.plutus.model.address.dto;

import com.finance.plutus.model.address.Address;
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
public class AddressDto {
	private Long id;
	@NotBlank
	private String street;
	@NotBlank
	private String number;
	private String additional;
	@NotNull
	private CityDto city;
	@NotNull
	private CountryDto country;
	@NotNull
	private CountyDto county;

	public Address toAddress() {
		return Address
				.builder()
				.id(id)
				.street(street)
				.number(number)
				.additional(additional)
				.city(city.toCity())
				.country(country.toCountry())
				.county(county.toCounty())
				.build();
	}
}
