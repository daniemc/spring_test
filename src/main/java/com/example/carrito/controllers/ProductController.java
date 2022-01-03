package com.example.carrito.controllers;

import java.util.List;
import java.util.Optional;

import com.example.carrito.domain.DomainId;
import com.example.carrito.domain.Utils;
import com.example.carrito.domain.product.ProductType;
import com.example.carrito.models.Product;
import com.example.carrito.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public @ResponseBody String addNewProduct(@RequestBody Product productForm) {
        
            DomainId productId = new DomainId();

            //Dado que el nombre y la descripcion son sttring simples, solo necesitan validacion
            String productName = Utils.validateString(productForm.getName());
            String productDescription = Utils.validateString(productForm.getDescription());   
            Integer productQty = Utils.validateNumber(productForm.getQty());
            Integer productPrice = Utils.validateNumber(productForm.getPrice());

            // Se crea el typo de pructo en el dominio ya que requiere de reglas de negocio especificas
            ProductType productType = new ProductType(productPrice);           

            // Se utiliza el modelo de producto creado en models con el constructor para mas facilidad y que se vea mas limpio
            // no se utilizan setters de momento
            Product product = new Product(productId, productName, productDescription, productQty, productPrice, productType);
   
            productRepository.save(product);

            // TODO: retornar objeto creado y estatus de la peticion
            return "Se guardo!";
    }

    // obtener todos los productos
    @GetMapping
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    // obtener un producto por id
    @GetMapping("/{id}")
    // el PathVariable es el que me toma el valor id que esta llegando en la URL
    // ejempllo: http://localhost:8080/products/beba1aa0-1472-4a1e-b7ce-05700042
    public ResponseEntity<Product> getProduct(@PathVariable(value = "id") String id) {

        // se utiliza el tipo opcional porque la busqueda puede o no retornar un valor
        Optional<Product> product = productRepository.findById(id);
        
        //Aqui se valida si la busqueda retornno o no un valor
        if (product.isPresent()) {
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") String id, 
        @RequestParam String name, 
        @RequestParam String description, 
        @RequestParam Integer price, 
        @RequestParam Integer qty) {
        // vamos a buscar el producto en la base de datos
        Optional<Product> actualProduct = productRepository.findById(id);

        // si el producto existe 
        if (actualProduct.isPresent()) {
            // leeemos el producto que vamos a actualizar
            Product productoActualizar = actualProduct.get();

            // Validamos los datos como en el POSt
            String productName = Utils.validateString(name);
            String productDescription = Utils.validateString(description);   
            Integer productQty = Utils.validateNumber(qty);
            Integer productPrice = Utils.validateNumber(price);
            ProductType productType = new ProductType(productPrice);  

            //llenamos los valores igual que en el POST (en la creacion del producto) pero con los setters
            productoActualizar.setName(productName);
            productoActualizar.setDescription(productDescription);
            productoActualizar.setPrice(price);
            productoActualizar.setQty(productQty);
            productoActualizar.setType(productType);


            // actualizamos los valores en la base de datos y los retornamos
            return new ResponseEntity<>(productRepository.save(productoActualizar), HttpStatus.OK);
            
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable(value = "id") String id) {

        // vamos a buscar el producto en la base de datos
        Optional<Product> productoAEliminar = productRepository.findById(id);
        
        if (productoAEliminar.isPresent()) {
            //aqui eliminio el producto de la base de datos por el repositorio
            productRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    

}

// ./gradlew bootRun
