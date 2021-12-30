package com.example.carrito.domain;

import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

// utilidades generales para el proyecto
// tales como validacion de datos y generacion de datos random
public class Utils {   

    // funcion generica para generar ids
    // de momento solo utilizada en DomainId 
    public static UUID generarId() {
        return UUID.randomUUID();
    }

    // este metodo se va a encargar de validar todos los String
    public static String validateString(String value) {
        Objects.requireNonNull(value, "Este valor no puede ser nulo");
        String trimmedValue = value.trim();
        if (trimmedValue.length() == 0) {
            throw new IllegalArgumentException("este campo no puede estar vacio");
        }
        // Pattern pattern = Pattern.compile("^[a-zA-Z\\s:]{10,64}$");
        // boolean isValid = pattern.matcher(trimmedValue).matches();
        // if(!isValid) {
        //     throw new IllegalArgumentException("Este campo solo puede contener valores alfanumericos");
        // }

        return trimmedValue;
    }


    public static Integer validateNumber(Integer number) {
        Objects.requireNonNull(number, "Este valor no puede ser nulo");
        if (number < 0) {
            throw new IllegalArgumentException("Este valor no puede ser inferios a 0");
        }
        return number;
    }

    
}
