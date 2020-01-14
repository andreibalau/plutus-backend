package com.finance.plutus.service.product.impl;

import com.finance.plutus.model.common.EntityCreatedDto;
import com.finance.plutus.model.product.Product;
import com.finance.plutus.model.product.dto.CreateProductDto;
import com.finance.plutus.repository.product.ProductRepository;
import com.finance.plutus.service.product.CreateProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Service
@RequiredArgsConstructor
public class CreateProductServiceImpl implements CreateProductService {

	private final ProductRepository productRepository;

	@Override
	public EntityCreatedDto create(CreateProductDto createProductDto) {
		Product product = createProductDto.toProduct();
		product.setCreatedOn(System.currentTimeMillis());
		return new EntityCreatedDto(productRepository.save(product).getId());
	}
}
