package com.tweetapp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.entities.Tweet;


@Repository
public interface TweetRepository extends MongoRepository<Tweet,Integer> {

	List<Tweet> findByUsername(String username);

}
