package main.java.com.distribuited.systems.msvc_email.Config;

@Configuration
@ConfigurationProperties(prefix="spring.mail")
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
