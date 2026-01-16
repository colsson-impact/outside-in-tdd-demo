package com.impact.outside_in_tdd_demo.contract.application.port.in;

import java.util.Optional;

import org.javamoney.moneta.Money;

import com.impact.outside_in_tdd_demo.contract.domain.ContractId;


public interface ContractCommissionUseCase {
    Optional<Money> calculateCommission(ContractId contractId, Money saleAmount);

}
