package comandos;

import entidades.Producto;
import lectorArchivos.ProductoData;

import java.util.List;
import java.util.Scanner;

public class AgregarProducto implements Comando {
    Scanner scanner = new Scanner(System.in); // Inicializa el scanner para leer la entrada del usuario
    List<Producto> productos; // Lista para almacenar los productos

    @Override
    public void ejecutar() {
        reloadProductos(); // Recarga los productos desde el almacenamiento

        System.out.print("Nombre del producto: ");
        String nuevoNombre = scanner.nextLine(); // Lee el nombre del nuevo producto
        boolean exists = false;

        // Comprueba si el producto ya existe en la lista
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nuevoNombre)) {
                System.out.println("El producto con este nombre ya está en la lista");
                exists = true;
                break; // Termina el bucle si encuentra un producto existente
            }
        }

        // Si el producto no existe, procede a agregarlo
        if (!exists) {
            double nuevoPrecio = 0;
            boolean precioValido = false;

            // Valida que el precio ingresado sea un número válido
            while (!precioValido) {
                System.out.print("Precio del producto: ");
                if (scanner.hasNextDouble()) {
                    nuevoPrecio = scanner.nextDouble();
                    scanner.nextLine(); // Limpia el buffer del scanner
                    precioValido = true; // Marca el precio como válido
                } else {
                    System.out.println("Entrada inválida. Por favor, ingresa un número válido para el precio.");
                    scanner.next(); // Limpia la entrada inválida
                }
            }

            // Crea un nuevo producto con el nombre y precio ingresados
            Producto nuevoProducto = new Producto(nuevoNombre, nuevoPrecio);
            productos.add(nuevoProducto); // Agrega el nuevo producto a la lista
            ProductoData.guardarProducto(nuevoProducto); // Guarda el nuevo producto en el almacenamiento
            System.out.println("Producto agregado exitosamente."); // Confirma al usuario que el producto fue agregado
        }
    }

    // Método para recargar la lista de productos desde el almacenamiento
    public void reloadProductos() {
        productos = ProductoData.leerTodosProductos();
    }
}
