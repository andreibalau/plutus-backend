package com.finance.plutus.model.dto;

import com.finance.plutus.model.entity.Business;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

/** Plutus Created by Catalin on 10/13/2020 */
@Getter
@Setter
public class BusinessDto {
  @NotBlank private String name;
  @NotBlank private String vat;
  @NotBlank private String commercialRegistry;
  @NotBlank private String address;
  @NotBlank private String bankAccount;
  @NotBlank private UUID bankId;
  private String email;
  private String phone;
  private String website;
  private String vies;

  public static BusinessDto mapFromEntity(Business business) {
    BusinessDto businessDto = new BusinessDto();
    businessDto.setName(business.getName());
    businessDto.setEmail(business.getEmail());
    businessDto.setPhone(business.getPhone());
    businessDto.setAddress(business.getAddress());
    businessDto.setVat(business.getVat());
    businessDto.setVies(business.getVies());
    businessDto.setWebsite(business.getWebsite());
    businessDto.setCommercialRegistry(business.getCommercialRegistry());
    businessDto.setBankId(business.getBank().getId());
    businessDto.setBankAccount(business.getBankAccount());
    return businessDto;
  }
}
