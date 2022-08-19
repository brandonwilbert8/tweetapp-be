package com.tweetapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.beans.UserResponse;
import com.tweetapp.entities.User;
import com.tweetapp.services.TweetService;
import com.tweetapp.services.UserService;

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
		String message = null;
		if (!validateRequest(user)) {
			message =   "Unsuccessful";
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		
		try {
		User currentUser = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		message =  "Successfully logged in as: " + currentUser.getUsername();
		//UserResponse userResponse = new UserResponse (true, message);
		return new ResponseEntity<>(message, HttpStatus.OK);
		} 
		catch(Exception e) {
			UserResponse userResponse = new UserResponse (false, "username/password combination doesn't match");
			message = "username/password combination doesn't match";
			return new ResponseEntity<>(message, HttpStatus.OK); 
		}
		
	}
	
	@PostMapping("/register")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@GetMapping("/user/search/{username}")
	public User getUserDetails(@PathVariable final String username) {
		return userService.findByUsername(username);
	}
	
	@GetMapping("/{username}/forgot")
	public String getPassword(@PathVariable final String username) {
		User currentUser = userService.findByUsername(username);
		return "password: " + currentUser.getPassword();
	}
	
	private boolean validateRequest(User user) {
		return true;
	}
}