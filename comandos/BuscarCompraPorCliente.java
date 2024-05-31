package comandos;

import entidades.Compra;
import lectorArchivos.CompraData;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuscarCompraPorCliente implements Comando {
    List<Compra> compras;  // Lista para almacenar todas las compras leídas
    Scanner scanner = new Scanner(System.in);  // Scanner para leer la entrada del usuario

    // Método que se ejecuta para buscar compras por cliente
    public void ejecutar() {
        // Recargar la lista de compras desde el archivo
        reloadCompras();
        // Solicitar al usuario que ingrese el nombre del cliente a buscar
        System.out.print("Ingresa el nombre a buscar en compras.csv: ");
        String nombreCl = scanner.nextLine();

        // Lista para almacenar las compras encontradas para el cliente especificado
        List<Compra> compraPorNombre = new ArrayList<>();
        // Recorrer la lista de compras y buscar coincidencias con el nombre del cliente
        for (Compra compra : compras) {
            if (compra.getNombreClinete().equalsIgnoreCase(nombreCl)) {
                compraPorNombre.add(compra);
            }
        }
        // Verificar si se encontraron compras para el cliente especificado
        if (!compraPorNombre.isEmpty()) {
            // Mostrar las compras del cliente
            System.out.println("\n" + "Cliente " + compraPorNombre.get(0).getNombreClinete() + " tiene estas compras:");
            compraPorNombre.stream().forEach(compra -> System.out
                    .println(compra.getNombreProducto() + "   " + compra.getCantidad()));
            System.out.println("-----------------------------------------");
        } else {
            // Mensaje si no se encontraron compras para el cliente
            System.out.println("No hay cliente con este nombre");
        }
    }

    // Método para recargar la lista de compras desde el archivo
    public void reloadCompras() {
        compras = CompraData.leerTodasCompras();
    }
}
