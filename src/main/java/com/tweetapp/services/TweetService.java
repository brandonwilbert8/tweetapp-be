package com.tweetapp.services;

import com.tweetapp.dao.TweetRepository;
import com.tweetapp.entities.Like;
import com.tweetapp.entities.Reply;
import com.tweetapp.entities.Tweet;
import com.tweetapp.exception.TweetNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TweetService {

	@Autowired
    TweetRepository tweetRepository;

//	@Autowired
//	public Producer producer;

	@Autowired
	ReplyService replyService;
	
	public Tweet createTweet(Tweet tweet) {
		log.info("Creating a tweet: {} - by TweetService", tweet.getTweetId());
		return tweetRepository.save(tweet);
	}
	
	public Tweet getTweetById(String tweetId) {
		log.info("Getting a tweet by id: {} - by TweetService", tweetId);
		return tweetRepository.findById(tweetId).orElseThrow(() -> new TweetNotFoundException("Tweet not found"));
	}

	public List<Tweet> getAllTweets() {
		log.info("Getting all tweets from repository - by TweetService");
		return tweetRepository.findAll();
	}
	
	public List<Tweet> getTweetsFromUsername(String username) {
		log.info("Getting all tweets from user: {} - by TweetService", username);
		return tweetRepository.findByUsername(username);
	}
	
	public Tweet createReply(Tweet reply) {
		return tweetRepository.save(reply);
	}
	
	public Tweet save(Tweet save) {
		log.info("Saving a tweet - by TweetService");
		return tweetRepository.save(save);
	}
	
	public void deleteById(String tweetId) {
		log.info("Deleting a tweet of id: {}", tweetId);
		tweetRepository.deleteById(tweetId);
	}

	public void actionTweet(String username, String id, String action) {
		log.info("Performing actionTweet - by TweetService");
		switch (action) {
			case "like":
				log.info("Liking a tweet of id: {}", id);
				likeTweet(username, id);
				break;
			case "unlike":
				log.info("Unliking a tweet of id: {}", id);
				unLikeTweet(username, id);
				break;
			default:
				break;
		}
	}

	private void unLikeTweet(String username, String id) {
		Tweet mainTweet = null;
		try {
			mainTweet = getTweetById(id);
			unLikeTweet(username, mainTweet);
		}	catch(TweetNotFoundException ex) {
			Reply replyTweet = replyService.getReplyById(id);
			mainTweet = getTweetById(replyTweet.getTweetId());
			Reply replyToReplace = mainTweet.getReplies().stream().filter(
					e -> e.getReplyTweetId().equals(id)
			).findFirst().get();
			unLikeTweet(username, replyToReplace);
		}
		save(mainTweet);
	}

	private void unLikeTweet(String username, Reply replyTweet) {
		replyTweet.getLike().setNoOfLikes(replyTweet.getLike().getNoOfLikes() - 1);
		List<String> existingLikedUsers = replyTweet.getLike().getDetails();
		existingLikedUsers.remove(username);
		replyTweet.getLike().setDetails(existingLikedUsers);
	}
	private void unLikeTweet(String username, Tweet mainTweet) {
		mainTweet.getLike().setNoOfLikes(mainTweet.getLike().getNoOfLikes() - 1);
		List<String> existingLikedUsers = mainTweet.getLike().getDetails();
		existingLikedUsers.remove(username);
		mainTweet.getLike().setDetails(existingLikedUsers);
	}

	private void likeTweet(String username, String id) {
		Tweet mainTweet = null;
		try {
		mainTweet = getTweetById(id);
		likeTweet(username, mainTweet);
		}	catch(TweetNotFoundException ex) {
			Reply replyTweet = replyService.getReplyById(id);
			mainTweet = getTweetById(replyTweet.getTweetId());
			Reply replyToReplace = mainTweet.getReplies().stream().filter(
					e -> e.getReplyTweetId().equals(id)
			).findFirst().get();
			likeTweet(username, replyToReplace);
		}
		save(mainTweet);
	}

	private void likeTweet(String username, Reply replyTweet) {
		Like like = new Like();
		if ( replyTweet.getLike() == null) {
			like.setNoOfLikes(1);
			List<String> users = new ArrayList<>();
			users.add(username);
			like.setDetails(users);
		} else {
			like.setNoOfLikes(replyTweet.getLike().getNoOfLikes() + 1);
			List<String> existingLikedUsers = replyTweet.getLike().getDetails();
			existingLikedUsers.add(username);
			like.setDetails(existingLikedUsers);
		}
		replyTweet.setLike(like);
	}
	private void likeTweet(String username, Tweet mainTweet) {
		Like like = new Like();
		if ( mainTweet.getLike() == null) {
			like.setNoOfLikes(1);
			List<String> users = new ArrayList<>();
			users.add(username);
			like.setDetails(users);
		} else {
			like.setNoOfLikes(mainTweet.getLike().getNoOfLikes() + 1);
			List<String> existingLikedUsers = mainTweet.getLike().getDetails();
			existingLikedUsers.add(username);
			like.setDetails(existingLikedUsers);
		}
		mainTweet.setLike(like);
	}

	public void replyTweet(Reply reply, String id) {
		Tweet mainTweet = null;
		try {
			mainTweet = getTweetById(id);
			if( mainTweet.getReplies() == null ) {
				List<Reply> replies = new ArrayList<>();
				replies.add(reply);
				mainTweet.setReplies(replies);
			} else {
				mainTweet.getReplies().add(reply);
			}
			save(mainTweet);
			replyService.save(reply);

		} catch(TweetNotFoundException ex) {
			// reply to a reply not the main tweet
			Reply replyTweet = replyService.getReplyById(id);
			mainTweet = getTweetById(replyTweet.getTweetId());

			Reply replyToReplace = mainTweet.getReplies().stream().filter(
					e -> e.getReplyTweetId().equals(id)
			).findFirst().get();
			if( replyToReplace.getReplies() == null ) {
				List<Reply> replies = new ArrayList<>();
				replies.add(reply);
				replyToReplace.setReplies(replies);
			} else {
				replyToReplace.getReplies().add(reply);
			}
			save(mainTweet);

			if ( replyTweet.getReplies() == null ) {
				List<Reply> replies = new ArrayList<>();
				replies.add(reply);
				replyTweet.setReplies(replies);
			} else {
				replyTweet.getReplies().add(reply);
			}
			replyService.save(replyTweet);
		}
	}
}
