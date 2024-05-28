package comandos;



import entidades.Producto;
import lectorArchivos.ProductoData;

import java.util.List;

public class VerProductos implements Comando{
    List<Producto> productos = ProductoData.leerTodosProductos();

    public void ejecutar(){
        System.out.println("Lista de productos.csv:");
        productos.stream().forEach(producto -> System.out.println(producto.toString()));
    }

}
