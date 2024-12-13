package com.example.sbj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SbjApplication {

	public static void main(String[] args) {

		System.out.println("Hello World");
		SpringApplication.run(SbjApplication.class, args);
	}

}
