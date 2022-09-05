package com.tweetapp.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tweetapp.entities.TopicMessageForTweet;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

@Slf4j
public class CustomSerializer implements Serializer<TopicMessageForTweet> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String s, TopicMessageForTweet data) {
        try {
            if (data == null){
                System.out.println("Null received at serializing");
                return null;
            }
            System.out.println("Serializing...");
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error when serializing TopicMessageForTweet to byte[]");
        }
    }

    @Override
    public byte[] serialize(String topic, Headers headers, TopicMessageForTweet data) {
        return new byte[0];
    }

    @Override
    public void close() {
    }
}