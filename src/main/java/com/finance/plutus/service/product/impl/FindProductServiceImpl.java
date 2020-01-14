package com.finance.plutus.service.product.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.finance.plutus.model.product.dto.ProductDto;
import com.finance.plutus.repository.product.ProductRepository;
import com.finance.plutus.service.product.FindProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Service
@RequiredArgsConstructor
public class FindProductServiceImpl implements FindProductService {

	private final ProductRepository productRepository;

	@Override
	public List<ProductDto> findAll() {
		return productRepository
				.findAll()
				.stream()
				.map(ProductDto::from)
				.collect(Collectors.toList());
	}
}
