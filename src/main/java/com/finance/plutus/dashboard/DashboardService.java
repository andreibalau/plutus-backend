package com.finance.plutus.dashboard;

import com.finance.plutus.app.payload.PlutusResponse;
import com.finance.plutus.transaction.TransactionService;
import com.finance.plutus.transaction.dto.FilterParams;
import com.finance.plutus.transaction.model.Transaction;
import com.finance.plutus.transaction.model.TransactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** Plutus Created by Catalin on 12/23/2020 */
@Service
@RequiredArgsConstructor
public class DashboardService {

  private final TransactionService transactionService;

  public PlutusResponse<List<Stat>> fetchStatistics() {
    List<Stat> statistics = new ArrayList<>();
    statistics.add(calculateIncomeForLastYear());
    statistics.add(calculateExpenseForLastYear());
    statistics.add(calculateDeductibleExpensesForLastYear());
    statistics.add(calculateIncomeForCurrentYear());
    statistics.add(calculateExpenseForCurrentYear());
    return new PlutusResponse<>(statistics);
  }

  private Stat calculateIncomeForLastYear() {
    List<Transaction> transactionList = fetchTransactionsForLastYear(TransactionType.INCOME);
    double total = transactionList.stream().mapToDouble(Transaction::getValue).sum();
    int lastYear = LocalDate.now().getYear() - 1;
    return Stat.of(String.format("Venituri pe anul %s", lastYear), total);
  }

  private Stat calculateExpenseForLastYear() {
    List<Transaction> transactionList = fetchTransactionsForLastYear(TransactionType.EXPENSE);
    double total = transactionList.stream().mapToDouble(Transaction::getValue).sum();
    int lastYear = LocalDate.now().getYear() - 1;
    return Stat.of(String.format("Cheltuieli pe anul %s", lastYear), total);
  }

  private Stat calculateIncomeForCurrentYear() {
    List<Transaction> transactionList = fetchTransactionsForCurrentYear(TransactionType.INCOME);
    double total = transactionList.stream().mapToDouble(Transaction::getValue).sum();
    return Stat.of("Venituri pe anul curent", total);
  }

  private Stat calculateExpenseForCurrentYear() {
    List<Transaction> transactionList = fetchTransactionsForCurrentYear(TransactionType.EXPENSE);
    double total = transactionList.stream().mapToDouble(Transaction::getValue).sum();
    return Stat.of("Cheltuieli pe anul curent", total);
  }

  private Stat calculateDeductibleExpensesForLastYear() {
    List<Transaction> transactionList = fetchTransactionsForLastYear(null);
    double total =
        transactionList.stream()
            .filter(Transaction::getDeductible)
            .mapToDouble(Transaction::getValue)
            .sum();
    int lastYear = LocalDate.now().getYear() - 1;
    return Stat.of(String.format("Cheltuieli deductibile pe anul %s", lastYear), total);
  }

  private List<Transaction> fetchTransactionsForCurrentYear(TransactionType transactionType) {
    LocalDate now = LocalDate.now();
    LocalDate startDate = now.withMonth(1).withDayOfMonth(1);
    LocalDate endDate = now.withMonth(12).withDayOfMonth(31);
    FilterParams params =
        FilterParams.builder()
            .partnerId(null)
            .type(transactionType)
            .startDate(startDate)
            .endDate(endDate)
            .build();
    return transactionService.findAll(params);
  }

  private List<Transaction> fetchTransactionsForLastYear(TransactionType transactionType) {
    LocalDate now = LocalDate.now().minusYears(1);
    LocalDate startDate = now.withMonth(1).withDayOfMonth(1);
    LocalDate endDate = now.withMonth(12).withDayOfMonth(31);
    FilterParams params =
        FilterParams.builder()
            .partnerId(null)
            .type(transactionType)
            .startDate(startDate)
            .endDate(endDate)
            .build();
    return transactionService.findAll(params);
  }
}
