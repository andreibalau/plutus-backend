package com.finance.plutus.service.product.impl;

import com.finance.plutus.model.product.dto.CreateProductDto;
import com.finance.plutus.model.product.dto.ProductDto;
import com.finance.plutus.service.product.CreateProduct;
import com.finance.plutus.service.product.FindProduct;
import com.finance.plutus.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

	private final CreateProduct createProduct;
	private final FindProduct findProduct;

	@Override
	public void create(CreateProductDto createProductDto) {
		createProduct.create(createProductDto);
	}

	@Override
	public List<ProductDto> findAll() {
		return findProduct.findAll();
	}
}
