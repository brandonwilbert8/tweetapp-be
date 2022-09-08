package com.tweetapp.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.validation.constraints.NotNull;

@Component
public final class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private final KafkaTemplate<String, Integer> kafkaTemplate;

    public Producer(KafkaTemplate<String, Integer> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Integer tweetId) {
        String topicName = "tweet-operation";
        ListenableFuture<SendResult<String, Integer>> future = kafkaTemplate.send(topicName, tweetId);

        //This will check producer result asynchronously to avoid thread blocking
        future.addCallback(new ListenableFutureCallback<SendResult<String, Integer>>() {
            @Override
            public void onFailure(@NotNull Throwable throwable) {
                logger.error("Failed to send message", throwable);
            }

            @Override
            public void onSuccess(SendResult<String, Integer> stringStringSendResult) {
                logger.info("Sent message successfully");
                System.out.println("Publishing to topic: " + topicName + ", with tweetId: " + tweetId);
            }
        });
    }
}
