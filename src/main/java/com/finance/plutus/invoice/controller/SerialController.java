package com.finance.plutus.invoice.controller;

import com.finance.plutus.app.payload.EntityCreatedResponse;
import com.finance.plutus.invoice.controller.payload.CreateSerialRequest;
import com.finance.plutus.invoice.controller.payload.FindSerialResponse;
import com.finance.plutus.invoice.controller.payload.UpdateSerialRequest;
import com.finance.plutus.invoice.model.SerialDto;
import com.finance.plutus.invoice.service.SerialService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

import static com.finance.plutus.app.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

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

  @ResponseStatus(NO_CONTENT)
  @PutMapping(
      path = "/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public void update(@PathVariable UUID id, @Valid @RequestBody UpdateSerialRequest request) {
    serialService.update(id, request.getSerial());
  }

  @GetMapping(
      path = "/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public FindSerialResponse find(@PathVariable UUID id) {
    SerialDto serial = serialService.findById(id);
    return new FindSerialResponse(serial);
  }
}
