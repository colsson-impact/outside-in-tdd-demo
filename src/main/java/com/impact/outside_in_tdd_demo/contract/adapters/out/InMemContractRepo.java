package com.impact.outside_in_tdd_demo.contract.adapters.out;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.impact.outside_in_tdd_demo.contract.application.port.out.ContractHibernator;
import com.impact.outside_in_tdd_demo.contract.application.port.out.ContractUnHibernator;
import com.impact.outside_in_tdd_demo.contract.domain.Contract;
import com.impact.outside_in_tdd_demo.contract.domain.ContractId;

@Repository
public class InMemContractRepo implements ContractHibernator, ContractUnHibernator {

    private Map<ContractId, Contract> contracts = new HashMap<>();

    @Override
    public void hibernate(Contract contract) {
       contracts.put(contract.getId(), contract);
    }


    @Override
    public Optional<Contract> awaken(ContractId contractId) {
        return contracts.containsKey(contractId) ? Optional.of(contracts.get(contractId)) : Optional.empty();
    }
}
