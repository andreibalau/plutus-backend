package com.finance.plutus.transaction.dto.html;

import com.finance.plutus.app.service.Params;
import com.finance.plutus.app.util.DateFormatter;
import com.finance.plutus.transaction.model.Transaction;
import com.finance.plutus.transaction.model.TransactionType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** Plutus Created by Catalin on 1/16/2021 */
public class TransactionsParams implements Params<List<Transaction>> {

  private final Map<String, Object> params = new HashMap<>();

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
    params.put("reports", prepareReports(transactionList));
  }

  private List<Report> prepareReports(List<Transaction> transactionList) {
    List<Report> reports = new ArrayList<>();
    reports.add(
        Report.builder()
            .year("Anul 2019")
            .transactions(getListByYear(2019, transactionList))
            .build());
    reports.add(
        Report.builder()
            .year("Anul 2020")
            .transactions(getListByYear(2020, transactionList))
            .build());
    reports.add(
        Report.builder()
            .year("Anul 2021")
            .transactions(getListByYear(2021, transactionList))
            .build());
    return reports;
  }

  private List<TransactionLine> getListByYear(int year, List<Transaction> transactions) {
    List<Transaction> yearTransactionList =
        transactions.stream()
            .filter(transaction -> transaction.getDate().getYear() == year)
            .collect(Collectors.toList());
    return yearTransactionList.stream()
        .map(
            transaction ->
                TransactionLine.builder()
                    .number(String.valueOf(yearTransactionList.indexOf(transaction) + 1))
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
