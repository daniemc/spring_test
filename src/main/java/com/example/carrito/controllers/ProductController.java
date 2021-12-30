package com.example.carrito.controllers;

import com.example.carrito.domain.DomainId;
import com.example.carrito.domain.Utils;
import com.example.carrito.domain.product.ProductType;
import com.example.carrito.models.Product;
import com.example.carrito.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// Se crea el endpoint / ruta con nombre prodcuts
// todos los peticiones que lleguen con la ruta products llegan aqui
// GET (obtener datos ), POST (para crear datos), PUT (para actualizar datos), DELETE (eliminar datos),
@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired

    private ProductRepository productRepository;
    
    // Todas las peticiones de tipo POST que lleguen a la ruta 'products', van a ejecutar este metodo
    @PostMapping
    public @ResponseBody String addNewProduct( 
        @RequestParam String name, 
        @RequestParam String description, 
        @RequestParam Integer price, 
        @RequestParam Integer qty) {
        
            DomainId productId = new DomainId();

            //Dado que el nombre y la descripcion son sttring simples, solo necesitan validacion
            String productName = Utils.validateString(name);
            String productDescription = Utils.validateString(description);   
            Integer productQty = Utils.validateNumber(qty);
            Integer productPrice = Utils.validateNumber(price);

            // Se crea el typo de pructo en el dominio ya que requiere de reglas de negocio especificas
            ProductType productType = new ProductType(productPrice);           

            // Se utiliza el modelo de producto creado en models con el constructor para mas facilidad y que se vea mas limpio
            // no se utilizan setters de momento
            Product product = new Product(productId, productName, productDescription, productQty, productPrice, productType);
   
            productRepository.save(product);

            return "Se guardo!";
    }

    

}

// ./gradlew bootRun
