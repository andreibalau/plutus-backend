package com.finance.plutus.service.exchange;

import static com.finance.plutus.util.DateTimeUtils.DATE_FORMAT;
import static com.finance.plutus.util.DateTimeUtils.parse;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.finance.plutus.model.exchange.dto.ExchangeDto;
import com.finance.plutus.repository.exchange.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * Plutus
 * Created by catalin on 08.10.2019
 */
@RequiredArgsConstructor
@Service
public class ExchangeService {

    private final ExchangeRepository exchangeRepository;
    private final ModelMapper modelMapper;

    public List<ExchangeDto> findAll() {
        return exchangeRepository
                .findAll()
                .stream()
                .map(exchangeHistory -> modelMapper.map(exchangeHistory, ExchangeDto.class))
                .collect(Collectors.toList());
    }

    public List<ExchangeDto> findAllByDate(String date) {
        LocalDate localDate = parse(date, DATE_FORMAT);
        return exchangeRepository
                .findAllByDate(localDate.toEpochDay())
                .stream()
                .map(exchangeHistory -> modelMapper.map(exchangeHistory, ExchangeDto.class))
                .collect(Collectors.toList());
    }

}
