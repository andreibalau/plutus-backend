package com.finance.plutus.model.partner.dto;

import com.finance.plutus.model.partner.Type;
import lombok.Getter;
import lombok.Setter;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Getter
@Setter
public class PreviewPartnerDto {

	private Long id;
	private String name;
	private String email;
	private Type type;

}
