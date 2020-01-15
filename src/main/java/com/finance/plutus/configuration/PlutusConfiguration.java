package com.finance.plutus.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Plutus
 * Created by catalin on 1/15/2020
 */
@Configuration
public class PlutusConfiguration {

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
