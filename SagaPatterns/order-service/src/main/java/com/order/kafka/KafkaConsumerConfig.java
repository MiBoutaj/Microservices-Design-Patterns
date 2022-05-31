package com.order.kafka;

import com.common.event.OrderEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String boostrapServers;


    public Map<String,Object> consumerConfig(){
        HashMap<String,Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,boostrapServers);
        return props;
    }

    @Bean
    public ConsumerFactory<String, OrderEvent> ConsumerOrderFactory(){
        JsonDeserializer<OrderEvent> jsonDeserializer = new JsonDeserializer<>();
        jsonDeserializer.addTrustedPackages("com.kafka");
        return new DefaultKafkaConsumerFactory<>(
                consumerConfig(),new StringDeserializer(),
                jsonDeserializer);
    }

    @Bean
    public KafkaListenerContainerFactory<
                ConcurrentMessageListenerContainer<String,OrderEvent>
                > factory(ConsumerFactory<String,OrderEvent> orderFactory  ){
        ConcurrentKafkaListenerContainerFactory<String,OrderEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(orderFactory);
        return factory;

    }




}
