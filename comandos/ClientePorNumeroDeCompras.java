package comandos;

import entidades.Compra;
import lectorArchivos.CompraData;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;

public class ClientePorNumeroDeCompras implements Comando{
    List<Compra> compras = CompraData.leerTodasCompras();
    @Override
    public void ejecutar() {
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
}
