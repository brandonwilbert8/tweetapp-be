package com.tweetapp.consumer;

import com.tweetapp.dao.TweetRepository;
import com.tweetapp.entities.TopicMessageForTweet;
import com.tweetapp.entities.Tweet;
import com.tweetapp.services.TweetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Consumer {
    private final TweetRepository tweetRepository;

    public Consumer(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }



    @KafkaListener(topics = "tweet-operation", containerFactory = "kafkaListenerContainerFactory")
    public void consume(Object action) {
        System.out.println("Consumed message: " + action);

//        if (action.equals("delete")) {
//            log.info("Deleting a tweet of id: {}", tweetId);
//            tweetRepository.deleteById(tweetId);
//        }
    }
}
