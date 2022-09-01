package com.tweetapp.services;

import com.tweetapp.dao.UserRepository;
import com.tweetapp.entities.User;
import com.tweetapp.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public User createUser(User user) {
		log.info("Creating user {} - by UserService", user);
		return userRepository.save(user);
	}

	public List<User> getAllUsers() {
		log.info("Getting all users - by UserService");
		return userRepository.findAll();
	}

	public User findByUsername(String username) {
		log.info("Finding user: {} - by UserService", username);
		return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found"));
	}
	
	public User findByUsernameAndPassword(String username,String password) {
		log.info("Checking if username and password match - by UserService");
		return userRepository.findByUsernameAndPassword(username, password).orElseThrow(() -> new UserNotFoundException("User not found"));
	}

	public List<User> findEveryUsername(String username) {
		log.info("Searching for any matched username: {} - by UserService", username);
		return userRepository.findByUsernameIsLike(username);
	}

	public Optional<User> checkUsernameExists(String username) {
		log.info("Checking if username already exists: {} - by UserService", username);
		return userRepository.findByUsername(username);
	}

	public Optional<User> checkEmailExists(String email) {
		log.info("Checking if email already exists: {} - by UserService", email);
		return userRepository.findByEmail(email);
	}

}

