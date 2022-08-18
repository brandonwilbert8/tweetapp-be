package com.tweetapp.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.dao.TweetRepository;
import com.tweetapp.dao.UserRepository;
import com.tweetapp.entities.Tweet;
import com.tweetapp.entities.User;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TweetRepository tweetRepository;
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public User getUserById(String userId) {
		return userRepository.findById(userId).get();
	}
	
	public User getUserByUsername(String username) {
		return userRepository.findById(username).get();
	}
	
	public User getUserByPassword(String password) {
		return userRepository.findById(password).get();
	}
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public User saveTweet(Tweet tweet, String username){
        User user = userRepository.findByUsername(username);
        user.getTweets().add(tweet);
        return userRepository.save(user);
    }

    /*public Set<Tweet> getTweets(String username){
        User user = userRepository.findByUsername(username);
        return user.getTweets();
    }*/
}
