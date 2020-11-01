package com.finance.plutus.invoice.controller;

import com.finance.plutus.app.payload.EntityCreatedResponse;
import com.finance.plutus.invoice.controller.payload.CreateSerialRequest;
import com.finance.plutus.invoice.controller.payload.FindSerialsResponse;
import com.finance.plutus.invoice.model.SerialDto;
import com.finance.plutus.invoice.service.SerialService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static com.finance.plutus.app.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;
import static org.springframework.http.HttpStatus.CREATED;

/** Plutus Created by catalin on 9/7/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/serials")
public class SerialController {

  private final SerialService serialService;

  @ResponseStatus(CREATED)
  @PostMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public EntityCreatedResponse create(@Valid @RequestBody CreateSerialRequest request) {
    UUID id = serialService.create(request.getSerial());
    return new EntityCreatedResponse(id);
  }

  @GetMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public FindSerialsResponse findAll() {
    List<SerialDto> serials = serialService.findAll();
    return new FindSerialsResponse(serials);
  }
}
