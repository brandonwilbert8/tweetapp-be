package com.tweetapp.consumer;

import com.tweetapp.dao.TweetRepository;
import com.tweetapp.entities.Tweet;
import com.tweetapp.services.TweetService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    private final TweetService tweetService;

        public ConsumerService(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @KafkaListener(topics = "rest-spring-boot-integration", containerFactory = "kafkaListenerContainerFactory")
    public void consume(String message) {
//        Tweet tweet = new Tweet();
//        if (message.equals("delete")) {
//            tweetService.deleteById(tweet.getTweetId());
//        }
    }
}
