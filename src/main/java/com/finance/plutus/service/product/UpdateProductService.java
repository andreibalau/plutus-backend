package com.finance.plutus.service.product;

import com.finance.plutus.model.product.dto.ModifyProductDto;

/**
 * Plutus
 * Created by catalin on 1/16/2020
 */
public interface UpdateProductService {
	void update(Long productId, ModifyProductDto productDto);
}
