package com.finance.plutus.model.transaction.dto;

import com.finance.plutus.model.transaction.Status;
import com.finance.plutus.model.transaction.Transaction;
import com.finance.plutus.model.transaction.Type;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Builder
@Data
public class CreateTransactionDto {
	@NotNull
	private Long date;
	@NotNull
	private Type type;
	@NotNull
	private Long vendor;
	@NotNull
	private Long client;
	@NotNull
	private Double subtotal;
	@NotNull
	private Double taxes;
	@NotNull
	private Double total;
	@NotNull
	private List<CreateTransactionLineDto> lines;
	@NotNull
	private Status status;
	@NotNull
	private Boolean canBeDeducted;

	public Transaction toTransaction() {
		return Transaction
				.builder()
				.date(date)
				.type(type)
				.subtotal(subtotal)
				.taxes(taxes)
				.total(total)
				.status(status)
				.canBeDeducted(canBeDeducted)
				.build();
	}
}
