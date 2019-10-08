package com.finance.plutus.service.exchange;

import com.finance.plutus.model.exchange.dto.ExchangeDto;
import com.finance.plutus.repository.exchange.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.finance.plutus.util.DateTimeUtils.DATE_FORMAT;
import static com.finance.plutus.util.DateTimeUtils.parse;

/**
 * Plutus
 * Created by catalin on 08.10.2019
 */
@RequiredArgsConstructor
@Service
public class ExchangeService {

    private final ExchangeRepository exchangeRepository;

    public List<ExchangeDto> findAll() {
        return exchangeRepository
                .findAll()
                .stream()
                .map(ExchangeDto::from)
                .collect(Collectors.toList());
    }

    public List<ExchangeDto> findAllByDate(String date) {
        LocalDate localDate = parse(date, DATE_FORMAT);
        return exchangeRepository
                .findAllByDate(localDate.toEpochDay())
                .stream()
                .map(ExchangeDto::from)
                .collect(Collectors.toList());
    }

}
