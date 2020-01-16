package com.finance.plutus.service.product;

import java.util.List;

import com.finance.plutus.model.product.dto.ProductDto;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
public interface FindProductService {
	List<ProductDto> findAll();
	ProductDto findById(Long productId);
}
