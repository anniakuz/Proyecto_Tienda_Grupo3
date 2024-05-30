package comandos;

import entidades.Producto;
import lectorArchivos.ProductoData;


import java.util.List;
import java.util.Scanner;

public class AgregarProducto implements Comando{
    Scanner scanner = new Scanner(System.in);
    List<Producto> productos;

    @Override
    public void ejecutar() {
        System.out.print("Nombre del producto: ");
        String nuevoNombre = scanner.nextLine();
        boolean exists = false;
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getNombre().equalsIgnoreCase(nuevoNombre)) {
                System.out.println("El producto con este nombre ya está en la lista");
                exists = true;
            }
        }

        if (!exists) {
            double nuevoPrecio = 0;
            boolean precioValido = false;

            while (!precioValido) {
                System.out.print("Precio del producto: ");
                if (scanner.hasNextDouble()) {
                    nuevoPrecio = scanner.nextDouble();
                    scanner.nextLine(); // clear the buffer
                    precioValido = true;
                } else {
                    System.out.println("Entrada inválida. Por favor, ingresa un número válido para el precio.");
                    scanner.next(); // clear the invalid input
                }
            }

            Producto nuevoProducto = new Producto(nuevoNombre, nuevoPrecio);
            productos.add(nuevoProducto);
            ProductoData.guardarProducto(nuevoProducto);
            System.out.println("Producto agregado exitosamente.");
        }
    }
    

    public void reloadProductos(){
        productos = ProductoData.leerTodosProductos();
    }
}

