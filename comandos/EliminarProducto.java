package comandos;

import entidades.Producto;
import lectorArchivos.ProductoData;

import java.util.List;
import java.util.Scanner;

public class EliminarProducto implements Comando{
    Scanner scanner = new Scanner(System.in);
    List<Producto> productos = ProductoData.leerTodosProductos();
    @Override
    public void ejecutar() {
        System.out.print("Nombre del producto a eliminar: ");
        String nombreEliminar = scanner.nextLine();

        Producto productoAEliminar = null;
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombreEliminar)) {
                productoAEliminar = producto;
                break;
            }
        }
        if (productoAEliminar != null) {
            productos.remove(productoAEliminar);
            ProductoData.eliminarProducto(productoAEliminar);
            System.out.println("Producto eliminado exitosamente.");
        } else {
            System.out.println("No hay producto con este nombre");
        }
    }
}
