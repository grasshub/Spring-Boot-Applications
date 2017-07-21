package com.greenfield.RestWithSwagger.repository;

import org.springframework.data.repository.CrudRepository;

import com.greenfield.RestWithSwagger.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}

