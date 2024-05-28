package comandos;


import entidades.Compra;
import entidades.Producto;
import lectorArchivos.CompraData;
import lectorArchivos.ProductoData;

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

            System.out.println("entidades.Producto: " + entry.getKey() + ": Ganancia Total: " + entry.getValue());
        }
    }
}
