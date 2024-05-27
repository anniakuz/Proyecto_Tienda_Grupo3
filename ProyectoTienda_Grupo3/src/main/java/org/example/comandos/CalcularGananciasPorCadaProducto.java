package org.example.comandos;

import org.example.entidades.Compra;
import org.example.entidades.Producto;
import org.example.lectorData.CompraData;
import org.example.lectorData.ProductoData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalcularGananciasPorCadaProducto implements Comando{
    List<Compra> compras = CompraData.leerTodasCompras();
    List<Producto> productos = ProductoData.leerTodosProductos();

    @Override
    public void ejecutar() {
        System.out.println("Ganancias totales por cada producto:");
        Map<String, Double> gananciasPorProducto = new HashMap<>();

        for (Compra compra : compras) {
            for (Producto producto : productos) {
                if (producto.getNombre().equals(compra.getNombreProducto())) {
                    double ganancia = compra.getCantidad() * producto.getPrecio();
                    gananciasPorProducto.put(producto.getNombre(), gananciasPorProducto.getOrDefault(producto.getNombre(), 0.0) + ganancia);
                }
            }
        }

        for (Map.Entry<String, Double> entry : gananciasPorProducto.entrySet()) {

            System.out.println("Producto: " + entry.getKey() + ": Ganancia Total: " + entry.getValue());
        }
    }
}
