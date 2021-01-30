package com.finance.plutus.transaction.dto.html;

import com.finance.plutus.app.service.Params;
import com.finance.plutus.app.util.DateFormatter;
import com.finance.plutus.transaction.model.Transaction;
import com.finance.plutus.transaction.model.TransactionType;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** Plutus Created by Catalin on 1/16/2021 */
@RequiredArgsConstructor
public class TransactionsParams implements Params<List<Transaction>> {

  private final Map<String, Object> params = new HashMap<>();
  private final String year;

  @Override
  public String getName() {
    return "Registru de incasari si plati";
  }

  @Override
  public Map<String, Object> getMap() {
    return params;
  }

  @Override
  public void submit(List<Transaction> transactionList) {
    params.put("name", "Registru de incasari si plati");
    params.put("year", year);
    params.put("transactions", mapTransactions(transactionList));
  }

  private List<TransactionLine> mapTransactions(List<Transaction> transactions) {
    return transactions.stream()
        .map(
            transaction ->
                TransactionLine.builder()
                    .number(String.valueOf(transactions.indexOf(transaction) + 1))
                    .date(DateFormatter.formatDate(transaction.getDate()))
                    .details(transaction.getDetails())
                    .document(transaction.getDocument())
                    .partner(transaction.getPartner().getName())
                    .expense(getTransactionIncomeOrExpense(transaction, TransactionType.EXPENSE))
                    .income(getTransactionIncomeOrExpense(transaction, TransactionType.INCOME))
                    .build())
        .collect(Collectors.toList());
  }

  private String getTransactionIncomeOrExpense(
      Transaction transaction, TransactionType transactionType) {
    return transaction.getType() == transactionType ? String.valueOf(transaction.getValue()) : null;
  }
}
