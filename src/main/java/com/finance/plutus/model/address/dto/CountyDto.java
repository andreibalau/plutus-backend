package com.finance.plutus.model.address.dto;

import com.finance.plutus.model.address.County;
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
public class CountyDto {
	@NotNull
	private Long id;
	@NotBlank
	private String name;
	@NotBlank
	private String abbrev;

	public County toCounty() {
		return County
				.builder()
				.id(id)
				.name(name)
				.abbrev(abbrev)
				.build();
	}
}
