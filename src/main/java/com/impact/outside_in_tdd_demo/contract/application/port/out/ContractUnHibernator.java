package com.impact.outside_in_tdd_demo.contract.application.port.out;

import java.util.Optional;

import com.impact.outside_in_tdd_demo.contract.domain.Contract;
import com.impact.outside_in_tdd_demo.contract.domain.ContractId;


public interface ContractUnHibernator {

    Optional<Contract> awaken(ContractId contractId);

}
