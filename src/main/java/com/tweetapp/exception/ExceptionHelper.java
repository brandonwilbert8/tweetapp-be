package com.tweetapp.exception;

import com.tweetapp.beans.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHelper {

    @ExceptionHandler(TweetNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleTweetNotFoundException(TweetNotFoundException tweetNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(tweetNotFoundException.getMessage());
    }

    @ExceptionHandler(ReplyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleReplyNotFoundException(ReplyNotFoundException replyNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(replyNotFoundException.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException userNotFoundException) {
        UserResponse userResponse = new UserResponse (false, userNotFoundException.getMessage());
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> handleUnidentifiedException(Exception e) {
        return new ResponseEntity<>("Exception occurred" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
