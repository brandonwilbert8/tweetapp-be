package com.tweetapp.producer;

//@Component
public final class Producer {
//    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
//
//    @Autowired
//    private final KafkaTemplate<String, Integer> kafkaTemplate;
//
//    public Producer(KafkaTemplate<String, Integer> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    public void sendMessage(Integer tweetId) {
//        String topicName = "tweet-operation";
//        ListenableFuture<SendResult<String, Integer>> future = kafkaTemplate.send(topicName, tweetId);
//
//        //This will check producer result asynchronously to avoid thread blocking
//        future.addCallback(new ListenableFutureCallback<SendResult<String, Integer>>() {
//            @Override
//            public void onFailure(@NotNull Throwable throwable) {
//                logger.error("Failed to send message", throwable);
//            }
//
//            @Override
//            public void onSuccess(SendResult<String, Integer> stringStringSendResult) {
//                logger.info("Sent message successfully");
//                System.out.println("Publishing to topic: " + topicName + ", with tweetId: " + tweetId);
//            }
//        });
//    }
}
