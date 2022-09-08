package com.tweetapp.consumer;

import com.tweetapp.dao.TweetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {
    private final TweetRepository tweetRepository;

    public ConsumerService(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @KafkaListener(topics = "tweet-operation", containerFactory = "kafkaListenerContainerFactory")
    public void consume(Integer tweetId) {
        System.out.println("Consumed tweetId: " + tweetId);
        log.info("Deleting tweet by Kafka: {}", tweetId);
        tweetRepository.deleteById(tweetId);
    }
}
