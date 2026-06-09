package com.distribuited.systems.msvc_email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class MsvcEmailApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcEmailApplication.class, args);
	}

}
