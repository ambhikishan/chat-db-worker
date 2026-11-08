package com.chat.db_worker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class DbWorkerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbWorkerApplication.class, args);
	}

}
