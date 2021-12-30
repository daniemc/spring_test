package com.example.carrito.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.carrito.domain.DomainId;
import com.example.carrito.domain.product.ProductType;

@Entity
@Table(name = "product")
public class Product implements Serializable{
    @Id

    private String id;
    private String name;
    private String description;
    private Integer price;
    private Integer qty;
    private String type;

    // Toda entidad necesita un constructor vacio por defecto
    public Product() {

    }

    public Product(DomainId id, String name, String description, Integer qty, Integer price, ProductType productType) {
        // como no pude econtrar forma de mapear correctamente el tipo DomainID a String en este modelo
        // decidi utilizar el metodo toString del objeto para traer el valor y que el modelo lo aceptara
        // correctamente
        this.id = id.toString();
        this.name = name;
        this.description = description;
        this.qty = qty;
        this.price = price;
        this.type = productType.toString();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public void setType(ProductType type) {
        this.type = type.toString();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getQty() {
        return qty;
    }

    public String getType() {
        return type;
    }

    // se crea el toString del modelo para retornar el producto cuando se solicita
    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", description=" + description + ", qty=" + qty + ", price=" + price + ", type=" + type + "]";
    }
}
 