package com.finance.plutus.service.product;

import com.finance.plutus.model.product.dto.ProductDto;

import java.util.List;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
public interface FindProduct {
	List<ProductDto> findAll();
}
