package com.tweetapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.dao.TweetRepository;
import com.tweetapp.entities.Tweet;
import com.tweetapp.entities.User;
import com.tweetapp.exception.TweetNotFoundException;

@Service
public class TweetService {

	@Autowired
    TweetRepository tweetRepository;
	
	public Tweet createTweet(Tweet tweet) {
		return tweetRepository.save(tweet);
	}
	
	public Tweet getTweetById(Integer tweetId) {
		return tweetRepository.findById(tweetId).get();
	}
	
	public List<Tweet> getAllTweets() {
		return tweetRepository.findAll();
	}
	
	public List<Tweet> getTweetsFromUsername(String username) {
		return tweetRepository.findByUsername(username);
	}
	
	public Tweet createReply(Tweet reply) {
		return tweetRepository.save(reply);
	}
	
	/*public Tweet findByid(Integer id) {
        if(id <= 0) {
            throw new IllegalArgumentException("Id is not valid, Please pass valid Id");
        }
        Optional<Tweet> response = tweetRepository.findById(id);

        if(!response.isPresent()){
            throw new TweetNotFoundException("Tweet not found with id"+id);
        }

        return response.get();

    }
    public Integer saveTweet(Tweet tweet){
        return tweetRepository.save(tweet).getTweetId();
    }*/
}
