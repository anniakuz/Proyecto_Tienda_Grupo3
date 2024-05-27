package org.example.comandos;

import org.example.entidades.Compra;
import org.example.entidades.Producto;
import org.example.lectorData.CompraData;
import org.example.lectorData.ProductoData;

import java.util.List;

public class CalcularGananciasTotales implements Comando{
    List<Compra> compras = CompraData.leerTodasCompras();
    List<Producto> productos = ProductoData.leerTodosProductos();

    @Override
    public void ejecutar() {
        double totalidad = 0.0;
        for (Compra compra : compras) {
            for (Producto producto : productos) {
                if (producto.getNombre().equals(compra.getNombreProducto())) {
                    totalidad += compra.getCantidad() * producto.getPrecio();
                }
            }
        }
        System.out.println("Las ganancias totales son :" + totalidad);
    }
}
