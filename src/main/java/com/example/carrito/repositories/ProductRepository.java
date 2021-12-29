package com.example.carrito.repositories;

import com.example.carrito.models.Product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
