package com.distribuited.systems.api_gateway_teenants.Dto;

public class GenerateKeyRequest {
    private String adminToken;
    private Integer teenantId;
    private Integer rateLimiter;
    public String getAdminToken() {
        return adminToken;
    }
    public void setAdminToken(String adminToken) {
        this.adminToken = adminToken;
    }
    public Integer getTeenantId() {
        return teenantId;
    }
    public void setTeenantId(Integer teenantId) {
        this.teenantId = teenantId;
    }
    public Integer getRateLimiter() {
        return rateLimiter;
    }
    public void setRateLimiter(Integer rateLimiter) {
        this.rateLimiter = rateLimiter;
    }
    
    
}
