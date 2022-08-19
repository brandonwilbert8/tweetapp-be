package com.tweetapp.entities;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("tweet")
public class Tweet {

	@Id
	private Integer tweetId;
	
	@Max(value = 144)
	@NotBlank(message = "tweet Cannot be Blank.please enter tweet ")
	@Pattern(regexp = "^[a-zA-Z0-9]")
	private String tweet;

	private String username;
	
	private Integer like;
	
	private List<Tweet> replies;
	
	/*private List<User> users;
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}*/
	
	public Tweet(String tweet, Integer tweetId) {
		super();
		this.tweetId = tweetId;
		this.tweet = tweet;
	}

	public Integer getLike() {
		return like;
	}

	public void setLike(Integer like) {
		this.like = like;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getTweetId() {
		return tweetId;
	}

	public void setTweetId(Integer tweetId) {
		this.tweetId = tweetId;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public List<Tweet> getReplies() {
		return replies;
	}

	public void setReplies(List<Tweet> replies) {
		this.replies = replies;
	}
	
}
