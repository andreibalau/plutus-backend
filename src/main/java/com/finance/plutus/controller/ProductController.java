package com.finance.plutus.controller;

import java.util.List;

import com.finance.plutus.api.ProductApi;
import com.finance.plutus.model.common.EntityCreatedDto;
import com.finance.plutus.model.product.dto.CreateProductDto;
import com.finance.plutus.model.product.dto.ProductDto;
import com.finance.plutus.service.product.CreateProductService;
import com.finance.plutus.service.product.FindProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@RestController
@RequiredArgsConstructor
public class ProductController implements ProductApi {

	private final CreateProductService createProductService;
	private final FindProductService findProductService;

	public EntityCreatedDto create(CreateProductDto createProductDto) {
		return createProductService.create(createProductDto);
	}

	public List<ProductDto> findAll() {
		return findProductService.findAll();
	}

}
