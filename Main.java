import comandos.*;

import java.util.Scanner;


public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        CambioDeComanda comanda = new CambioDeComanda();
        comanda.registrarComanda("1", new VerCompras());
        comanda.registrarComanda("2", new BuscarCompraPorCliente());
        comanda.registrarComanda("3", new VerProductos());
        comanda.registrarComanda("4", new BuscarProducto());
        comanda.registrarComanda("5", new CalcularGananciasTotales());
        comanda.registrarComanda("6", new CalcularGananciasPorProducto());
        comanda.registrarComanda("7", new CalcularGananciasPorCadaProducto());
        comanda.registrarComanda("8", new AgregarProducto());
        comanda.registrarComanda("9", new AgregarCompra());

        comanda.registrarComanda("11", new ActualizarPrecioProducto());
        comanda.registrarComanda("12", new ClientePorNumeroDeCompras());
        comanda.registrarComanda("0", new Salir());


        String opcion = null;
        do {
            System.out.println("Selecciona una opción:");
            System.out.println("1. Ver todo el contenido de compras.csv");
            System.out.println("2. Buscar por el nombre en compras.csv");
            System.out.println("3. Ver todo el contenido de productos.csv");
            System.out.println("4. Buscar por el nombre en productos.csv");
            System.out.println("5. Ganancias totales");
            System.out.println("6. Ganancias totales de un producto");
            System.out.println("7. Ganancias totales de cada producto");
            System.out.println("8. Agregar un producto");
            System.out.println("9. Agregar una compra");
            System.out.println("10. Eliminar un producto");
            System.out.println("11. Actualizar el precio de un producto");
            System.out.println("12. Cliente más activo por numero de compras");
            System.out.println("13. Cliente más activo por valor de compras");
            System.out.println("0. Salir");

            opcion = scanner.next();
            scanner.nextLine();
            comanda.ejecutar(opcion);


        } while (Integer.parseInt(opcion) != 0);

    }
}
