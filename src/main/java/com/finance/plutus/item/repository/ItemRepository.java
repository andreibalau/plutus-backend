package com.finance.plutus.item.repository;

import com.finance.plutus.item.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/** Plutus Created by catalin on 7/2/2020 */
public interface ItemRepository extends JpaRepository<Item, UUID> {}
