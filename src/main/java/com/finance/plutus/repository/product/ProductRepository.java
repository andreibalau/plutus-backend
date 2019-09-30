package com.finance.plutus.repository.product;

import com.finance.plutus.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Plutus
 * Created by catalin on 21.09.2019
 */
public interface ProductRepository extends JpaRepository<Product, Long> { }
