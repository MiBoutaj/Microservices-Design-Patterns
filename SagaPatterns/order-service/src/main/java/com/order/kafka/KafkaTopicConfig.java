package com.order.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic OrderTopic(){
        return TopicBuilder.name("OrderTopic").build();
    }
    @Bean
    public NewTopic PaymentTopic(){
        return TopicBuilder.name("PaymentTopic").build();
    }

}
