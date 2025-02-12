package com.impact.outside_in_tdd_demo.contract.domain;

import java.util.UUID;

import com.impact.outside_in_tdd_demo.shared.domain.BaseEntityId;


// Should be in a Partner package - but for the sake of the example, it is here
public class PartnerId extends BaseEntityId {

    private PartnerId(UUID id) {
        super(id);
    }

    public static PartnerId newId() {
        return new PartnerId(UUID.randomUUID());
    }

    public static PartnerId fromString(String id) {
        return new PartnerId(UUID.fromString(id));
    }
}

