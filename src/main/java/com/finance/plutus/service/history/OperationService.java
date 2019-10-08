package com.finance.plutus.service.history;

import com.finance.plutus.model.history.Operation;
import com.finance.plutus.repository.history.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Plutus
 * Created by catalin on 08.10.2019
 */
@Service
@RequiredArgsConstructor
public class OperationService {

    private final OperationRepository operationRepository;

    public void trackOperation(String operationName, String user) {
        Operation operation = Operation
                .builder()
                .name(operationName)
                .user(user)
                .datetime(System.currentTimeMillis())
                .build();
        operationRepository.save(operation);
    }

}
