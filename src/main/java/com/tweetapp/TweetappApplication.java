package com.tweetapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.tweetapp.dao.UserRepository;
import com.tweetapp.entities.User;

@SpringBootApplication
@EnableMongoRepositories
public class TweetappApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(TweetappApplication.class, args);
	}

}
