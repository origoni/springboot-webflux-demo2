package com.millky.demo.post;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@SpringBootApplication
public class SpringbootWebfluxDemo2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWebfluxDemo2Application.class, args);
	}
}
