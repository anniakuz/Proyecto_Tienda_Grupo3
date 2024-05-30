package comandos;


import entidades.Compra;
import lectorArchivos.CompraData;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuscarCompraPorCliente implements Comando{
    List<Compra> compras = CompraData.leerTodasCompras();
     Scanner scanner = new Scanner(System.in);

    public void ejecutar(){
        System.out.print("Ingresa el nombre a buscar en compras.csv: ");
        String nombreCl = scanner.nextLine();

        List<Compra> compraPorNombre = new ArrayList<>();
        for (Compra compra : compras) {
            if (compra.getNombreClinete().equalsIgnoreCase(nombreCl)) {
                compraPorNombre.add(compra);
            }
        }
        if (!compraPorNombre.isEmpty()) {
            System.out.println("\n" + "Cliente " + compraPorNombre.get(0).getNombreClinete() + " tiene estas compras:");
            compraPorNombre.stream().forEach(compra -> System.out
                    .println(compra.getNombreProducto() + "   " + compra.getCantidad()));
            System.out.println("-----------------------------------------");
        } else {
            System.out.println("No hay cliente con este nombre");
        }
    }

}
