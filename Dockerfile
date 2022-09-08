FROM openjdk:18

COPY target/tweetapp.jar tweetapp.jar

CMD java -jar tweetapp.jar

