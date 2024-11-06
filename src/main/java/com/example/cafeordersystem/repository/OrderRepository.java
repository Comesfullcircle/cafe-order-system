package com.example.cafeordersystem.repository;

import com.example.cafeordersystem.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}
