package com.finance.plutus.model.user.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

/**
 * Plutus
 * Created by catalin on 24.01.2020
 */
@Getter
@Setter
public class BusinessDto {

    @NotBlank
    private String name;
    @NotBlank
    private String iban;
    @NotBlank
    private String cui;
    @NotBlank
    private String regCom;
    @NotBlank
    private String address;

}
