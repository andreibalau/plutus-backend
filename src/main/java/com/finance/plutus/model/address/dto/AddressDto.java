package com.finance.plutus.model.address.dto;

import com.finance.plutus.model.address.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

	public static AddressDto from(Address address) {
		return AddressDto
				.builder()
				.id(address.getId())
				.street(address.getStreet())
				.number(address.getNumber())
				.additional(address.getAdditional())
				.city(CityDto.from(address.getCity()))
				.country(CountryDto.from(address.getCountry()))
				.county(CountyDto.from(address.getCounty()))
				.build();
	}
}
