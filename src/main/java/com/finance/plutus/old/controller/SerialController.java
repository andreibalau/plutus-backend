package com.finance.plutus.old.controller;

import com.finance.plutus.old.controller.payload.CreateSerialRequest;
import com.finance.plutus.old.controller.payload.EntityCreatedResponse;
import com.finance.plutus.old.controller.payload.FindSerialsResponse;
import com.finance.plutus.old.model.dto.SerialDto;
import com.finance.plutus.old.service.serial.CreateSerialService;
import com.finance.plutus.old.service.serial.FindSerialService;
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

import static com.finance.plutus.old.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;
import static org.springframework.http.HttpStatus.CREATED;

/** Plutus Created by catalin on 9/7/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/serials")
public class SerialController {

  private final CreateSerialService createSerialService;
  private final FindSerialService findSerialService;

  @ResponseStatus(CREATED)
  @PostMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public EntityCreatedResponse create(@Valid @RequestBody CreateSerialRequest request) {
    UUID id = createSerialService.create(request.getSerial());
    return new EntityCreatedResponse(id);
  }

  @GetMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public FindSerialsResponse findAll() {
    List<SerialDto> serials = findSerialService.findAllDto();
    return new FindSerialsResponse(serials);
  }
}
