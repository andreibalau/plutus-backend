package com.finance.plutus.service.product;

import com.finance.plutus.model.product.dto.CreateProductDto;
import com.finance.plutus.model.product.dto.ProductDto;

import java.util.List;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
public interface ProductService {
	void create(CreateProductDto createProductDto);
	List<ProductDto> findAll();
}
