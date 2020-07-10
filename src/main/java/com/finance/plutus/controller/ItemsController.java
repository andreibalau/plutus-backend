package com.finance.plutus.controller;

import com.finance.plutus.controller.payload.CreateItemRequest;
import com.finance.plutus.controller.payload.EntityCreatedResponse;
import com.finance.plutus.controller.payload.FindItemResponse;
import com.finance.plutus.controller.payload.FindItemsResponse;
import com.finance.plutus.model.dto.ItemDto;
import com.finance.plutus.model.dto.PreviewItemDto;
import com.finance.plutus.service.CreateItemService;
import com.finance.plutus.service.DeleteItemService;
import com.finance.plutus.service.FindItemService;
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

import javax.validation.Valid;
import java.util.List;

import static com.finance.plutus.configuration.Api.APPLICATION_VND_PLUTUS_FINANCE_JSON;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

/** Plutus Created by catalin on 7/2/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/items")
public class ItemsController {

  private final CreateItemService createItemService;
  private final FindItemService findItemService;
  private final DeleteItemService deleteItemService;

  @ResponseStatus(CREATED)
  @PostMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public EntityCreatedResponse create(@Valid @RequestBody CreateItemRequest request) {
    Long id = createItemService.create(request.getItem());
    return new EntityCreatedResponse(id);
  }

  @GetMapping(
      value = "/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public FindItemResponse findById(@PathVariable Long id) {
    ItemDto itemDto = findItemService.findDtoById(id);
    return new FindItemResponse(itemDto);
  }

  @GetMapping(
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public FindItemsResponse findAllByPage(@RequestParam Integer page, @RequestParam Integer size) {
    List<PreviewItemDto> items = findItemService.findAllByPage(page, size);
    return new FindItemsResponse(items, page, items.size());
  }

  @ResponseStatus(NO_CONTENT)
  @DeleteMapping(
      value = "/{id}",
      consumes = APPLICATION_VND_PLUTUS_FINANCE_JSON,
      produces = APPLICATION_VND_PLUTUS_FINANCE_JSON)
  public void delete(@PathVariable Long id) {
    deleteItemService.delete(id);
  }
}
