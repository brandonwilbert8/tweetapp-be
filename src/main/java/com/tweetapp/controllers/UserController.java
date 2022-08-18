package com.tweetapp.controllers;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.dao.UserRepository;
import com.tweetapp.entities.Tweet;
import com.tweetapp.entities.User;
import com.tweetapp.services.TweetService;
import com.tweetapp.services.UserService;

@RestController
@RequestMapping("/api/v1.0/tweets")
@EnableMongoRepositories
@CrossOrigin
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public TweetService tweetService;

	@GetMapping("/users/all")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@PostMapping("/login")
	public String loginUser(@RequestBody User user) {
		String username = user.getUsername();
		String password = user.getPassword();
		User currentUser = userRepository.findByUsernameAndPassword(username, password);
		return "Successfully logged in as: " + currentUser.getUsername();
	}
	
	@PostMapping("/register")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@GetMapping("/user/search/{username}")
	public User getUserDetails(@PathVariable final String username) {
		return userRepository.findByUsername(username);
	}
	
	@GetMapping("/{username}/forgot")
	public String getPassword(@PathVariable final String username) {
		User currentUser = userRepository.findByUsername(username);
		return "password: " + currentUser.getPassword();
	}
}