package com.apesbook.gmall.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.apesbook.gmall"})
public class GmallUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GmallUserServiceApplication.class, args);
	}

}
