package com.impact.outside_in_tdd_demo.contract.domain;

import java.util.UUID;

import com.impact.outside_in_tdd_demo.shared.domain.BaseEntityId;



public class BrandId extends BaseEntityId {

    private BrandId(UUID id) {
        super(id);
    }

    public static BrandId newId() {
        return new BrandId(UUID.randomUUID());
    }

    public static BrandId fromString(String id) {
        return new BrandId(UUID.fromString(id));
    }

}
