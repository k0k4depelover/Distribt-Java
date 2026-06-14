package com.distribuited.systems.msvc_email.Configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.email")
public class MailProperties {
    public String username;
    public String port;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPort() {
        return port;
    }
    public void setPort(String port) {
        this.port = port;
    }  
    
    
    
}
