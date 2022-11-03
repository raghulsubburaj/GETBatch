package com.Teleapps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages=("com.Teleapps"))
public class GetBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetBatchApplication.class, args);
	}

}
