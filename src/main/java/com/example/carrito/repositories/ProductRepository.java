package com.example.carrito.repositories;

import com.example.carrito.models.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Este es el repositorio
// Es el que se encarga de hacer todas las operaciones del CRUD
// Eso se hace con la anotacion repository y utilizando el extends JpaRepository
// Esto por debajo tiene toda la logica para hacer las consultas SQL en la base de datos 
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

}
