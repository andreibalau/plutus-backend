package com.finance.plutus.partner;

import com.finance.plutus.app.payload.PlutusRequest;
import com.finance.plutus.app.payload.PlutusResponse;
import com.finance.plutus.app.payload.EntityCreatedResponse;
import com.finance.plutus.partner.dto.CreatePartnerDto;
import com.finance.plutus.partner.dto.PartnerDto;
import com.finance.plutus.partner.dto.UpdatePartnerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static com.finance.plutus.app.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

/** Plutus Created by catalin on 7/2/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/partners")
public class PartnerController {

  private final PartnerFacadeService partnerFacadeService;

  @ResponseStatus(CREATED)
  @PostMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public EntityCreatedResponse create(@Valid @RequestBody PlutusRequest<CreatePartnerDto> request) {
    return partnerFacadeService.create(request);
  }

  @PutMapping(
      value = "/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public void update(
      @PathVariable UUID id, @Valid @RequestBody PlutusRequest<UpdatePartnerDto> request) {
    partnerFacadeService.update(id, request);
  }

  @GetMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      params = {"page", "size"})
  public PlutusResponse<List<PartnerDto>> findAll(
      @RequestParam Integer page, @RequestParam Integer size) {
    return partnerFacadeService.findAll(page, size);
  }

  @ResponseStatus(NO_CONTENT)
  @DeleteMapping(
      value = "/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public void delete(@PathVariable UUID id) {
    partnerFacadeService.delete(id);
  }
}
