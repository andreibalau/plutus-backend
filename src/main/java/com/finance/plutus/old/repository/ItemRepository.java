package com.finance.plutus.old.repository;

import com.finance.plutus.old.model.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/** Plutus Created by catalin on 7/2/2020 */
public interface ItemRepository extends JpaRepository<Item, UUID> {
  Page<Item> findAll(Pageable pageable);
}
