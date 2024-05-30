package comandos;



import entidades.Compra;
import entidades.Producto;
import lectorArchivos.CompraData;
import lectorArchivos.ProductoData;

import java.util.List;

public class CalcularGananciasTotales implements Comando{
    List<Compra> compras;
    List<Producto> productos;

    @Override
    public void ejecutar() {
        reloadProductos();
        reloadCompras();
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
    public void reloadProductos(){
        productos = ProductoData.leerTodosProductos();
    }
    public void reloadCompras(){
        compras = CompraData.leerTodasCompras();
    }
}
