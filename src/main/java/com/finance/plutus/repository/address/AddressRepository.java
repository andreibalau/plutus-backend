package com.finance.plutus.repository.address;

import com.finance.plutus.model.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Plutus
 * Created by catalin on 21.09.2019
 */
public interface AddressRepository extends JpaRepository<Address, Long> { }
