package lectorArchivos;

import entidades.Compra;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CompraData {
    // Ruta del archivo CSV donde se almacenan las compras
    private static final String FILE_PATH = "archivos/compras.csv";
    private static Path path = Paths.get(FILE_PATH);

    // Método para leer todas las compras desde el archivo CSV
    public static List<Compra> leerTodasCompras() {
        // Lista para almacenar las compras leídas
        List<Compra> compras = new ArrayList<>();

        String linea;  // Variable para almacenar cada línea leída del archivo
        String[] lines;  // Arreglo para almacenar los valores de cada línea divididos por coma

        try {
            // Crear un BufferedReader para leer el archivo
            BufferedReader lector = new BufferedReader(new FileReader(FILE_PATH));
            lector.readLine(); // Leer y descartar la primera línea (cabezera)
            
            // Leer cada línea del archivo
            while ((linea = lector.readLine()) != null) {
                // Dividir la línea en valores separados por coma
                lines = linea.split(",");
                String nombreCliente = lines[0];
                String nombreProducto = lines[1];
                int cantidad = Integer.parseInt(lines[2]);
                
                // Crear un objeto Compra con los valores leídos
                Compra compra = new Compra(nombreCliente, nombreProducto, cantidad);
                // Agregar la compra a la lista
                compras.add(compra);
            }
            lector.close(); // Cerrar el lector
        } catch (Exception e) {
            // Mostrar un mensaje de error si ocurre una excepción
            JOptionPane.showConfirmDialog(null, e);
        }

        // Devolver la lista de compras
        return compras;
    }

    // Método para guardar una compra en el archivo CSV
    public static void guardarCompra(Compra compra) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            // Escribir la compra en el archivo en formato CSV
            writer.write(compra.getNombreClinete() + "," + compra.getNombreProducto() + "," + compra.getCantidad());
            writer.newLine(); // Escribir una nueva línea
        } catch (IOException e) {
            // Mostrar un mensaje de error si ocurre una excepción
            JOptionPane.showMessageDialog(null, "Error al guardar la compra: " + e.getMessage());
        }
    }
}
