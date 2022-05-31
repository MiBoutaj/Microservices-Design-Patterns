package com.payment.config;

import com.common.event.OrderEvent;
import com.common.event.OrderStatus;
import com.payment.service.PaymentService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentConsumerConfig {

    @Autowired
    private PaymentService paymentService;

    @KafkaListener(topics = "OrderTopic", groupId = "groupID")
    void listener(ConsumerRecord<String,OrderEvent> consumerRecord){

        if(OrderStatus.ORDER_CREATED.equals(consumerRecord.value().getOrderStatus())){
            paymentService.newOrderEvent(consumerRecord.value());
        }else{
            paymentService.cancelOrderEvent(consumerRecord.value());
        }
    }

}
