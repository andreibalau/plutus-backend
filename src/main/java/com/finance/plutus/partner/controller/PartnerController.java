package com.finance.plutus.partner.controller;

import com.finance.plutus.app.payload.EntityCreatedResponse;
import com.finance.plutus.partner.model.PartnerDto;
import com.finance.plutus.partner.controller.payload.CreatePartnerRequest;
import com.finance.plutus.partner.controller.payload.FindPartnersResponse;
import com.finance.plutus.partner.controller.payload.UpdatePartnerRequest;
import com.finance.plutus.partner.service.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

  private final PartnerService partnerService;

  @ResponseStatus(CREATED)
  @PostMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public EntityCreatedResponse create(@Valid @RequestBody CreatePartnerRequest request) {
    UUID id = partnerService.create(request.getPartner());
    return new EntityCreatedResponse(id);
  }

  @PutMapping(
      value = "/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public void update(@PathVariable UUID id, @Valid @RequestBody UpdatePartnerRequest request) {
    partnerService.update(id, request.getPartner());
  }

  @GetMapping(
      value = "/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public FindPartnersResponse findById(@PathVariable UUID id) {
    PartnerDto partner = partnerService.findById(id);
    return FindPartnersResponse.builder()
        .page(0)
        .size(1)
        .total(1)
        .partners(List.of(partner))
        .build();
  }

  @GetMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      params = {"page", "size"})
  public FindPartnersResponse findAll(@RequestParam Integer page, @RequestParam Integer size) {
    List<PartnerDto> partners = partnerService.findAll(page, size);
    long total = partnerService.count();
    return FindPartnersResponse.builder()
        .page(page)
        .size(size)
        .total(total)
        .partners(partners)
        .build();
  }

  @ResponseStatus(NO_CONTENT)
  @DeleteMapping(
      value = "/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public void delete(@PathVariable UUID id) {
    partnerService.delete(id);
  }
}
