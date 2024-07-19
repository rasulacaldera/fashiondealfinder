package com.bootsandcodes.fashiondealfinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FashiondealfinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(FashiondealfinderApplication.class, args);
	}

}
