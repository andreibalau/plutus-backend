package com.finance.plutus.service.product.impl;

import com.finance.plutus.exception.ProductException;
import com.finance.plutus.model.product.Product;
import com.finance.plutus.repository.product.ProductRepository;
import com.finance.plutus.service.product.DeleteProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Plutus
 * Created by catalin on 1/16/2020
 */
@Service
@RequiredArgsConstructor
public class DeleteProductServiceImpl implements DeleteProductService {

	private final ProductRepository productRepository;

	@Override
	public void delete(Long productId) {
		Product product = productRepository
				.findById(productId)
				.orElseThrow(ProductException::productNotFound);
		productRepository.delete(product);
	}

}
