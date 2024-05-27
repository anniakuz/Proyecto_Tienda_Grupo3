package org.example.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Compra {
    private String nombreClinete;
    private String nombreProducto;
    private int cantidad;
    private Date date = new Date();


    @Override
    public String toString() {
        return nombreClinete + "   " + nombreProducto + " " + cantidad;
    }


}
