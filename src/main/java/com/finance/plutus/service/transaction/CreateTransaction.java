package com.finance.plutus.service.transaction;

import com.finance.plutus.model.common.EntityCreatedDto;
import com.finance.plutus.model.transaction.dto.CreateTransactionDto;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
public interface CreateTransaction {
	EntityCreatedDto create(CreateTransactionDto createTransactionDto);
}
