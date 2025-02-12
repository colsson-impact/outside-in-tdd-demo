package com.impact.outside_in_tdd_demo.contract.application.port.in;

import com.impact.outside_in_tdd_demo.contract.domain.BrandId;
import com.impact.outside_in_tdd_demo.contract.domain.ContractId;
import com.impact.outside_in_tdd_demo.contract.domain.PartnerId;
import com.impact.outside_in_tdd_demo.contract.domain.Percentage;


public interface ProposeContractUseCase {

    ContractId proposeContract(BrandId brandId, PartnerId partnerId, Percentage commissionPercentage);
}
