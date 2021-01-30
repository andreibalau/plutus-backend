package com.finance.plutus.transaction.impl;

import com.finance.plutus.app.exception.EntityNotFoundException;
import com.finance.plutus.app.service.CsvReader;
import com.finance.plutus.app.service.PdfGenerator;
import com.finance.plutus.app.service.Template;
import com.finance.plutus.currency.CurrencyRate;
import com.finance.plutus.currency.CurrencyRateService;
import com.finance.plutus.partner.Partner;
import com.finance.plutus.partner.PartnerService;
import com.finance.plutus.transaction.TransactionMapper;
import com.finance.plutus.transaction.TransactionService;
import com.finance.plutus.transaction.dto.*;
import com.finance.plutus.transaction.dto.html.TransactionsParams;
import com.finance.plutus.transaction.exception.WrongTransactionStatusException;
import com.finance.plutus.transaction.model.Transaction;
import com.finance.plutus.transaction.model.TransactionCurrency;
import com.finance.plutus.transaction.model.TransactionStatus;
import com.finance.plutus.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/** Plutus Created by Catalin on 1/23/2021 */
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

  private final TransactionRepository transactionRepository;
  private final PartnerService partnerService;
  private final TransactionMapper transactionMapper;
  private final CsvReader csvReader;
  private final PdfGenerator pdfGenerator;
  private final CurrencyRateService currencyRateService;

  @Override
  @Transactional
  public UUID create(CreateTransactionDto createTransactionDto) {
    Transaction transaction = transactionMapper.mapToEntity(createTransactionDto);
    Partner partner = partnerService.findById(createTransactionDto.getPartnerId());
    transaction.setPartner(partner);
    updateCurrency(transaction);
    transactionRepository.save(transaction);
    return transaction.getId();
  }

  @Override
  @Transactional
  public void create(UploadFileDto uploadFileDto) {
    List<CreateTransactionFileDto> transactions =
        csvReader.loadList(CreateTransactionFileDto.class, uploadFileDto.getFile());
    transactions.forEach(
        dto -> {
          Transaction transaction = transactionMapper.mapToEntity(dto);
          Partner partner = partnerService.findById(dto.getPartnerId());
          transaction.setPartner(partner);
          transactionRepository.save(transaction);
        });
  }

  @Override
  @Transactional
  public void update(UUID id, UpdateTransactionDto updateTransactionDto) {
    Transaction transaction = findById(id);
    if (transaction.getStatus() == TransactionStatus.DONE) {
      throw new WrongTransactionStatusException();
    }
    Transaction updatedTransaction =
        transactionMapper.mapToEntity(transaction, updateTransactionDto);
    Partner partner = partnerService.findById(updateTransactionDto.getPartnerId());
    updatedTransaction.setPartner(partner);
    updateCurrency(updatedTransaction);
    transactionRepository.save(updatedTransaction);
  }

  @Override
  public List<Transaction> findAll(FilterParams filterParams, int page, int size) {
    Map<String, Object> params = createFilterParamsMap(filterParams);
    return transactionRepository.findAllFiltered(params, PageRequest.of(page, size));
  }

  @Override
  public List<Transaction> findAll(FilterParams filterParams) {
    Map<String, Object> params = createFilterParamsMap(filterParams);
    return transactionRepository.findAllFiltered(params);
  }

  @Override
  public List<Transaction> findAllById(Iterable<UUID> ids) {
    return transactionRepository.findAllById(ids);
  }

  @Override
  public List<Transaction> findAll() {
    return transactionRepository.findAll();
  }

  @Override
  public Transaction findById(UUID id) {
    return transactionRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("transaction"));
  }

  @Override
  public TransactionStat calculateStat(FilterParams filterParams) {
    List<Transaction> transactions = findAll(filterParams);
    TransactionStat transactionStat = new TransactionStat();
    transactionStat.setNumber(transactions.size());
    transactionStat.setTotal(transactions.stream().mapToDouble(Transaction::getValue).sum());
    return transactionStat;
  }

  @Override
  @Transactional
  public void delete(UUID id) {
    Transaction transaction = findById(id);
    if (transaction.getStatus() == TransactionStatus.DONE) {
      throw new WrongTransactionStatusException();
    }
    transactionRepository.delete(transaction);
  }

  @Override
  public byte[] downloadDocument(String year) {
    FilterParams filterParams =
        FilterParams.builder()
            .startDate(String.format("%s-01-01", year))
            .endDate(String.format("%s-12-31", year))
            .build();
    List<Transaction> transactions = findAll(filterParams);
    TransactionsParams params = new TransactionsParams(year);
    params.submit(transactions);
    return pdfGenerator.generateSingle(Template.TRANSACTIONS, params).orElseThrow();
  }

  @Override
  @Transactional
  public void collect(UUID id) {
    Transaction transaction = findById(id);
    collect(transaction);
  }

  @Override
  @Transactional
  public void collect(Iterable<UUID> ids) {
    findAllById(ids).forEach(this::collect);
  }

  private void updateCurrency(Transaction transaction) {
    TransactionCurrency transactionCurrency = transaction.getTransactionCurrency();
    if (transactionCurrency != null) {
      CurrencyRate currencyRate =
          currencyRateService.findLastByDateAndCurrency(
              transaction.getDate(), transactionCurrency.getCurrency());
      Double value = transaction.getValue();
      transaction.setValue(value * currencyRate.getRate());
      transactionCurrency.setRate(currencyRate.getRate());
      transaction.setTransactionCurrency(transactionCurrency);
    }
  }

  private void collect(Transaction transaction) {
    if (transaction.getStatus() != TransactionStatus.DONE) {
      transaction.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
      transaction.setStatus(TransactionStatus.DONE);
      transactionRepository.save(transaction);
    }
  }

  private Map<String, Object> createFilterParamsMap(FilterParams filterParams) {
    Map<String, Object> params = new HashMap<>();
    if (filterParams.getPartnerId() != null) {
      params.put("partnerId", filterParams.getPartnerId());
    }
    if (filterParams.getType() != null) {
      params.put("type", filterParams.getType());
    }
    if (filterParams.getStartDate() != null) {
      params.put("startDate", LocalDate.parse(filterParams.getStartDate()));
    }
    if (filterParams.getEndDate() != null) {
      params.put("endDate", LocalDate.parse(filterParams.getEndDate()));
    }
    if (filterParams.getDeductible() != null) {
      params.put("deductible", filterParams.getDeductible());
    }
    return params;
  }
}
