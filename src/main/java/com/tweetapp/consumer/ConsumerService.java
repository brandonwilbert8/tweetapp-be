package com.tweetapp.consumer;

import com.tweetapp.dao.TweetRepository;
import com.tweetapp.entities.Tweet;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    private final TweetRepository tweetRepository;

    public ConsumerService(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @KafkaListener(topics = "rest-spring-boot-integration", containerFactory = "kafkaListenerContainerFactory")
    public void consume(String message) {
        Tweet tweet = new Tweet();
        if (message.equals("delete")) {
            tweetRepository.deleteById(tweet.getTweetId());
        }
    }
}
