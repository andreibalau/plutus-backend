package com.finance.plutus.service.transaction.impl;

import com.finance.plutus.model.common.EntityCreatedDto;
import com.finance.plutus.model.transaction.Transaction;
import com.finance.plutus.model.transaction.TransactionLine;
import com.finance.plutus.model.transaction.dto.CreateTransactionDto;
import com.finance.plutus.model.transaction.dto.CreateTransactionLineDto;
import com.finance.plutus.repository.partner.PartnerRepository;
import com.finance.plutus.repository.product.ProductRepository;
import com.finance.plutus.repository.transaction.TransactionRepository;
import com.finance.plutus.service.transaction.CreateTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@RequiredArgsConstructor
@Service
public class CreateTransactionImpl implements CreateTransaction {

	private final TransactionRepository transactionRepository;
	private final PartnerRepository partnerRepository;
	private final ProductRepository productRepository;

	@Override
	public EntityCreatedDto create(CreateTransactionDto createTransactionDto) {
		Set<TransactionLine> lines = createTransactionDto
				.getLines()
				.stream()
				.map(this::mapLine)
				.collect(Collectors.toSet());
		Transaction transaction = createTransactionDto.toTransaction();
		transaction.setCreatedOn(System.currentTimeMillis());
		transaction.setLines(lines);
		transaction.setVendor(partnerRepository.getOne(createTransactionDto.getVendor()));
		transaction.setClient(partnerRepository.getOne(createTransactionDto.getClient()));
		return new EntityCreatedDto(transactionRepository.save(transaction).getId());
	}

	private TransactionLine mapLine(CreateTransactionLineDto createTransactionLineDto) {
		TransactionLine transactionLine = createTransactionLineDto.toTransactionLine();
		transactionLine.setCreatedOn(System.currentTimeMillis());
		transactionLine.setProduct(productRepository.getOne(createTransactionLineDto.getProduct()));
		return transactionLine;
	}
}
