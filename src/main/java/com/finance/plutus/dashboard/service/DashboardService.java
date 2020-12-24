package com.finance.plutus.dashboard.service;

import com.finance.plutus.dashboard.controller.payload.DashboardResponse;
import com.finance.plutus.dashboard.model.BestPartnerDto;
import com.finance.plutus.dashboard.model.ExpenseDto;
import com.finance.plutus.dashboard.model.IncomeDto;
import com.finance.plutus.partner.model.Partner;
import com.finance.plutus.partner.model.PartnerType;
import com.finance.plutus.transaction.model.FilterTransactionDto;
import com.finance.plutus.transaction.model.Transaction;
import com.finance.plutus.transaction.model.TransactionType;
import com.finance.plutus.transaction.service.TransactionFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
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
    FilterTransactionDto filter = prepareFilter(TransactionType.INCOME);
    List<Transaction> transactionList = transactionFinder.findAllFiltered(filter);
    double total = transactionList.stream().mapToDouble(Transaction::getValue).sum();
    long count = transactionList.size();
    return new IncomeDto(total, count);
  }

  private ExpenseDto calculateExpenses() {
    FilterTransactionDto filter = prepareFilter(TransactionType.EXPENSE);
    List<Transaction> transactionList = transactionFinder.findAllFiltered(filter);
    double total = transactionList.stream().mapToDouble(Transaction::getValue).sum();
    long count = transactionList.size();
    return new ExpenseDto(total, count);
  }

  private BestPartnerDto findBestPartner() {
    FilterTransactionDto filter = prepareFilter(null);
    List<Transaction> transactionList = transactionFinder.findAllFiltered(filter);
    Set<Partner> clients =
        transactionList.stream()
            .map(Transaction::getPartner)
            .filter(partner -> partner.getType() == PartnerType.CLIENT)
            .collect(Collectors.toSet());
    double total = 0;
    Partner bestPartner = null;
    for (Partner client : clients) {
      double clientTotal =
          transactionList.stream()
              .filter(transaction -> transaction.getPartner().equals(client))
              .mapToDouble(Transaction::getValue)
              .sum();
      if (clientTotal > total) {
        total = clientTotal;
        if (total > 0) {
          bestPartner = client;
          break;
        }
      }
    }
    return new BestPartnerDto(
        Optional.ofNullable(bestPartner).map(Partner::getName).orElse(""), total);
  }

  private FilterTransactionDto prepareFilter(TransactionType transactionType) {
    LocalDate now = LocalDate.now();
    FilterTransactionDto filter = new FilterTransactionDto();
    filter.setStartDate(now.withMonth(1).withDayOfMonth(1));
    filter.setEndDate(now.withMonth(12).withDayOfMonth(31));
    filter.setType(transactionType);
    return filter;
  }
}
