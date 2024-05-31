package comandos;

import entidades.Producto;
import lectorArchivos.ProductoData;

import java.util.List;

public class VerProductos implements Comando {
    List<Producto> productos;  // Lista para almacenar todos los productos leídos

    // Método que se ejecuta para mostrar la lista de productos
    public void ejecutar() {
        // Recargar la lista de productos desde el archivo
        reloadProductos();
        // Mostrar un mensaje indicando que se va a listar los productos
        System.out.println("Lista de productos.csv:");
        // Recorrer la lista de productos y mostrarlos en la consola
        productos.stream().forEach(producto -> System.out.println(producto.toString()));
    }

    // Método para recargar la lista de productos desde el archivo
    public void reloadProductos() {
        productos = ProductoData.leerTodosProductos();
    }
}
