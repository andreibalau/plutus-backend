package com.finance.plutus.controller;

import com.finance.plutus.controller.payload.CreatePartnerRequest;
import com.finance.plutus.controller.payload.EntityCreatedResponse;
import com.finance.plutus.controller.payload.FindPartnerResponse;
import com.finance.plutus.controller.payload.FindPartnersResponse;
import com.finance.plutus.controller.payload.UpdatePartnerRequest;
import com.finance.plutus.model.dto.PartnerDto;
import com.finance.plutus.model.dto.PreviewPartnerDto;
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

import static com.finance.plutus.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

/** Plutus Created by catalin on 7/2/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/partners")
public class PartnersController {

  private final CreatePartnerService createPartnerService;
  private final FindPartnerService findPartnerService;
  private final DeletePartnerService deletePartnerService;
  private final UpdatePartnerService updatePartnerService;

  @ResponseStatus(CREATED)
  @PostMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public EntityCreatedResponse create(@Valid @RequestBody CreatePartnerRequest request) {
    Long id = createPartnerService.create(request.getPartner(), request.getBusiness());
    return new EntityCreatedResponse(id);
  }

  @PutMapping(
      value = "/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public void update(@Valid @RequestBody UpdatePartnerRequest request, @PathVariable Long id) {
    updatePartnerService.update(id, request.getPartner(), request.getBusiness());
  }

  @GetMapping(
      value = "/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public FindPartnerResponse findById(@PathVariable Long id) {
    PartnerDto partnerDto = findPartnerService.findDtoById(id);
    return new FindPartnerResponse(partnerDto);
  }

  @GetMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      params = {"page", "size"})
  public FindPartnersResponse findAllByPage(
      @RequestParam Integer page, @RequestParam Integer size) {
    List<PreviewPartnerDto> partners = findPartnerService.findAllByPage(page, size);
    return new FindPartnersResponse(partners, page, findPartnerService.countAll());
  }

  @GetMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public FindPartnersResponse findAll() {
    List<PreviewPartnerDto> partners = findPartnerService.findAll();
    return new FindPartnersResponse(partners, 0, partners.size());
  }

  @ResponseStatus(NO_CONTENT)
  @DeleteMapping(
      value = "/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public void delete(@PathVariable Long id) {
    deletePartnerService.delete(id);
  }
}
