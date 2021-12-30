package com.example.carrito.domain;

import java.io.Serializable;
import java.util.UUID;

// Class type generiuco creado poara manejar Ids
// para todas las entidades del dominio
public class DomainId implements Serializable{
    private final UUID value;

    public DomainId() {
        UUID value = Utils.generarId();
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }   
}
