package com.tweetapp.services;

import com.tweetapp.dao.TweetRepository;
import com.tweetapp.entities.Like;
import com.tweetapp.entities.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
	
	public Tweet save(Tweet save) {
		return tweetRepository.save(save);
	}
	
	public void deleteById(Integer tweetId) {
		tweetRepository.deleteById(tweetId);
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

	public void actionTweet(String username, Integer tweetId, String action) {
		
		switch (action) {
			case "like": likeTweet(username, tweetId);
			             break;
			case "unlike": unLikeTweet(username, tweetId);
							break;
			default:
						break;
		}
		
	}

	private void unLikeTweet(String username, Integer tweetId) {
		Tweet tweet2 = getTweetById(tweetId);
		tweet2.getLike().setNoOfLikes(tweet2.getLike().getNoOfLikes() - 1);
		List<String> existingLikedUsers = tweet2.getLike().getDetails();
		existingLikedUsers.remove(username);
		tweet2.getLike().setDetails(existingLikedUsers);
		save(tweet2);
	}

	private void likeTweet(String username, Integer tweetId) {
		Tweet tweet2 = getTweetById(tweetId);
		Like like = new Like();
		if ( tweet2.getLike() == null) {
			like.setNoOfLikes(1);
			List<String> users = new ArrayList<>();
			users.add(username);
			like.setDetails(users);
		} else {
			like.setNoOfLikes(tweet2.getLike().getNoOfLikes() + 1);
			List<String> existingLikedUsers = tweet2.getLike().getDetails();
			existingLikedUsers.add(username);
			like.setDetails(existingLikedUsers);
		}
		tweet2.setLike(like);
		save(tweet2);
	}
}
