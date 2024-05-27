package org.example.lectorData;

import org.example.entidades.Compra;

import javax.swing.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CompraData {

    private static CompraData compraDataInstance;
    private static final String FILE_PATH = "archivos/compras.csv";
    private static Path path = Paths.get(FILE_PATH);

    /*
    public static CompraData getInstance(){
        if(compraDataInstance == null){
            compraDataInstance = new CompraData();
        }
        return compraDataInstance;
    } */


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
                Compra compra = new Compra(nombreCliente,nombreProducto,cantidad, new Date());
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
