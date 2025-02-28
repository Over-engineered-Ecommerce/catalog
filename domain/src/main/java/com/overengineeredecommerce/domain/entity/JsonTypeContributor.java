package com.overengineeredecommerce.domain.entity;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import org.hibernate.boot.model.TypeContributions;
import org.hibernate.boot.model.TypeContributor;
import org.hibernate.service.ServiceRegistry;

public class JsonTypeContributor implements TypeContributor {

    @Override
    public void contribute(TypeContributions typeContributions, ServiceRegistry serviceRegistry) {
        typeContributions.contributeType(JsonType.INSTANCE);
    }
}


