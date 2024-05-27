package org.example.comandos;

import org.example.entidades.Compra;
import org.example.entidades.Producto;
import org.example.lectorData.CompraData;
import org.example.lectorData.ProductoData;

import java.util.List;


public class VerCompras implements Comando {


    List<Compra> compras = CompraData.leerTodasCompras();

    @Override
    public void ejecutar() {
        System.out.println("Lista de comandas.csv:");
        compras.stream().forEach(producto -> System.out.println(producto.toString()));
    }
}
