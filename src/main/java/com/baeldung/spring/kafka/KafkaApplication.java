package com.baeldung.spring.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class KafkaApplication {

    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext context = SpringApplication.run(KafkaApplication.class, args);

        MessageProducer producer = context.getBean(MessageProducer.class);
        MessageListener listener = context.getBean(MessageListener.class);


        /**
         * Demo 1
         */
        /*
         * Sending a Hello World message to topic 'baeldung'.
         * Must be received by:
         *
         * 1, groupId = "foo"
         * 2, groupId = "bar"
         * 3, No group id
         *
         * Totally 3 listeners get this message
         */
        //producer.sendMessage("Hello, World!");
        //listener.getLatch().await(10, TimeUnit.SECONDS);


        /**
         * Demo 2
         */
        /*
         * This demo is for show it is able to send in which parititon, and listener listen on which partition
         */
//        for (int i = 0; i < 5; i++) {
//            // The second parameter is stands for partition
//            producer.sendMessageToPartition("Hello To Partitioned Topic!", 0);
//        }
//        listener.getPartitionLatch().await(10, TimeUnit.SECONDS);


        /**
         * Demo 3
         */
        /*
         * This is for showing that, the container factory is able to filter,
         * when the incomming message contains "World", it will alto filtered
         */
//        producer.sendMessageToFiltered("Hello Baeldung!");
//        producer.sendMessageToFiltered("Hello World!");
//        listener.getFilterLatch().await(10, TimeUnit.SECONDS);


        /**
         * Demo 4
         */
        /*
         * Sending message to 'greeting' topic. This will send
         * and received a java object with the help of
         * greetingKafkaListenerContainerFactory.
         */
        producer.sendGreetingMessage(new Greeting("Greetings", "World!"));
        listener.getGreetingLatch().await(10, TimeUnit.SECONDS);

        context.close();
    }

}
