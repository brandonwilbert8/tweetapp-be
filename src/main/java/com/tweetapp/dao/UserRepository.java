package com.tweetapp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.entities.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	@Override
	public List<User> findAll();
	
	Optional<User> findById(String userId);

	User findByUsername(String username);
	
	User findByPassword(String password);
	
	User findByUsernameAndPassword(String username, String password);
}
