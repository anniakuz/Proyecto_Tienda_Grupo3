package comandos;

import entidades.Compra;
import entidades.Producto;
import lectorArchivos.CompraData;
import lectorArchivos.ProductoData;

import java.util.*;

public class ClientePorValorDeCompras implements Comando{
    List<Compra> compras;
    List<Producto> productos;
    @Override
    public void ejecutar() {
        reloadProductos();
        reloadCompras();
        Map<String, Double> gananciasPorCliente = new HashMap<>();


        //por cada compra miramos el nombre de producto y su precio para añadirlo al map con el String con el nombre de cliente
        // y el valor de sus compras que se va actualizando si ha hecho más de una compra.
        for(int i = 0; i < compras.size();i++){

            for (int j = 0; j < productos.size(); j++) {
                if (productos.get(j).getNombre().equalsIgnoreCase(compras.get(i).getNombreProducto())) {
                    double total = 0.0;
                    // sumamos el total de esta compra
                    total += compras.get(i).getCantidad() * productos.get(j).getPrecio();

                    //si el map no tiene el nombre del cliente, significa que estamos leyendo su primera compra
                    //por eso, añadimos el nombre y el total tal cual
                    if(!gananciasPorCliente.containsKey(compras.get(i).getNombreClinete())){
                        gananciasPorCliente.put(compras.get(i).getNombreClinete(), total);

                        //si encontramos el nombre de cliente en el map, significa que no estamos leyendo su primera compra
                        //y tenemos que sumar la totalidad obtenido con la totalidad que ya tiene
                    }else if(gananciasPorCliente.containsKey(compras.get(i).getNombreClinete())){
                        double nuevaTotal = 0.0;
                        nuevaTotal = total +gananciasPorCliente.get(compras.get(i).getNombreClinete());
                        gananciasPorCliente.put(compras.get(i).getNombreClinete(), nuevaTotal);
                    }
                }
            }
        }

        //Después de acabar el for loop y obtener el map comleto con los clientes y sus valores de compras,
        //Buscamos el cliente con el valor de compra máximo

        Double totalMax = Collections.max(gananciasPorCliente.entrySet(),
                (entry1, entry2) -> Math.toIntExact((long) (entry1.getValue() - entry2.getValue()))).getValue();

        //Tenemos en cuenta que más de un cliente puede tener el mismo valor de compra
        //Por eso, miramos con el for loop si hay éste es el caso e imprimimos los nombres de estos clientes
        System.out.println("Clientes mas activos tienen " + totalMax + "  euros total en compras");
        for (Map.Entry<String, Double> entry : gananciasPorCliente.entrySet()) {
            if (Objects.equals(entry.getValue(), totalMax)) {
                System.out.println(entry.getKey()+ "    "+ entry.getValue());
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
