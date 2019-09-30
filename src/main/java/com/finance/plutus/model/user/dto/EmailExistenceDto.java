package com.finance.plutus.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Getter
@Setter
@AllArgsConstructor
public class EmailExistenceDto {
	private boolean exists;
}
