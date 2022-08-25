package com.tweetapp.dao;

import com.tweetapp.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	@Override
	public List<User> findAll();

//	public List<User> findByUsernameAuto(String username);
	
	Optional<User> findById(String userId);

	Optional<User> findByUsername(String username);
	
	Optional<User> findByPassword(String password);
	
	Optional<User> findByUsernameAndPassword(String username, String password);

}
