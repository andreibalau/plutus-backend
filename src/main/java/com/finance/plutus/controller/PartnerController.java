package com.finance.plutus.controller;

import com.finance.plutus.controller.payload.CreatePartnerRequest;
import com.finance.plutus.controller.payload.EntityCreatedResponse;
import com.finance.plutus.controller.payload.FindPartnerResponse;
import com.finance.plutus.controller.payload.FindPartnersResponse;
import com.finance.plutus.controller.payload.UpdatePartnerRequest;
import com.finance.plutus.model.dto.PartnerDto;
import com.finance.plutus.service.partner.CreatePartnerService;
import com.finance.plutus.service.partner.DeletePartnerService;
import com.finance.plutus.service.partner.FindPartnerService;
import com.finance.plutus.service.partner.UpdatePartnerService;
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

import static com.finance.plutus.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

/** Plutus Created by catalin on 7/2/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/partners")
public class PartnerController {

  private final CreatePartnerService createPartnerService;
  private final DeletePartnerService deletePartnerService;
  private final UpdatePartnerService updatePartnerService;
  private final FindPartnerService findPartnerService;

  @ResponseStatus(CREATED)
  @PostMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public EntityCreatedResponse create(@Valid @RequestBody CreatePartnerRequest request) {
    String id = createPartnerService.create(request.getPartner());
    return new EntityCreatedResponse(id);
  }

  @PutMapping(
      value = "/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public void update(@PathVariable UUID id, @Valid @RequestBody UpdatePartnerRequest request) {
    updatePartnerService.update(id, request.getPartner());
  }

  @GetMapping(
      value = "/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public FindPartnerResponse findById(@PathVariable UUID id) {
    PartnerDto partnerDto = findPartnerService.findDtoById(id);
    return new FindPartnerResponse(partnerDto);
  }

  @GetMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      params = {"page", "size"})
  public FindPartnersResponse findAll(@RequestParam Integer page, @RequestParam Integer size) {
    List<PartnerDto> partners = findPartnerService.findAllDtoByPage(page, size);
    return new FindPartnersResponse(partners, page, size, findPartnerService.countAll());
  }

  @ResponseStatus(NO_CONTENT)
  @DeleteMapping(
      value = "/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public void delete(@PathVariable UUID id) {
    deletePartnerService.delete(id);
  }
}
