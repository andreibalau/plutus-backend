package com.finance.plutus.model.user;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

/**
 * Plutus
 * Created by catalin on 24.01.2020
 */
@Getter
@Setter
@Embeddable
public class Business {

    @NotBlank
    @Column(nullable = false, name = "business_name")
    private String name;
    @NotBlank
    @Column(nullable =  false, name = "iban")
    private String iban;
    @NotBlank
    @Column(nullable =  false, name = "cui")
    private String cui;
    @NotBlank
    @Column(nullable =  false, name = "reg_com")
    private String regCom;
    @NotBlank
    @Column(nullable =  false, name = "address")
    private String address;

}
