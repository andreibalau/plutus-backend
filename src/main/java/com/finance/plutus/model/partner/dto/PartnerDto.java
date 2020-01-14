package com.finance.plutus.model.partner.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.finance.plutus.model.partner.Partner;
import lombok.Builder;
import lombok.Data;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Builder
@Data
public class PartnerDto {
	@NotNull
	private Long id;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@NotBlank
	private String email;
	@NotBlank
	private String phone;

	public static PartnerDto from(Partner partner) {
		return PartnerDto
				.builder()
				.id(partner.getId())
				.firstName(partner.getFirstName())
				.lastName(partner.getLastName())
				.email(partner.getEmail())
				.phone(partner.getPhone())
				.build();
	}
}
