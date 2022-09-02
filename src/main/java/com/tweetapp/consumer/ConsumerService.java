package com.tweetapp.consumer;

import com.tweetapp.dao.TweetRepository;
import com.tweetapp.entities.Tweet;
import com.tweetapp.services.TweetService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    private final TweetRepository tweetRepository;

    public ConsumerService(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @KafkaListener(topics = "tweet-operation", containerFactory = "kafkaListenerContainerFactory")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }
}
