package org.example.comandos;

import org.example.entidades.Producto;
import org.example.lectorData.ProductoData;

import java.util.List;
import java.util.Scanner;

public class BuscarProducto implements Comando{

    Scanner scanner;
    List<Producto> productos = ProductoData.leerTodosProductos();

    @Override
    public void ejecutar() {
        System.out.print("Ingresa el nombre del producto a buscar: ");
        String nombreProducto = scanner.nextLine();
        boolean encontrado = false;
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombreProducto)) {
                encontrado = true;
                System.out.println(producto.toString());
            }
        }
        if (!encontrado) {
            System.out.println("No hay producto con este nombre");
        }
    }

}
