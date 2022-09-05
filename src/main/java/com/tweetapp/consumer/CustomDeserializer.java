package com.tweetapp.consumer;

        import com.fasterxml.jackson.databind.ObjectMapper;
        import com.tweetapp.entities.TopicMessageForTweet;
        import org.apache.kafka.common.errors.SerializationException;
        import org.apache.kafka.common.serialization.Deserializer;

        import java.util.Map;

public class CustomDeserializer implements Deserializer<TopicMessageForTweet> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public TopicMessageForTweet deserialize(String topic, byte[] data) {
        try {
            if (data == null){
                System.out.println("Null received at deserializing");
                return null;
            }
            System.out.println("Deserializing...");
            return objectMapper.readValue(data, TopicMessageForTweet.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to TopicMessageForTweet", e);
        }
    }

    @Override
    public void close() {
    }
}