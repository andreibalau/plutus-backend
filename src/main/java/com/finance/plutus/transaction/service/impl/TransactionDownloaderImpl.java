package com.finance.plutus.transaction.service.impl;

import com.finance.plutus.app.service.PdfGenerator;
import com.finance.plutus.app.service.Template;
import com.finance.plutus.transaction.model.Transaction;
import com.finance.plutus.transaction.model.html.TransactionsParams;
import com.finance.plutus.transaction.service.TransactionDownloader;
import com.finance.plutus.transaction.service.TransactionFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/** Plutus Created by Catalin on 1/16/2021 */
@Service
@RequiredArgsConstructor
public class TransactionDownloaderImpl implements TransactionDownloader {

  private final PdfGenerator pdfGenerator;
  private final TransactionFinder transactionFinder;

  @Override
  public byte[] download() {
    List<Transaction> transactions = transactionFinder.findAll();
    TransactionsParams params = new TransactionsParams();
    params.submit(transactions);
    return pdfGenerator.generateSingle(Template.TRANSACTIONS, params).orElseThrow();
  }
}
