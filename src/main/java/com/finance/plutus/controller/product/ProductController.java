package com.finance.plutus.controller.product;

import com.finance.plutus.model.common.EntityCreatedDto;
import com.finance.plutus.model.product.dto.CreateProductDto;
import com.finance.plutus.model.product.dto.ProductDto;
import com.finance.plutus.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@RequiredArgsConstructor
@RequestMapping("/api/products")
@RestController
public class ProductController {

	private final ProductService productService;

	@ResponseStatus(CREATED)
	@PostMapping("/create")
	public EntityCreatedDto create(@Valid @RequestBody CreateProductDto createProductDto) {
		return productService.create(createProductDto);
	}

	@GetMapping("/")
	public List<ProductDto> findAll() {
		return productService.findAll();
	}

}
