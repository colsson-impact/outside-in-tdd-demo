package com.impact.outside_in_tdd_demo.contract.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class ContractTest {

    @Test
    void essenceBrandId(){

        BrandId brandId = BrandId.newId();
        PartnerId partnerId = PartnerId.newId();

        Contract contract = Contract.propose(brandId, partnerId, Percentage.getInstanceOf(13.5));

        assertEquals(brandId, contract.essence().brandId());

    }

    @Test
    void saleAmountOf1000CommissionOf10Percent(){
        BrandId brandId = BrandId.newId();
        PartnerId partnerId = PartnerId.newId();

        Contract contract = Contract.propose(brandId, partnerId, Percentage.getInstanceOf(10));

        assertEquals(100.0, contract.commission(1000));
    }


}