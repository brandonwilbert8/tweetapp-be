package com.tweetapp.exception;

@SuppressWarnings("serial")
public class TweetNotFoundException extends RuntimeException {
	public TweetNotFoundException(String s) {
        super(s);
    }
}
