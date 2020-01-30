package com.finance.plutus.repository.serial;

import java.util.Optional;

import com.finance.plutus.model.serial.Serial;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Plutus
 * Created by catalin on 21.09.2019
 */
public interface SerialRepository extends JpaRepository<Serial, Long> {
	Optional<Serial> findByName(String name);
}
