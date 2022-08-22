package com.tweetapp.controllers;

import com.tweetapp.entities.Reply;
import com.tweetapp.entities.Tweet;
import com.tweetapp.services.ReplyService;
import com.tweetapp.services.TweetService;
import com.tweetapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1.0/tweets")
@EnableMongoRepositories
@CrossOrigin
public class TweetController {
	
	@Autowired
	public TweetService tweetService;

	@Autowired
	public ReplyService replyService;
	
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
		tweetService.save(tweet1);
		return "updated tweet: " + tweetId;
	}
	
	@DeleteMapping("/{username}/delete/{tweetId}")
	public String deleteTweet(@PathVariable String username, @PathVariable Integer tweetId) {
		tweetService.deleteById(tweetId);
		return "deleted tweet with id: " + tweetId;
	}
	
	@PutMapping("/{username}/{action}/{tweetId}")
	public String likeTweet(@PathVariable String username, @PathVariable String action, @PathVariable Integer tweetId) {
		tweetService.actionTweet(username, tweetId, action);
		return "liked tweet with id: " + tweetId;
	}

	@PostMapping("/{username}/reply/{id}")
	public String replyTweet(@RequestBody Reply reply, @PathVariable String username, @PathVariable Integer id) {
		tweetService.replyTweet(reply, id);
		return "replied tweet with id: " + id;
	}
}
