package com.mediscreen.UserInterfaceApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UserInterfaceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserInterfaceAppApplication.class, args);
	}

}
