package com.food.ordering.system.order.service.domain;

import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    public OrderDomainService orderDomainService(){
        return new OrderDomainServiceImpl();
    }
}
