package com.finance.plutus.controller.transaction;

import com.finance.plutus.model.common.EntityCreatedDto;
import com.finance.plutus.model.transaction.dto.CreateTransactionDto;
import com.finance.plutus.service.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
@RestController
public class TransactionController {

	private final TransactionService transactionService;

	@ResponseStatus(CREATED)
	@PostMapping("/create")
	public EntityCreatedDto create(@Valid @RequestBody CreateTransactionDto createTransactionDto) {
		return transactionService.create(createTransactionDto);
	}

}
