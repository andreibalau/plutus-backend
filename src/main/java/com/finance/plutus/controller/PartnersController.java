package com.finance.plutus.controller;

import static com.finance.plutus.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import javax.validation.Valid;
import java.util.List;

import com.finance.plutus.controller.payload.CreatePartnerRequest;
import com.finance.plutus.controller.payload.EntityCreatedResponse;
import com.finance.plutus.controller.payload.FindPartnerResponse;
import com.finance.plutus.controller.payload.FindPartnersResponse;
import com.finance.plutus.model.dto.PartnerDto;
import com.finance.plutus.model.dto.PreviewPartnerDto;
import com.finance.plutus.service.CreatePartnerService;
import com.finance.plutus.service.DeletePartnerService;
import com.finance.plutus.service.FindPartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/** Plutus Created by catalin on 7/2/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/partners")
public class PartnersController {

  private final CreatePartnerService createPartnerService;
  private final FindPartnerService findPartnerService;
  private final DeletePartnerService deletePartnerService;

  @ResponseStatus(CREATED)
  @PostMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public EntityCreatedResponse create(@Valid @RequestBody CreatePartnerRequest request) {
    Long id = createPartnerService.create(request.getPartner(), request.getBusiness());
    return new EntityCreatedResponse(id);
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
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public FindPartnersResponse findAllByPage(
      @RequestParam Integer page, @RequestParam Integer size) {
    List<PreviewPartnerDto> partners = findPartnerService.findAllByPage(page, size);
    return new FindPartnersResponse(partners, page, partners.size());
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
