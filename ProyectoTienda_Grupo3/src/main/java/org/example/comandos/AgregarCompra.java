package org.example.comandos;

import org.example.entidades.Compra;
import org.example.entidades.Producto;
import org.example.lectorData.CompraData;
import org.example.lectorData.ProductoData;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AgregarCompra implements Comando{
    Scanner scanner = new Scanner(System.in);
    List<Producto> productos = ProductoData.leerTodosProductos();
    List<Compra> compras = CompraData.leerTodasCompras();

    @Override
    public void ejecutar() {
        System.out.println("Nombre del cliente: ");
        String nombreCliente = scanner.nextLine();
        System.out.println("Nombre producto: ");
        String nameProducto = scanner.nextLine();
        boolean findProducto = false;

        for (Producto producto : productos) {
            if (nameProducto.equals(producto.getNombre())) {
                findProducto = true;

                System.out.println("Cantidad del producto: ");
                int cantidadProducto = scanner.nextInt();

                Compra nuevacompra = new Compra(nombreCliente, nameProducto, cantidadProducto, new Date());
                compras.add(nuevacompra);
                CompraData.guardarCompra(nuevacompra);

                System.out.println("Se ha añadido la compra!");
                System.out.println(
                        nombreCliente + " | " + nameProducto + " | " + cantidadProducto + " | " + " | "+ nuevacompra.getDate()+"\n");
            }
        }
        if (!findProducto) {
            System.out.println("\n" + "No existe este producto!" + "\n");
        }
    }
}
