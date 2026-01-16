package com.impact.outside_in_tdd_demo.contract.application;

import java.util.Optional;

import org.javamoney.moneta.Money;
import org.springframework.stereotype.Service;

import com.impact.outside_in_tdd_demo.contract.application.port.in.ContractCommissionUseCase;
import com.impact.outside_in_tdd_demo.contract.application.port.in.GetContractUseCase;
import com.impact.outside_in_tdd_demo.contract.application.port.in.ProposeContractUseCase;
import com.impact.outside_in_tdd_demo.contract.application.port.out.ContractUnHibernator;
import com.impact.outside_in_tdd_demo.contract.application.port.out.ContractHibernator;
import com.impact.outside_in_tdd_demo.contract.domain.BrandId;
import com.impact.outside_in_tdd_demo.contract.domain.Contract;
import com.impact.outside_in_tdd_demo.contract.domain.ContractId;
import com.impact.outside_in_tdd_demo.contract.domain.PartnerId;
import com.impact.outside_in_tdd_demo.contract.domain.Percentage;


@Service
public class ContractApplicationService implements ProposeContractUseCase, GetContractUseCase, ContractCommissionUseCase {

    private ContractHibernator contractHibernator;

    private ContractUnHibernator contractUnHibernator;

    public ContractApplicationService(ContractHibernator contractHibernator, ContractUnHibernator contractUnHibernator) {

        this.contractHibernator = contractHibernator;
        this.contractUnHibernator = contractUnHibernator;
    }

    @Override
    public ContractId proposeContract(BrandId brandId, PartnerId partnerId, Percentage commissionPercentage) {
        Contract contract = Contract.propose(brandId, partnerId, commissionPercentage);

        contractHibernator.hibernate(contract);

        return contract.getId();
    }

    @Override
    public Optional<Contract.ContractEssence> getContract(ContractId contractId) {
        return contractUnHibernator.awaken(contractId).map(Contract::essence);
    }

    @Override
    public Optional<Money> calculateCommission(ContractId contractId, Money saleAmount) {
        return contractUnHibernator.awaken(contractId).map(contract -> contract.commission(saleAmount));
    }
}
