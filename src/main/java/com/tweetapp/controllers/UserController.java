package com.tweetapp.controllers;

import com.tweetapp.beans.UserResponse;
import com.tweetapp.entities.User;
import com.tweetapp.services.TweetService;
import com.tweetapp.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1.0/tweets")
@EnableMongoRepositories
@CrossOrigin
@Slf4j
public class UserController {

	@Autowired
	public UserService userService;
	
	@Autowired
	public TweetService tweetService;

	@GetMapping("/users/all")
	public List<User> getAllUsers() {
		log.info("Getting all available users");
		return userService.getAllUsers();
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user) {
		if (!validateRequest(user)) {
			return new ResponseEntity<>("test", HttpStatus.BAD_REQUEST);
		}
		log.info("Logging in");
		User currentUser = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		UserResponse userResponse = new UserResponse (true,  "Successfully logged in as: " + currentUser.getUsername());
		return new ResponseEntity<>(userResponse, HttpStatus.OK);

	}
	
	@PostMapping("/register")
	public User createUser(@RequestBody User user) {
		log.info("Registering a user");
		return userService.createUser(user);
	}

	@PutMapping("/{username}/forgot")
	public String getPassword(@PathVariable final String username, @RequestBody User user) {
		log.info("Resetting a password for: {}", username);
		User currentUser = userService.findByUsername(username);
		currentUser.setPassword(user.getPassword());
		userService.createUser(currentUser);
		return "Successfully reset password for: " + currentUser.getUsername();
	}
	
	private boolean validateRequest(User user) {
		return true;
	}

	@GetMapping("/user/search/{username}")
	public List<User> getUserDetails(@PathVariable final String username) {
		log.info("Searching user");
		return userService.findEveryUsername(username);
	}

	@GetMapping("/user/check/{username}")
	public Optional<User> checkUsername(@PathVariable final String username) {
		log.info("Checking if username exists: {}", username);
		return userService.checkUsernameExists(username);
	}

	@GetMapping("/user/email/{email}")
	public Optional<User> checkEmail(@PathVariable final String email) {
		log.info("Checking if email exists: {}", email);
		return userService.checkEmailExists(email);
	}
}