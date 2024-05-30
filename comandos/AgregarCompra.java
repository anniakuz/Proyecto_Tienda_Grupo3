package comandos;



import entidades.Compra;
import entidades.Producto;
import lectorArchivos.CompraData;
import lectorArchivos.ProductoData;

import java.util.List;
import java.util.Scanner;

public class AgregarCompra implements Comando{
    Scanner scanner = new Scanner(System.in);
    List<Producto> productos;
    List<Compra> compras;

    @Override
    public void ejecutar() {
        reloadProductos();
        reloadCompras();
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

                Compra nuevacompra = new Compra(nombreCliente, nameProducto, cantidadProducto);
                compras.add(nuevacompra);
                CompraData.guardarCompra(nuevacompra);

                System.out.println("Se ha añadido la compra!");
                System.out.println(
                        nombreCliente + " | " + nameProducto + " | " + cantidadProducto + " | " +"\n");
            }
        }
        if (!findProducto) {
            System.out.println("\n" + "No existe este producto!" + "\n");
        }
    }
    public void reloadProductos(){
        productos = ProductoData.leerTodosProductos();
    }
    public void reloadCompras(){
        compras = CompraData.leerTodasCompras();
    }
}
