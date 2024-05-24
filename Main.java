import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Producto> productos = ProductoData.leerTodosProductos();
        List<Compra> compras = CompraData.leerTodasCompras();

        while (true) {
            System.out.println("Selecciona una opción:");
            System.out.println("1. Ver todo el contenido de compras.csv");
            System.out.println("2. Buscar por el nombre en compras.csv");
            System.out.println("3. Ver todo el contenido de productos.csv");
            System.out.println("4. Buscar por el nombre en productos.csv");
            System.out.println("5. Ganancias totales");
            System.out.println("6. Ganancias totales por producto");
            System.out.println("7. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Contenido de compras.csv:");
                    compras.stream().forEach(compra -> System.out.println(compra.toString()));
                    System.out.println("-----------------------------------------");
                    break;
                case 2:
                    System.out.print("Ingresa el nombre a buscar en compras.csv: ");
                    String nombreCl = scanner.nextLine();

                    List<Compra> compraPorNombre = new ArrayList<>();
                    for(int i = 0; i < compras.size();i++){
                        if(compras.get(i).getNombreClinete().toLowerCase().equals(nombreCl.toLowerCase())){
                            compraPorNombre.add(compras.get(i));
                        }
                    }
                    if(!compraPorNombre.isEmpty()){
                        System.out.println("Cliente " + nombreCl + " tiene estas compras:");
                        compraPorNombre.stream().forEach(compra -> System.out.println(compra.getNombreProducto() +"   " + compra.getCantidad()));
                        System.out.println("-----------------------------------------");
                    }else{
                        System.out.println("No hay cliente con este nombre");
                        
                    }
                    break;
                case 3:
                    System.out.println("Lista de productos.csv:");
                    productos.stream().forEach(producto -> System.out.println(producto.toString()));
                    System.out.println("-----------------------------------------");
                    break;
                case 4:
                    System.out.print("Ingresa el nombre del producto a buscar ");
                    String nombreProducto = scanner.nextLine();
                    boolean encontrado = false;
                    for(int i = 0; i < ProductoData.leerTodosProductos().size();i++){
                        if(productos.get(i).getNombre().toLowerCase().equals(nombreProducto.toLowerCase())){
                            encontrado = true;
                            System.out.println(productos.get(i).toString());
                        }
                    }
                    if(encontrado == false){
                        System.out.println("No hay producto con este nombre");
                    }
                    System.out.println("-----------------------------------------");
                    break;
                case 5:
                    System.out.println("Ganancias totales: " );
                    double totalidad = 0.0;
                    for(int i = 0; i < compras.size();i++){
                        String productoNombre = compras.get(i).getNombreProducto();
                        for(int j = 0; j < productos.size();j++){
                            if(productos.get(j).getNombre().equals(productoNombre)){

                                totalidad += compras.get(i).getCantidad() * productos.get(j).getPrecio();
                            }
                        }
                    }
                    System.out.println("Las ganancias totales son :" +totalidad);
                    System.out.println("-----------------------------------------");
                    break;
                case 6:
                    System.out.println("Introduce le nombre del producto");
                    String nombrePr = scanner.nextLine();

                    double totalidadProducto = 0.0;
                    for(int i = 0; i< productos.size();i++){
                        if(productos.get(i).getNombre().toLowerCase().equals(nombrePr.toLowerCase())){
                            for(int j = 0; j <compras.size();j++){
                                if(compras.get(j).getNombreProducto().toLowerCase().equals(nombrePr.toLowerCase())){
                                    totalidadProducto += productos.get(i).getPrecio()*compras.get(j).getCantidad();
                                }
                            }
                            System.out.println("Ganancias por producto " + nombrePr + " es " + totalidadProducto);
                            System.out.println("-----------------------------------------");
                            break;
                        }else{
                            System.out.println("No hay producto con este nombre");
                            break;
                        }
                    }

                    break;
                case 7:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }

    } 
}
