package com.finance.plutus.controller;

import java.util.List;

import com.finance.plutus.api.ProductApi;
import com.finance.plutus.model.common.EntityCreatedDto;
import com.finance.plutus.model.product.dto.ModifyProductDto;
import com.finance.plutus.model.product.dto.ProductDto;
import com.finance.plutus.service.product.CreateProductService;
import com.finance.plutus.service.product.DeleteProductService;
import com.finance.plutus.service.product.FindProductService;
import com.finance.plutus.service.product.UpdateProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@RestController
@RequiredArgsConstructor
public class ProductController implements ProductApi {

	private final CreateProductService createProductService;
	private final FindProductService findProductService;
	private final UpdateProductService updateProductService;
	private final DeleteProductService deleteProductService;

	@Override
	public EntityCreatedDto create(ModifyProductDto productDto) {
		return createProductService.create(productDto);
	}

	@Override
	public List<ProductDto> findAll() {
		return findProductService.findAll();
	}

	@Override
	public ProductDto findById(Long productId) {
		return findProductService.findById(productId);
	}

	@Override
	public void update(Long productId, ModifyProductDto productDto) {
		updateProductService.update(productId, productDto);
	}

	@Override
	public void delete(Long productId) {
		deleteProductService.delete(productId);
	}

}
