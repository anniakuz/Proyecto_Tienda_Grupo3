package org.example.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Producto {
    private String nombre;
    private double precio;


    @Override
    public String toString() {
        return nombre + "   " + precio;
    }
}
