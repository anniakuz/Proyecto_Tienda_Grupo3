package comandos;

import entidades.Compra;
import lectorArchivos.CompraData;

import java.util.List;

public class VerCompras implements Comando {

    List<Compra> compras;

    @Override
    public void ejecutar() {
        reloadCompras();
        System.out.println("Lista de comandas.csv:" + "\n");
        compras.stream().forEach(producto -> System.out.println(producto.toString()));
        /*un stream en Java permite realizar operaciones sobre colecciones de manera funcional y declarativa, facilitando la manipulación de datos de una forma más concisa y legible.*/
    }

    public void reloadCompras() {
        compras = CompraData.leerTodasCompras();
    }
}
