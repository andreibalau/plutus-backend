package com.finance.plutus.controller;

import com.finance.plutus.controller.payload.CreateSerialRequest;
import com.finance.plutus.controller.payload.EntityCreatedResponse;
import com.finance.plutus.controller.payload.FindSerialsResponse;
import com.finance.plutus.model.dto.SerialDto;
import com.finance.plutus.service.serial.CreateSerialService;
import com.finance.plutus.service.serial.FindSerialService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.finance.plutus.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;
import static org.springframework.http.HttpStatus.CREATED;

/** Plutus Created by catalin on 9/7/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/serials")
public class SerialsController {

  private final CreateSerialService createSerialService;
  private final FindSerialService findSerialService;

  @ResponseStatus(CREATED)
  @PostMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public EntityCreatedResponse create(@Valid @RequestBody CreateSerialRequest request) {
    Long id = createSerialService.create(request.getSerial());
    return new EntityCreatedResponse(id);
  }

  @GetMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public FindSerialsResponse findAll() {
    List<SerialDto> serials = findSerialService.findAll();
    return new FindSerialsResponse(serials, 0, serials.size());
  }
}
