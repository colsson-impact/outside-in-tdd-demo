package com.impact.outside_in_tdd_demo.contract.application.port.in;

import java.util.Optional;

import com.impact.outside_in_tdd_demo.contract.domain.ContractId;


public interface ContractCommissionUseCase {
    Optional<Double> calculateCommission(ContractId contractId, double saleAmount);

}
