package com.finance.plutus.service.product;

import com.finance.plutus.model.common.EntityCreatedDto;
import com.finance.plutus.model.product.dto.CreateProductDto;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
public interface CreateProduct {
	EntityCreatedDto create(CreateProductDto createProductDto);
}
