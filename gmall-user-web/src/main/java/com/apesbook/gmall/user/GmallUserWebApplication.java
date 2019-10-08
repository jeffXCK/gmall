package com.apesbook.gmall.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"com.apesbook.gmall"}, exclude={DataSourceAutoConfiguration.class})
public class GmallUserWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(GmallUserWebApplication.class, args);
	}

}
