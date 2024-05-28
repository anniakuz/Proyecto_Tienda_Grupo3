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

        String linea;
        String[] lines;

        List<Producto> productos = new ArrayList<>();
        try {
            BufferedReader lector = new BufferedReader(new FileReader(FILE_PATH));
            lector.readLine();
            while ((linea = lector.readLine()) != null) {
                lines = linea.split(",");
                String nombre = lines[0];
                Double price = Double.parseDouble(lines[1]);

                Producto producto = new Producto(nombre, price);
                productos.add(producto);
                // System.out.println(producto.getNombre()+ "------"+ producto.getPrecio());
            }
            lector.close();

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }

        return productos;
    }

    /**
     * Guardar un nuevo producto en el archivo CSV
     *
     * @param producto
     */
    public static void guardarProducto(Producto producto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(producto.getNombre() + "," + String.format(Locale.US, "%.2f", producto.getPrecio()));
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el producto: " + e.getMessage());
        }
    }

    /**
     * Eliminar un producto del archivo CSV
     *
     * @param producto
     */
    public static void eliminarProducto(Producto producto) {
        List<Producto> productos = leerTodosProductos();
        productos.removeIf(p -> p.getNombre().equalsIgnoreCase(producto.getNombre()));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write("nombre,precio");
            writer.newLine();
            for (Producto p : productos) {
                writer.write(p.getNombre() + "," + String.format(Locale.US, "%.2f", p.getPrecio()));
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el producto: " + e.getMessage());
        }
    }

    /**
     * Actualizar un producto en el archivo CSV
     *
     * @param producto
     */
    public static void actualizarProducto(Producto producto) {
        List<Producto> productos = leerTodosProductos();

        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(producto.getNombre())) {
                p.setPrecio(producto.getPrecio());
                break;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write("nombre,precio");
            writer.newLine();
            for (Producto p : productos) {
                writer.write(p.getNombre() + "," + String.format(Locale.US, "%.2f", p.getPrecio()));
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el producto: " + e.getMessage());
        }
    }
}
