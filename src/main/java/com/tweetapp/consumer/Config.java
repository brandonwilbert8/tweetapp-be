package com.tweetapp.consumer;


//@EnableKafka
//@Configuration
public class Config {
//
//    @Value(value = "${kafka.bootstrapAddress}")
//    private String bootstrapAddress;
//
//    @Value(value = "${kafka.groupId}")
//    private String groupId;
//
//    @Value(value = "${kafka.jaas}")
//    private String jaas;
//
//    @Bean
//    public ConsumerFactory<String, Integer> consumerFactory() {
//        Map<String, Object> properties = new HashMap<>();
//        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
//        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
////        //Set these if using SASL authentication or Confluent Cloud
////        properties.put("security.protocol", "SASL_SSL");
////        properties.put("sasl.mechanism", "PLAIN");
////        properties.put("sasl.jaas.config", jaas);
//        return new DefaultKafkaConsumerFactory<>(properties);
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, Integer> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, Integer> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }
}
