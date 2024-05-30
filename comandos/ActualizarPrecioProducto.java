package comandos;

import entidades.Producto;
import lectorArchivos.ProductoData;

import java.util.List;
import java.util.Scanner;

public class ActualizarPrecioProducto implements Comando{

    Scanner scanner = new Scanner(System.in);
    List<Producto> productos;

    @Override
    public void ejecutar() {

        reloadProductos();
     
        System.out.print("Nombre del producto a actualizar: ");
        String nombreActualizar = scanner.nextLine();
        boolean exist = false;

        Producto productoAActualizar = null;
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombreActualizar)) {
                exist = true;
                productoAActualizar = producto;
                double nuevoPrecioActualizar = 0.0;
                boolean precioActualizarValido = false;

                while (!precioActualizarValido) {
                    System.out.print("Nuevo precio del producto: ");
                    if (scanner.hasNextDouble()) {
                        nuevoPrecioActualizar = scanner.nextDouble();
                        scanner.nextLine(); // clear the buffer
                        precioActualizarValido = true;
                    } else {
                        System.out.println(
                                "Entrada inválida. Por favor, ingresa un número válido para el precio.");
                        scanner.next(); // clear the invalid input
                    }
                }

                productoAActualizar.setPrecio(nuevoPrecioActualizar);
                ProductoData.actualizarProducto(productoAActualizar);
                System.out.println("Producto actualizado exitosamente.");
            }
        }

        if(!exist){
            System.out.println("No existe el producto con este nombre");
        }
        /*
        if (productoAActualizar != null) {
            double nuevoPrecioActualizar = 0.0;
            boolean precioActualizarValido = false;

            while (!precioActualizarValido) {
                System.out.print("Nuevo precio del producto: ");
                if (scanner.hasNextDouble()) {
                    nuevoPrecioActualizar = scanner.nextDouble();
                    scanner.nextLine(); // clear the buffer
                    precioActualizarValido = true;
                } else {
                    System.out.println(
                            "Entrada inválida. Por favor, ingresa un número válido para el precio.");
                    scanner.next(); // clear the invalid input
                }
            }

            productoAActualizar.setPrecio(nuevoPrecioActualizar);
            ProductoData.actualizarProducto(productoAActualizar);
            System.out.println("Producto actualizado exitosamente.");
        } else {
            System.out.println("No hay producto con este nombre");
        }*/
    }
    public void reloadProductos(){
        productos = ProductoData.leerTodosProductos();
    }
}
