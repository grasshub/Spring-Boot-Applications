package com.greenfield.springbootmvc.repositories;

import org.springframework.data.repository.CrudRepository;

import com.greenfield.springbootmvc.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
