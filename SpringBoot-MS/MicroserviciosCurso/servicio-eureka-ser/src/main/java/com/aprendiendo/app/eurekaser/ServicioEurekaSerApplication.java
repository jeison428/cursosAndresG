package com.aprendiendo.app.eurekaser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ServicioEurekaSerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioEurekaSerApplication.class, args);
	}

}
