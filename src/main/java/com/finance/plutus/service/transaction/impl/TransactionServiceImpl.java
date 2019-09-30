package com.finance.plutus.service.transaction.impl;

import com.finance.plutus.model.common.EntityCreatedDto;
import com.finance.plutus.model.transaction.dto.CreateTransactionDto;
import com.finance.plutus.service.transaction.CreateTransaction;
import com.finance.plutus.service.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

	private final CreateTransaction createTransaction;

	@Override
	public EntityCreatedDto create(CreateTransactionDto createTransactionDto) {
		return createTransaction.create(createTransactionDto);
	}
}
