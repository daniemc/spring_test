package com.example.carrito.domain.product;

public class ProductType {
    private final String value;

    public ProductType(Integer price) {        
        if (price < 50) {
            this.value = "Barato";
        }else if (price >= 50 && price < 199) {
            this.value = "Normal";
        } else {
            // no se pone validacion extra porque se obvia y el valor ya viene validado en que sea mayor que 0
            this.value = "Caro";
        }
    }

    @Override
    public String toString() {
        return value.toString();
    }  

}
