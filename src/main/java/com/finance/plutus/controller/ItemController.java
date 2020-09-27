package com.finance.plutus.controller;

import com.finance.plutus.controller.payload.*;
import com.finance.plutus.model.dto.ItemDto;
import com.finance.plutus.service.item.CreateItemService;
import com.finance.plutus.service.item.DeleteItemService;
import com.finance.plutus.service.item.FindItemService;
import com.finance.plutus.service.item.UpdateItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.finance.plutus.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

/** Plutus Created by catalin on 7/2/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/items")
public class ItemController {

  private final CreateItemService createItemService;
  private final DeleteItemService deleteItemService;
  private final FindItemService findItemService;
  private final UpdateItemService updateItemService;

  @ResponseStatus(CREATED)
  @PostMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public EntityCreatedResponse create(@Valid @RequestBody CreateItemRequest request) {
    String id = createItemService.create(request.getItem());
    return new EntityCreatedResponse(id);
  }

  @PutMapping(
      value = "/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public void update(@PathVariable String id, @Valid @RequestBody UpdateItemRequest request) {
    updateItemService.update(id, request.getItem());
  }

  @GetMapping(
      value = "/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public FindItemResponse findById(@PathVariable String id) {
    ItemDto itemDto = findItemService.findDtoById(id);
    return new FindItemResponse(itemDto);
  }

  @GetMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      params = {"page", "size"})
  public FindItemsResponse findAll(@RequestParam Integer page, @RequestParam Integer size) {
    List<ItemDto> items = findItemService.findAllDtoByPage(page, size);
    return new FindItemsResponse(items, page, size, findItemService.countAll());
  }

  @ResponseStatus(NO_CONTENT)
  @DeleteMapping(
      value = "/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public void delete(@PathVariable String id) {
    deleteItemService.delete(id);
  }
}
