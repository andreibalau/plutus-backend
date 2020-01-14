package com.finance.plutus.model.address.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.finance.plutus.model.address.Address;
import lombok.Getter;
import lombok.Setter;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Getter
@Setter
public class CreateAddressDto {
	@NotBlank
	private String street;
	@NotBlank
	private String number;
	private String additional;
	@NotNull
	private Long city;
	@NotNull
	private Long country;
	@NotNull
	private Long county;

	public Address toAddress() {
		return Address
				.builder()
				.street(street)
				.number(number)
				.additional(additional)
				.build();
	}
}
