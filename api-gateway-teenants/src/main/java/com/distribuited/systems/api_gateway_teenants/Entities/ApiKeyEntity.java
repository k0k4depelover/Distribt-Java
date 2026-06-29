package com.distribuited.systems.api_gateway_teenants.Entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "api_keys")
public class ApiKeyEntity {
    @Column(name = "id")
    private Integer Id;

    @Column(name= "teenant_id")
    private Integer teenantId;

    @Column(name = "api_key")
    private String apiKey;

    @Column(name = "rate_limit")
    private Integer rateLimit;

    @Column(name = "created_at")
    private Date createdAt;

    public Integer getId() {
        return Id;
    }


    public void setId(Integer id) {
        Id = id;
    }


    public Integer getTeenantId() {
        return teenantId;
    }


    public void setTeenantId(Integer teenantId) {
        this.teenantId = teenantId;
    }


    public String getApiKey() {
        return apiKey;
    }


    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }


    public Integer getRateLimit() {
        return rateLimit;
    }


    public void setRateLimit(Integer rateLimit) {
        this.rateLimit = rateLimit;
    }


    public Date getCreatedAt() {
        return createdAt;
    }


    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }





}
