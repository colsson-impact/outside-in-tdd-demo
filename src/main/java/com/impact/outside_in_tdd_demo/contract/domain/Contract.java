package com.impact.outside_in_tdd_demo.contract.domain;

public class Contract {

    public record ContractEssence(ContractId contractId, BrandId brandId, PartnerId partnerId, Percentage commissionPercentage) {

    }

    private enum ContractStatus {
        PROPOSED, ACCEPTED, DEACTIVATED
    }

    private ContractId id;

    private BrandId brandId;
    private PartnerId partnerId;

    private ContractStatus status;

    private Percentage commissionPercentage;

    private Contract(BrandId brandId, PartnerId partnerId, Percentage commissionPercentage) {
        this.id = ContractId.newId();
        this.brandId = brandId;
        this.partnerId = partnerId;
        this.commissionPercentage = commissionPercentage;
    }

    public static Contract propose(BrandId brandId, PartnerId partnerId, Percentage commissionPercentage) {
        return new Contract(brandId, partnerId, commissionPercentage);
    }

    public double commission(double saleAmount) {
        return commissionPercentage.of(saleAmount);
    }

    public ContractId getId() {
        return id;
    }

    public ContractEssence essence(){
        return new ContractEssence(id, brandId, partnerId, commissionPercentage);
    }

}
