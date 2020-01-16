package com.finance.plutus.service.product.impl;

import com.finance.plutus.model.common.EntityCreatedDto;
import com.finance.plutus.model.product.Product;
import com.finance.plutus.model.product.dto.ModifyProductDto;
import com.finance.plutus.repository.product.ProductRepository;
import com.finance.plutus.service.product.CreateProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Service
@RequiredArgsConstructor
public class CreateProductServiceImpl implements CreateProductService {

	private final ProductRepository productRepository;
	private final ModelMapper modelMapper;

	@Override
	public EntityCreatedDto create(ModifyProductDto productDto) {
		Product product = modelMapper.map(productDto, Product.class);
		product.setCreatedOn(System.currentTimeMillis());
		product.setUpdatedOn(System.currentTimeMillis());
		return new EntityCreatedDto(productRepository.save(product).getId());
	}
}
