package com.serverservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //now all microservices will register here...and now it handles all the port numbers
public class ServerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerServiceApplication.class, args);
	}

}
