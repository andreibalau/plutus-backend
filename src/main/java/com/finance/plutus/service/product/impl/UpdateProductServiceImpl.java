package com.finance.plutus.service.product.impl;

import com.finance.plutus.exception.ProductException;
import com.finance.plutus.model.product.Product;
import com.finance.plutus.model.product.dto.ModifyProductDto;
import com.finance.plutus.repository.product.ProductRepository;
import com.finance.plutus.service.product.UpdateProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Plutus
 * Created by catalin on 1/16/2020
 */
@Service
@RequiredArgsConstructor
public class UpdateProductServiceImpl implements UpdateProductService {

	private final ProductRepository productRepository;

	@Override
	public void update(Long productId, ModifyProductDto productDto) {
		Product product = findProduct(productId);
		updateProduct(product, productDto);
	}

	private Product findProduct(Long productId) {
		return productRepository
				.findById(productId)
				.orElseThrow(ProductException::productNotFound);
	}

	private void updateProduct(Product product, ModifyProductDto productDto) {
		product.setName(productDto.getName());
		product.setUnitPrice(productDto.getUnitPrice());
		product.setTotalPrice(productDto.getTotalPrice());
		product.setTva(productDto.getTva());
		product.setUom(productDto.getUom());
		product.setType(productDto.getType());
		product.setUpdatedOn(System.currentTimeMillis());
		productRepository.save(product);
	}

}
