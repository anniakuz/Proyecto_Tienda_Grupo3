package lectorArchivos;

import entidades.Producto;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;

public class ProductoData {
    private static final String FILE_PATH = "archivos/productos.csv";
    private static Path path = Paths.get(FILE_PATH);

    /**
     * Leer el documento y añadirlo a la lista
     * cosas a tener en cuenta: comas
     * @return la lista de los productos con su info
     */
    public static List<Producto> leerTodosProductos() {
        String linea;  // Variable para almacenar cada línea leída del archivo
        String[] lines;  // Arreglo para almacenar los valores de cada línea divididos por coma

        List<Producto> productos = new ArrayList<>();  // Lista para almacenar los productos leídos
        try {
            BufferedReader lector = new BufferedReader(new FileReader(FILE_PATH));  // Crear un BufferedReader para leer el archivo
            lector.readLine();  // Leer y descartar la primera línea (cabecera)
            while ((linea = lector.readLine()) != null) {
                lines = linea.split(",");  // Dividir la línea en valores separados por coma
                String nombre = lines[0];
                Double price = Double.parseDouble(lines[1]);

                Producto producto = new Producto(nombre, price);  // Crear un objeto Producto con los valores leídos
                productos.add(producto);  // Agregar el producto a la lista
                // System.out.println(producto.getNombre()+ "------"+ producto.getPrecio());
            }
            lector.close();  // Cerrar el lector

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);  // Mostrar un mensaje de error si ocurre una excepción
        }

        return productos;  // Devolver la lista de productos
    }

    /**
     * Guardar un nuevo producto en el archivo CSV
     *
     * @param producto
     */
    public static void guardarProducto(Producto producto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            // Escribir el producto en el archivo en formato CSV
            writer.write(producto.getNombre() + "," + String.format(Locale.US, "%.2f", producto.getPrecio()));
            writer.newLine();  // Escribir una nueva línea
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el producto: " + e.getMessage());  // Mostrar un mensaje de error si ocurre una excepción
        }
    }

    /**
     * Actualizar un producto en el archivo CSV
     *
     * @param producto
     */
    public static void actualizarProducto(Producto producto) {
        List<Producto> productos = leerTodosProductos();  // Leer todos los productos del archivo

        // Buscar y actualizar el producto en la lista
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(producto.getNombre())) {
                p.setPrecio(producto.getPrecio());
                break;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            // Reescribir el archivo completo con la lista actualizada de productos
            writer.write("nombre,precio");  // Escribir la cabecera
            writer.newLine();
            for (Producto p : productos) {
                writer.write(p.getNombre() + "," + String.format(Locale.US, "%.2f", p.getPrecio()));
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el producto: " + e.getMessage());  // Mostrar un mensaje de error si ocurre una excepción
        }
    }
}
