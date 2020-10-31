package com.finance.plutus.old.controller;

import com.finance.plutus.old.controller.payload.FindBusinessResponse;
import com.finance.plutus.old.controller.payload.UpdateBusinessRequest;
import com.finance.plutus.old.model.dto.BusinessDto;
import com.finance.plutus.old.service.user.FindBusinessService;
import com.finance.plutus.old.service.user.UpdateBusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

import static com.finance.plutus.old.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;

/** Plutus Created by catalin on 7/1/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

  private final UpdateBusinessService updateBusinessService;
  private final FindBusinessService findBusinessService;

  @PutMapping(
      path = "/{id}/business",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public void updateBusiness(
      @PathVariable UUID id, @Valid @RequestBody UpdateBusinessRequest request) {
    updateBusinessService.update(id, request.getBusiness());
  }

  @GetMapping(
      path = "/{id}/business",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public FindBusinessResponse findBusiness(@PathVariable UUID id) {
    BusinessDto businessDto = findBusinessService.getDto();
    return new FindBusinessResponse(businessDto);
  }
}
