package com.finance.plutus.serial;

import com.finance.plutus.app.payload.PlutusRequest;
import com.finance.plutus.app.payload.PlutusResponse;
import com.finance.plutus.serial.dto.SerialDto;
import com.finance.plutus.serial.dto.UpdateSerialDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

import static com.finance.plutus.app.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;
import static org.springframework.http.HttpStatus.NO_CONTENT;

/** Plutus Created by catalin on 9/7/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/serials")
public class SerialController {

  private final SerialFacadeService serialFacadeService;

  @ResponseStatus(NO_CONTENT)
  @PutMapping(
      path = "/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public void update(
      @PathVariable UUID id, @Valid @RequestBody PlutusRequest<UpdateSerialDto> request) {
    serialFacadeService.update(id, request);
  }

  @GetMapping(
      path = "/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public PlutusResponse<SerialDto> find(@PathVariable UUID id) {
    return serialFacadeService.findById(id);
  }
}
