package com.example.carrito.controllers;

import com.example.carrito.models.Product;
import com.example.carrito.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired

    private ProductRepository productRepository;
    
    @PostMapping
    public @ResponseBody String addNewProduct (
        // @RequestParam Long id, 
        @RequestParam String name, 
        @RequestParam String description, 
        @RequestParam Double price, 
        @RequestParam Integer qty, 
        @RequestParam String type) {

            Product product = new Product();
            product.setDescription(description);
            // product.setId(id);
            product.setName(name);
            product.setType(type);
            product.setQty(qty);
            product.setPrice(price);

            productRepository.save(product);

            return "Se guardo!";
    }

}

// ./gradlew bootRun
