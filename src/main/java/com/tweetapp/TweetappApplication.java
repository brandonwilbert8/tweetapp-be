package com.tweetapp;

import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableMongoRepositories
@Generated
@EnableWebMvc
public class TweetappApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(TweetappApplication.class, args);
	}

}
