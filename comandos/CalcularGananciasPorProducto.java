package comandos;



import entidades.Compra;
import entidades.Producto;
import lectorArchivos.CompraData;
import lectorArchivos.ProductoData;

import java.util.List;
import java.util.Scanner;

public class CalcularGananciasPorProducto implements Comando {
    Scanner scanner = new Scanner(System.in);
    List<Compra> compras;
    List<Producto> productos;

    @Override
    public void ejecutar() {
        reloadProductos();
        reloadCompras();
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

    public void reloadProductos(){
        productos = ProductoData.leerTodosProductos();
    }
    public void reloadCompras(){
        compras = CompraData.leerTodasCompras();
    }
}
