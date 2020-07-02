package com.finance.plutus.repository;

import com.finance.plutus.model.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/** Plutus Created by catalin on 7/2/2020 */
public interface ItemRepository extends JpaRepository<Item, Long> {
  Page<Item> findAll(Pageable pageable);
}
