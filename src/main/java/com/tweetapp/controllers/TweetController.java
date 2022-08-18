package com.tweetapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.dao.TweetRepository;
import com.tweetapp.entities.Tweet;
import com.tweetapp.entities.User;
import com.tweetapp.services.TweetService;
import com.tweetapp.services.UserService;


@RestController
@RequestMapping("/api/v1.0/tweets")
@EnableMongoRepositories
@CrossOrigin
public class TweetController {

	@Autowired
	public TweetRepository tweetRepository;
	
	@Autowired
	public TweetService tweetService;
	
	@Autowired
	public UserService userService;
	
	@GetMapping("/all")
	public List<Tweet> getAllTweet() {
		return tweetService.getAllTweets();	
	}
	
	@GetMapping("/{username}")
	public List<Tweet> getUserTweet(@PathVariable String username) {
		return tweetService.getTweetsFromUsername(username);
	}
	
	@GetMapping("/{username}/{tweetId}")
	public Tweet getUserTweetById(@PathVariable String username, @PathVariable Integer tweetId) {
		return tweetService.getTweetById(tweetId);
	}
	
	@PostMapping("/{username}/add")
	public Tweet createTweet(@RequestBody Tweet tweet, @PathVariable String username) {
		return tweetService.createTweet(tweet);
	}
	
	@PutMapping("/{username}/update/{tweetId}")
	public String updateTweet(@RequestBody Tweet tweet, @PathVariable String username, @PathVariable Integer tweetId) {
		Tweet tweet1 = tweetService.getTweetById(tweetId);
		tweet1.setTweet(tweet.getTweet());
		tweetRepository.save(tweet1);
		return "updated tweet: " + tweetId;
	}
	
	@DeleteMapping("/{username}/delete/{tweetId}")
	public String deleteTweet(@PathVariable String username, @PathVariable Integer tweetId) {
		tweetRepository.deleteById(tweetId);
		return "deleted tweet with id: " + tweetId;
	}
	
	@PutMapping("/{username}/like/{tweetId}")
	public String likeTweet(@RequestBody Tweet tweet, @PathVariable String username, @PathVariable Integer tweetId) {
		Tweet tweet2 = tweetService.getTweetById(tweetId);
		tweet2.setLike(tweet.getLike());
		tweetRepository.save(tweet2);
		return "liked tweet with id: " + tweetId;
	}
	
	@PostMapping("/{username}/reply/{tweetId}")
	public String replyTweet(@RequestBody Tweet tweet, @PathVariable String username, @PathVariable Integer tweetId) {
		Tweet tweet3 = tweetService.getTweetById(tweetId);
		tweet3.setReplies(tweet.getReplies());
		tweetRepository.save(tweet3);
		return "replied tweet with id: " + tweetId;
	}
}
