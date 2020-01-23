package com.finance.plutus.model.anaf;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * Plutus
 * Created by catalin on 1/20/2020
 */
@Getter
@Setter
public class InfoResponseDto {

	private Integer cod;
	private String message;
	private List<InfoDto> found;
	@JsonIgnore
	private List<Object> notfound;

}
