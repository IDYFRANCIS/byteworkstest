package com.francis.byteworkstest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;


@EnableAutoConfiguration(exclude = { FreeMarkerAutoConfiguration.class })
@SpringBootApplication
public class ByteworkstestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ByteworkstestApplication.class, args);
	}

}
