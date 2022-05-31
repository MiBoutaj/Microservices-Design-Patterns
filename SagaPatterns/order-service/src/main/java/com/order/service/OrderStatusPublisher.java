package com.order.service;

import com.common.dto.OrderRequestDto;
import com.common.event.OrderEvent;
import com.common.event.OrderStatus;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
@AllArgsConstructor
public class OrderStatusPublisher {

    private KafkaTemplate<String,OrderEvent> kafkaTemplate;

    public void publishOrderEvent(OrderRequestDto orderRequestDto, OrderStatus orderStatus){
        OrderEvent orderEvent = new OrderEvent(orderRequestDto,orderStatus);
       kafkaTemplate.send("OrderTopic",orderEvent);
        System.out.println(orderEvent);
    }

}
