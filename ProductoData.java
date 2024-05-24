import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ProductoData {
    private static final String FILE_PATH = "archivos/productos.csv";
    private static Path path = Paths.get(FILE_PATH);

    /**
     * leer el documento y añadirlo a la lista
     * cosas a tener en cuenta : comas
     * @return la lista de los productos con su info
     */
    public static List<Producto> leerTodosProductos() {

            String linea;
            String [] lines;

            List<Producto> productos = new ArrayList<>();
            try{

                BufferedReader lector = new BufferedReader(new FileReader(FILE_PATH));
                lector.readLine();
                while ((linea = lector.readLine()) !=null){
                    lines = linea.split(",");
                    String nombre = lines[0];
                    Double price = Double.parseDouble(lines[1]);

                    Producto producto = new Producto(nombre, price);
                    productos.add(producto);
                    //System.out.println(producto.getNombre()+ "------"+ producto.getPrecio());
                }
                lector.close();

            }catch (Exception e){
                JOptionPane.showConfirmDialog(null,e);
            }

            return productos;
    }
    
}
