package com.finance.plutus.controller.product;

import static org.springframework.http.HttpStatus.CREATED;

import javax.validation.Valid;
import java.util.List;

import com.finance.plutus.model.common.EntityCreatedDto;
import com.finance.plutus.model.product.dto.CreateProductDto;
import com.finance.plutus.model.product.dto.ProductDto;
import com.finance.plutus.service.product.CreateProductService;
import com.finance.plutus.service.product.FindProductService;
import com.finance.plutus.util.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@RequiredArgsConstructor
@RequestMapping(Api.PRODUCTS)
@RestController
public class ProductController {

	private final CreateProductService createProductService;
	private final FindProductService findProductService;

	@ResponseStatus(CREATED)
	@PostMapping
	public EntityCreatedDto create(@Valid @RequestBody CreateProductDto createProductDto) {
		return createProductService.create(createProductDto);
	}

	@GetMapping
	public List<ProductDto> findAll() {
		return findProductService.findAll();
	}

}
