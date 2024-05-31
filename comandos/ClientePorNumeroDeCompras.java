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
        Map<String, Double> gananciasPorCliente = new HashMap<>();
        //recorremos la lista de las compras
        for(int i = 0; i < compras.size();i++){
                //recorremos la lista de productos
            for (int j = 0; j < productos.size(); j++) {
                if (productos.get(j).getNombre().equalsIgnoreCase(compras.get(i).getNombreProducto())) {
                    //si encontramos el producto cogemos su precio en Producto y su cantidad en Compra y calculamos la totalidad de esta compra
                    double total = 0.0;
                    total += compras.get(i).getCantidad() * productos.get(j).getPrecio();

                    if(!gananciasPorCliente.containsKey(compras.get(i).getNombreClinete())){
                        //si no encontramos al cliente en el map, significa que es su primera compra, entonces lo guardamos tal cual
                        gananciasPorCliente.put(compras.get(i).getNombreClinete(), total);
                    }else if(gananciasPorCliente.containsKey(compras.get(i).getNombreClinete())){
                        double nuevaTotal = 0.0;
                        //si encontramos al cliente, actualizamos la totalidad 
                        nuevaTotal = total +gananciasPorCliente.get(compras.get(i).getNombreClinete());
                        gananciasPorCliente.put(compras.get(i).getNombreClinete(), nuevaTotal);
                    }
                }
            }
        }
        //encontamos el value mas grande en el map

        Double totalMax = Collections.max(gananciasPorCliente.entrySet(),
                (entry1, entry2) -> Math.toIntExact((long) (entry1.getValue() - entry2.getValue()))).getValue();

        
        //tenemos en cuenta que pueden haber más de un cliente con la misma totalidad de las compras
        //por eso, recorremos el map para comprobar si alguien más tiene la misma totalidad e imprimimos 
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
