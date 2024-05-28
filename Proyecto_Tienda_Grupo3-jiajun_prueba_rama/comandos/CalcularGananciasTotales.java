package comandos;



import entidades.Compra;
import entidades.Producto;
import lectorArchivos.CompraData;
import lectorArchivos.ProductoData;

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
