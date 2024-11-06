package com.example.cafeordersystem.service;

import com.example.cafeordersystem.domain.CreateOrder;
import com.example.cafeordersystem.domain.Order;
import com.example.cafeordersystem.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void newOrder(CreateOrder createOrder){
        Order entity = Order.newOrder(createOrder);
        orderRepository.save(entity);
    }
}
