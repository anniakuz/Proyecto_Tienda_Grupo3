package comandos;

import entidades.Producto;
import lectorArchivos.ProductoData;

import java.util.List;
import java.util.Scanner;

public class BuscarProducto implements Comando {

    Scanner scanner = new Scanner(System.in);  // Scanner para leer la entrada del usuario
    List<Producto> productos;  // Lista para almacenar todos los productos leídos

    @Override
    public void ejecutar() {
        // Recargar la lista de productos desde el archivo
        reloadProductos();
        // Solicitar al usuario que ingrese el nombre del producto a buscar
        System.out.print("Ingresa el nombre del producto a buscar: ");
        String nombreProducto = scanner.nextLine();
        boolean encontrado = false;  // Bandera para indicar si se encontró el producto
        
        // Recorrer la lista de productos y buscar coincidencias con el nombre del producto
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombreProducto)) {
                encontrado = true;
                // Mostrar el producto encontrado
                System.out.println(producto.toString());
            }
        }
        // Si no se encontró el producto, mostrar un mensaje indicando que no hay producto con ese nombre
        if (!encontrado) {
            System.out.println("No hay producto con este nombre");
        }
    }

    // Método para recargar la lista de productos desde el archivo
    public void reloadProductos() {
        productos = ProductoData.leerTodosProductos();
    }
}
