package com.tweetapp.controllers;

import com.tweetapp.beans.UserResponse;
import com.tweetapp.entities.User;
import com.tweetapp.services.TweetService;
import com.tweetapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/tweets")
@EnableMongoRepositories
@CrossOrigin
public class UserController {

	@Autowired
	public UserService userService;
	
	@Autowired
	public TweetService tweetService;

	@GetMapping("/users/all")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user) {
		if (!validateRequest(user)) {
			return new ResponseEntity<>("test", HttpStatus.BAD_REQUEST);
		}
		User currentUser = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		UserResponse userResponse = new UserResponse (true,  "Successfully logged in as: " + currentUser.getUsername());
		return new ResponseEntity<>(userResponse, HttpStatus.OK);

	}
	
	@PostMapping("/register")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	@GetMapping("/{username}/forgot")
	public String getPassword(@PathVariable final String username) {
		User currentUser = userService.findByUsername(username);
		return "password: " + currentUser.getPassword();
	}
	
	private boolean validateRequest(User user) {
		return true;
	}

	@GetMapping("/user/search/{username}")
	public List<User> getUserDetails(@PathVariable final String username) {
		return userService.findEveryUsername(username);
	}

}