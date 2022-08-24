package com.tweetapp.exception;

public class ReplyNotFoundException extends RuntimeException {
    public ReplyNotFoundException(String s) {
        super(s);
    }
}
