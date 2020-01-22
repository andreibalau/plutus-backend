package com.finance.plutus.model.invoice.dto;

import com.finance.plutus.model.invoice.Status;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Plutus
 * Created by catalin on 22.01.2020
 */
@Getter
@Setter
public class ChangeStatusDto {

    @NotNull
    private Status status;

}
