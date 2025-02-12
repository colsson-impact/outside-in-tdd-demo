package com.impact.outside_in_tdd_demo.contract.application.port.out;

import com.impact.outside_in_tdd_demo.contract.domain.Contract;


public interface ContractHibernator {

    void hibernate(Contract contract);
}
