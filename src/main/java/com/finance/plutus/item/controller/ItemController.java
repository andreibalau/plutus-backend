package com.finance.plutus.item.controller;

import com.finance.plutus.app.payload.EntityCreatedResponse;
import com.finance.plutus.item.controller.payload.CreateItemRequest;
import com.finance.plutus.item.controller.payload.FindItemsResponse;
import com.finance.plutus.item.controller.payload.UpdateItemRequest;
import com.finance.plutus.item.model.ItemDto;
import com.finance.plutus.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static com.finance.plutus.old.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

/** Plutus Created by catalin on 7/2/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/items")
public class ItemController {

  private final ItemService itemService;

  @ResponseStatus(CREATED)
  @PostMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public EntityCreatedResponse create(@Valid @RequestBody CreateItemRequest request) {
    UUID id = itemService.create(request.getItem());
    return new EntityCreatedResponse(id);
  }

  @PutMapping(
      value = "/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public void update(@PathVariable UUID id, @Valid @RequestBody UpdateItemRequest request) {
    itemService.update(id, request.getItem());
  }

  @GetMapping(
      value = "/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public FindItemsResponse findById(@PathVariable UUID id) {
    ItemDto item = itemService.findById(id);
    return FindItemsResponse.builder().page(0).size(1).total(1).items(List.of(item)).build();
  }

  @GetMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      params = {"page", "size"})
  public FindItemsResponse findAll(@RequestParam Integer page, @RequestParam Integer size) {
    List<ItemDto> items = itemService.findAll(page, size);
    long total = itemService.count();
    return FindItemsResponse.builder().page(page).size(size).total(total).items(items).build();
  }

  @ResponseStatus(NO_CONTENT)
  @DeleteMapping(
      value = "/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public void delete(@PathVariable UUID id) {
    itemService.delete(id);
  }
}
