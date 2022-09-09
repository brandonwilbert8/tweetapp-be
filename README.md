# TweetApp_BackEnd - Spring Boot Application

## Description

MC1 Backend - Spring Boot Application for FSE1 Accreditation

## Getting Started

### Dependencies

* Windows / MacOS required to run the application

### Executing program

**Before running the Spring Boot application, make sure that Kafka is configured and running locally**

* Download kafka from: https://www.apache.org/dyn/closer.cgi?path=/kafka/3.2.1/kafka_2.13-3.2.1.tgz

* When finish downloading, extract Kafka
```
tar -xzf kafka_2.13-3.2.1.tgz
cd kafka_2.13-3.2.1
```

* Next, need to start zookeper service
```
bin/zookeeper-server-start.sh config/zookeeper.properties
```

* Open another terminal, then start the broker service
```
bin/kafka-server-start.sh config/server.properties
```

**When both zookeeper and broker services are running, then run the Spring Boot Application**

* How to run the program
```
mvn clean install
```
* Run TweetappApplication.java class

**Notes: need to adjust IP address for kafka (on application.properties) and prometheus (prometheus.yml) when necessary**

## Author

* Brandon Wilbert [brandon.wilbert@cognizant.com]

## Branch Details

* develop
    * Initial Release
* logging
    * Added a logging system - Slf4j
    * Various bug fixes and optimizations
* kafka
    * Added initial kafka configuration
* kafka_latest
    * Added additional kafka configuration
* docker_latest
    * Added docker compose file
    * Added Dockerfile for containerising the application
    * Added actuator
    * Added prometheus
    * Added grafana
    * This branch is up to date with branch **master**

## Local Port details

* localhost:8081          --> Spring Boot Application
* localhost:3031          --> React / Frontend
* localhost:3000          --> grafana
* localhost:9090          --> prometheus
* localhost:8081/actuator --> actuator info
