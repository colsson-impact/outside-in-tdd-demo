package com.impact.outside_in_tdd_demo.contract.domain;

import java.util.UUID;

import com.impact.outside_in_tdd_demo.shared.domain.BaseEntityId;


public class ContractId extends BaseEntityId {

    private ContractId(UUID id) {
        super(id);
    }

    public static ContractId newId() {
        return new ContractId(UUID.randomUUID());
    }

    public static ContractId fromString(String id) {
        return new ContractId(UUID.fromString(id));
    }

}