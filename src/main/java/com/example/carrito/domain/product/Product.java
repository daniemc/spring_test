package com.example.carrito.domain.product;

import java.math.BigDecimal;

public class Product {
    // Attibutes
    // - Id
    // - Nombre
    // - Descripcion
    // - Precio
    // - Cantidad
    // - Denominacion

    private final String id;
    private final String name;
    private final String desc;
    private final BigDecimal price;
    private final Integer qty;
    private final String type;

    public Product(String id, String name, String desc, BigDecimal price, Integer qty, String type) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.qty = qty;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQty() {
        return qty;
    }

    public String getType() {
        return type;
    }

}
