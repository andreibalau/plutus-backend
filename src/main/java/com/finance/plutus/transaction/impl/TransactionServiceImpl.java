package com.finance.plutus.transaction.impl;

import com.finance.plutus.app.exception.EntityNotFoundException;
import com.finance.plutus.app.service.CsvReader;
import com.finance.plutus.app.service.PdfGenerator;
import com.finance.plutus.app.service.Template;
import com.finance.plutus.partner.Partner;
import com.finance.plutus.partner.PartnerService;
import com.finance.plutus.transaction.TransactionMapper;
import com.finance.plutus.transaction.TransactionService;
import com.finance.plutus.transaction.dto.CreateTransactionDto;
import com.finance.plutus.transaction.dto.UpdateTransactionDto;
import com.finance.plutus.transaction.dto.UploadFileDto;
import com.finance.plutus.transaction.dto.html.TransactionsParams;
import com.finance.plutus.transaction.exception.WrongTransactionStatusException;
import com.finance.plutus.transaction.model.Transaction;
import com.finance.plutus.transaction.model.TransactionStatus;
import com.finance.plutus.transaction.model.TransactionType;
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
  public byte[] downloadAll() {
    List<Transaction> transactions = findAll();
    TransactionsParams params = new TransactionsParams();
    params.submit(transactions);
    return pdfGenerator.generateSingle(Template.TRANSACTIONS, params).orElseThrow();
  }

  @Override
  @Transactional
  public UUID create(CreateTransactionDto createTransactionDto) {
    Transaction transaction = transactionMapper.mapToEntity(createTransactionDto);
    Partner partner = partnerService.findById(createTransactionDto.getPartnerId());
    transaction.setPartner(partner);
    transactionRepository.save(transaction);
    return transaction.getId();
  }

  @Override
  public void create(UploadFileDto uploadFileDto) {
    List<CreateTransactionDto> createTransactionDtoList =
        csvReader.loadList(CreateTransactionDto.class, uploadFileDto.getFile());
    createTransactionDtoList.forEach(this::create);
  }

  @Override
  public List<Transaction> findAll(
      int page,
      int size,
      UUID partnerId,
      TransactionType type,
      LocalDate startDate,
      LocalDate endDate) {
    Map<String, Object> params = createFilterParams(partnerId, type, startDate, endDate);
    return transactionRepository.findAllFiltered(params, PageRequest.of(page, size));
  }

  @Override
  public List<Transaction> findAll(
      UUID partnerId, TransactionType type, LocalDate startDate, LocalDate endDate) {
    Map<String, Object> params = createFilterParams(partnerId, type, startDate, endDate);
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
    transactionRepository.save(updatedTransaction);
  }

  @Override
  @Transactional
  public void collect(UUID id) {
    Transaction transaction = findById(id);
    if (transaction.getStatus() != TransactionStatus.DONE) {
      transaction.setUpdatedOn(LocalDateTime.now(ZoneOffset.UTC));
      transaction.setStatus(TransactionStatus.DONE);
      transactionRepository.save(transaction);
    }
  }

  @Override
  public void collect(Iterable<UUID> ids) {
    ids.forEach(this::collect);
  }

  private Map<String, Object> createFilterParams(
      UUID partnerId, TransactionType type, LocalDate startDate, LocalDate endDate) {
    Map<String, Object> params = new HashMap<>();
    if (partnerId != null) {
      params.put("partnerId", partnerId);
    }
    if (type != null) {
      params.put("type", type);
    }
    if (startDate != null) {
      params.put("startDate", startDate);
    }
    if (endDate != null) {
      params.put("endDate", endDate);
    }
    return params;
  }
}
