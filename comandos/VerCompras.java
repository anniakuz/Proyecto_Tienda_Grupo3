package comandos;


import entidades.Compra;
import lectorArchivos.CompraData;

import java.util.List;


public class VerCompras implements Comando {


    List<Compra> compras = CompraData.leerTodasCompras();

    @Override
    public void ejecutar() {
        System.out.println("Lista de comandas.csv:");
        compras.stream().forEach(producto -> System.out.println(producto.toString()));
    }
}
