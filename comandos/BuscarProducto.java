package comandos;

import entidades.Producto;
import lectorArchivos.ProductoData;


import java.util.List;
import java.util.Scanner;

public class BuscarProducto implements Comando{

    Scanner scanner = new Scanner(System.in);
    List<Producto> productos;

    @Override
    public void ejecutar() {
        reloadProductos();
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
    public void reloadProductos(){
        productos = ProductoData.leerTodosProductos();
    }

}
