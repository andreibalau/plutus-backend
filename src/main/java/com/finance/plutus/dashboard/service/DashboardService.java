package com.finance.plutus.dashboard.service;

import com.finance.plutus.dashboard.controller.payload.DashboardResponse;
import com.finance.plutus.dashboard.model.BestPartnerDto;
import com.finance.plutus.dashboard.model.ExpenseDto;
import com.finance.plutus.dashboard.model.IncomeDto;
import com.finance.plutus.partner.model.Partner;
import com.finance.plutus.partner.model.PartnerType;
import com.finance.plutus.transaction.model.Transaction;
import com.finance.plutus.transaction.model.TransactionType;
import com.finance.plutus.transaction.service.TransactionFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/** Plutus Created by Catalin on 12/23/2020 */
@Service
@RequiredArgsConstructor
public class DashboardService {

  private final TransactionFinder transactionFinder;

  public DashboardResponse fetchStatistics() {
    return DashboardResponse.builder()
        .incomes(calculateIncomes())
        .expenses(calculateExpenses())
        .bestPartner(findBestPartner())
        .build();
  }

  private IncomeDto calculateIncomes() {
    List<Transaction> transactionList = fetchTransactions(TransactionType.INCOME);
    double total = transactionList.stream().mapToDouble(Transaction::getValue).sum();
    long count = transactionList.size();
    return new IncomeDto(total, count);
  }

  private ExpenseDto calculateExpenses() {
    List<Transaction> transactionList = fetchTransactions(TransactionType.EXPENSE);
    double total = transactionList.stream().mapToDouble(Transaction::getValue).sum();
    long count = transactionList.size();
    return new ExpenseDto(total, count);
  }

  private BestPartnerDto findBestPartner() {
    List<Transaction> transactionList = fetchTransactions(null);
    Set<Partner> clients =
        transactionList.stream()
            .map(Transaction::getPartner)
            .filter(partner -> partner.getType() == PartnerType.CLIENT)
            .collect(Collectors.toSet());
    Map<Double, Partner> map = new HashMap<>();
    for (Partner client : clients) {
      double clientTotal =
          transactionList.stream()
              .filter(transaction -> transaction.getPartner().equals(client))
              .mapToDouble(Transaction::getValue)
              .sum();
      map.put(clientTotal, client);
    }
    double total = 0;
    for (Map.Entry<Double, Partner> entry : map.entrySet()) {
      double value = entry.getKey();
      if (value > total) {
        total = value;
      }
    }
    return new BestPartnerDto(
        Optional.ofNullable(map.get(total)).map(Partner::getName).orElse(""), total);
  }

  private List<Transaction> fetchTransactions(TransactionType transactionType) {
    LocalDate now = LocalDate.now();
    LocalDate startDate = now.withMonth(1).withDayOfMonth(1);
    LocalDate endDate = now.withMonth(12).withDayOfMonth(31);
    return transactionFinder.findAll(null, transactionType, startDate, endDate);
  }
}
