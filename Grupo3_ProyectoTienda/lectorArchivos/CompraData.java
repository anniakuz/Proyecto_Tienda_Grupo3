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
    private static final String FILE_PATH = "archivos/compras.csv";
    private static Path path = Paths.get(FILE_PATH);


    public static List<Compra> leerTodasCompras(){

        List<Compra> compras = new ArrayList<>();

        String linea;
        String [] lines;

        try{

            BufferedReader lector = new BufferedReader(new FileReader(FILE_PATH));
            lector.readLine();
            while ((linea = lector.readLine()) !=null){
                lines = linea.split(",");
                String nombreCliente = lines[0];
                String nombreProducto = lines[1];
                int cantidad = Integer.parseInt(lines[2]);
                Compra compra = new Compra(nombreCliente,nombreProducto,cantidad);
                compras.add(compra);

            }
            lector.close();

        }catch (Exception e){
            JOptionPane.showConfirmDialog(null,e);
        }

        return compras;

    }
    
    public static void guardarCompra(Compra compra) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(compra.getNombreClinete() + "," + compra.getNombreProducto() + "," +  compra.getCantidad());
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar la compra: " + e.getMessage());
        }
    }
}
