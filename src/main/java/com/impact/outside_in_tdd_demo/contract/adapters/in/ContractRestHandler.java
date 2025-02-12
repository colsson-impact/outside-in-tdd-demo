package com.impact.outside_in_tdd_demo.contract.adapters.in;

import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.impact.outside_in_tdd_demo.contract.application.port.in.ContractCommissionUseCase;
import com.impact.outside_in_tdd_demo.contract.application.port.in.GetContractUseCase;
import com.impact.outside_in_tdd_demo.contract.application.port.in.ProposeContractUseCase;
import com.impact.outside_in_tdd_demo.contract.domain.BrandId;
import com.impact.outside_in_tdd_demo.contract.domain.Contract;
import com.impact.outside_in_tdd_demo.contract.domain.ContractId;
import com.impact.outside_in_tdd_demo.contract.domain.PartnerId;
import com.impact.outside_in_tdd_demo.contract.domain.Percentage;


@RestController
@RequestMapping("/contracts")
public class ContractRestHandler {

    private ProposeContractUseCase proposeContractUseCase;

    private GetContractUseCase getContractUseCase;

    private ContractCommissionUseCase contractCommissionUseCase;

    public ContractRestHandler(ProposeContractUseCase proposeContractUseCase, GetContractUseCase getContractUseCase, ContractCommissionUseCase contractCommissionUseCase) {
        this.proposeContractUseCase = proposeContractUseCase;
        this.getContractUseCase = getContractUseCase;
        this.contractCommissionUseCase = contractCommissionUseCase;
    }

    @PostMapping
    public ProposeContractResponse proposeContract(@RequestBody ProposeContractRequest proposeContractRequest) {

        ContractId contractId = proposeContractUseCase.proposeContract(
                BrandId.fromString(proposeContractRequest.brandId()), PartnerId.fromString(proposeContractRequest.partnerId()),
                Percentage.getInstanceOf(proposeContractRequest.commissionPercentage()));


        return new ProposeContractResponse(
                contractId.val(), proposeContractRequest.brandId(), proposeContractRequest.partnerId(),
                proposeContractRequest.commissionPercentage());

    }

    @GetMapping("/{contractId}")
    public ProposeContractResponse getContract(@PathVariable String contractId) {

        return getContractUseCase.getContract(ContractId.fromString(contractId)).map(ContractRestHandler::mapToResponse)
                .orElseThrow(() -> new UnsupportedOperationException("Contract not found case not implemented"));

    }

    @GetMapping("/{contractId}/commission")
    public CommissionContractResponse calculateCommission(@PathVariable String contractId, @RequestParam double saleAmount) {
        return contractCommissionUseCase.calculateCommission(ContractId.fromString(contractId), saleAmount)
                .map(commission -> mapToResponse(ContractId.fromString(contractId), saleAmount, commission))
                .orElseThrow(() -> new NoSuchElementException("Contract not found case not implemented"));
    }

    private static CommissionContractResponse mapToResponse(ContractId contractId, double saleAmount, double commission) {
        return new CommissionContractResponse(contractId.val(), saleAmount, commission);
    }


    private static ProposeContractResponse mapToResponse(Contract.ContractEssence contractEssence) {
        return new ProposeContractResponse(
                contractEssence.contractId().val(), contractEssence.brandId().val(),
                contractEssence.partnerId().val(), contractEssence.commissionPercentage().getValue());
    }

}
