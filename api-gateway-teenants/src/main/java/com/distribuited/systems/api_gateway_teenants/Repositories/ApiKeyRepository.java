package com.distribuited.systems.api_gateway_teenants.Repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.distribuited.systems.api_gateway_teenants.Entities.ApiKeyEntity;

public interface ApiKeyRepository extends CrudRepository<Integer, ApiKeyEntity> {
    
    @Query("select p.teenantId FROM ApiKeyEntity p WHERE p.apiKey = ?1 ")
    String findByIdTeenantByApiKey(String apiKey);

    void save(ApiKeyEntity key);
}
