package com.finance.plutus.controller;

import static com.finance.plutus.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;
import static org.springframework.http.HttpStatus.CREATED;

import javax.validation.Valid;

import com.finance.plutus.controller.payload.CreateSerialRequest;
import com.finance.plutus.controller.payload.EntityCreatedResponse;
import com.finance.plutus.service.serial.CreateSerialService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/** Plutus Created by catalin on 9/7/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/serials")
public class SerialsController {

  private final CreateSerialService createSerialService;

  @ResponseStatus(CREATED)
  @PostMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public EntityCreatedResponse create(@Valid @RequestBody CreateSerialRequest request) {
    Long id = createSerialService.create(request.getSerial());
    return new EntityCreatedResponse(id);
  }
}
