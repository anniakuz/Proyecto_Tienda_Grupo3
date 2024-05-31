package comandos;

import entidades.Compra;
import entidades.Producto;
import lectorArchivos.CompraData;
import lectorArchivos.ProductoData;

import java.util.*;


public class ClientePorNumeroDeCompras implements Comando{
    List<Compra> compras;
    List<Producto> productos;
    @Override
    public void ejecutar() {
        reloadProductos();
        reloadCompras();
        //Contamos la cantidad de veces que un cliente ha compra y lo guardamos en un map con el nombre de cliente como key
        // y la cantidad de compras como el Value
        Map<String, Long> cantidadCompras = compras.stream()
                .collect(Collectors.groupingBy(
                        Compra::getNombreClinete,
                        counting()
                ));

        //en el map buscamos el value maximo que representa la cantidad de compras
        Long numeroCompras = Collections.max(cantidadCompras.entrySet(),
                (entry1, entry2) -> Math.toIntExact(entry1.getValue() - entry2.getValue())).getValue();

        //tenemos en cuenta que mas de un cliente puede hacer la misma cantidad de compras,
        //por eso hacemos el for para recorrer el map y sacar los nombres de los clientes con el value maximo de compra
        System.out.println("Clientes mas activos tienen " + numeroCompras + " compras");
        for (Map.Entry<String, Long> entry : cantidadCompras.entrySet()) {
            if (entry.getValue() == numeroCompras) {
                System.out.println(entry.getKey());
            }
        }
    }
    public void reloadProductos(){
        productos = ProductoData.leerTodosProductos();
    }
    public void reloadCompras(){
        compras = CompraData.leerTodasCompras();
    }
}
