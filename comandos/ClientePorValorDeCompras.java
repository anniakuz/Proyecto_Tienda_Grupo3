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

        for(int i = 0; i < compras.size();i++){

            for (int j = 0; j < productos.size(); j++) {
                if (productos.get(j).getNombre().equalsIgnoreCase(compras.get(i).getNombreProducto())) {
                    double total = 0.0;
                    total += compras.get(i).getCantidad() * productos.get(j).getPrecio();

                    if(!gananciasPorCliente.containsKey(compras.get(i).getNombreClinete())){
                        gananciasPorCliente.put(compras.get(i).getNombreClinete(), total);
                    }else if(gananciasPorCliente.containsKey(compras.get(i).getNombreClinete())){
                        double nuevaTotal = 0.0;
                        nuevaTotal = total +gananciasPorCliente.get(compras.get(i).getNombreClinete());
                        gananciasPorCliente.put(compras.get(i).getNombreClinete(), nuevaTotal);
                    }
                }
            }
        }

        Double totalMax = Collections.max(gananciasPorCliente.entrySet(),
                (entry1, entry2) -> Math.toIntExact((long) (entry1.getValue() - entry2.getValue()))).getValue();

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
