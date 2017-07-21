package com.greenfield.springmvcactivemq.repository;

import org.springframework.data.repository.CrudRepository;

import com.greenfield.springmvcactivemq.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}
