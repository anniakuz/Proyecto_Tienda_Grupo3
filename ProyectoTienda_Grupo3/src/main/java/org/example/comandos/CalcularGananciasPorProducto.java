package org.example.comandos;

import org.example.entidades.Compra;
import org.example.entidades.Producto;
import org.example.lectorData.CompraData;
import org.example.lectorData.ProductoData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CalcularGananciasPorProducto implements Comando {
    Scanner scanner;
    List<Compra> compras = CompraData.leerTodasCompras();
    List<Producto> productos = ProductoData.leerTodosProductos();

    @Override
    public void ejecutar() {
        System.out.println("Introduce el nombre del producto:");
        String nombrePr = scanner.nextLine();

        double totalidadProducto = 0.0;
        boolean productoEncontrado = false;
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombrePr)) {
                productoEncontrado = true;
                for (Compra compra : compras) {
                    if (compra.getNombreProducto().equalsIgnoreCase(nombrePr)) {
                        totalidadProducto += producto.getPrecio() * compra.getCantidad();
                    }
                }
                System.out.println("Ganancias por producto " + nombrePr + " es " + totalidadProducto);
            }
        }
    }
}
