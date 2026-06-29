package com.distribuited.systems.api_gateway_teenants.Controller;

import com.distribuited.systems.api_gateway_teenants.Repositories.ApiKeyRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.distribuited.systems.api_gateway_teenants.Dto.ApiKeyResponse;
import com.distribuited.systems.api_gateway_teenants.Dto.GenerateKeyRequest;
import com.distribuited.systems.api_gateway_teenants.Entities.ApiKeyEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/api/v1/internal/teenant-provider")
public class ApiKeyGeneratorController {
    private final ApiKeyRepository apiKeyRepository;

    @Value("${app.admin.secret-token}")
    private String adminSecret;


    ApiKeyGeneratorController(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }


    @PostMapping("/generate-key")
    public ResponseEntity<?> postMethodName(@RequestBody GenerateKeyRequest request) {
        if(! request.getAdminToken().equals(adminSecret)){
            return ResponseEntity.status(404).build();
        }

        String apiKey = "sk_" + UUID.randomUUID().toString();
        ApiKeyEntity key= new ApiKeyEntity();
        key.setApiKey(apiKey);
        key.setTeenantId(request.getTeenantId());
        key.setRateLimit(request.getRateLimiter());
        
        apiKeyRepository.save(key);

        return ResponseEntity.ok(new ApiKeyResponse(apiKey));

    }
    
}
