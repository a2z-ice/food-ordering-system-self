package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
public class OrderCreatedApplicationEventListener {
    private final OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher;

    public OrderCreatedApplicationEventListener(OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher) {
        this.orderCreatedPaymentRequestMessagePublisher = orderCreatedPaymentRequestMessagePublisher;
    }
    @TransactionalEventListener
    void process(OrderCreatedEvent orderCreatedEvent){

    }

}
