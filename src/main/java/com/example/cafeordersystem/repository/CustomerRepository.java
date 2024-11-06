package com.example.cafeordersystem.repository;

import com.example.cafeordersystem.domain.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
