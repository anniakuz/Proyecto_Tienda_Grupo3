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
            System.out.println("7. Agregar un producto");
            System.out.println("8. Eliminar un producto");
            System.out.println("9. Actualizar el precio de un producto");
            System.out.println("10. Salir");

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
                    for (Compra compra : compras) {
                        if (compra.getNombreClinete().equalsIgnoreCase(nombreCl)) {
                            compraPorNombre.add(compra);
                        }
                    }
                    if (!compraPorNombre.isEmpty()) {
                        System.out.println("Cliente " + nombreCl + " tiene estas compras:");
                        compraPorNombre.stream().forEach(compra -> System.out.println(compra.getNombreProducto() + "   " + compra.getCantidad()));
                        System.out.println("-----------------------------------------");
                    } else {
                        System.out.println("No hay cliente con este nombre");
                    }
                    break;
                case 3:
                    productos = ProductoData.leerTodosProductos();
                    System.out.println("Lista de productos.csv:");
                    productos.stream().forEach(producto -> System.out.println(producto.toString()));
                    System.out.println("-----------------------------------------");
                    break;
                case 4:
                    System.out.print("Ingresa el nombre del producto a buscar: ");
                    String nombreProducto = scanner.nextLine();
                    boolean encontrado = false;
                    for (Producto producto : productos) {
                        if (producto.getNombre().equalsIgnoreCase(nombreProducto)) {
                            encontrado = true;
                            System.out.println(producto.toString());
                        }
                    }
                    if (!encontrado) {
                        System.out.println("No hay producto con este nombre");
                    }
                    System.out.println("-----------------------------------------");
                    break;
                case 5:
                    System.out.println("Ganancias totales: ");
                    double totalidad = 0.0;
                    for (Compra compra : compras) {
                        for (Producto producto : productos) {
                            if (producto.getNombre().equals(compra.getNombreProducto())) {
                                totalidad += compra.getCantidad() * producto.getPrecio();
                            }
                        }
                    }
                    System.out.println("Las ganancias totales son :" + totalidad);
                    System.out.println("-----------------------------------------");
                    break;
                case 6:
                    System.out.println("Introduce el nombre del producto:");
                    String nombrePr = scanner.nextLine();

                    double totalidadProducto = 0.0;
                    boolean productoEncontrado = false;
                    for (Producto producto : productos) {
                        if (producto.getNombre().equalsIgnoreCase(nombrePr)) {
                            productoEncontrado = true;
                            for (Compra compra : compras) {
                                if (compra.getNombreProducto().equalsIgnoreCase(nombrePr)) {
                                    totalidadProducto += producto.getPrecio() * compra.getCantidad();
                                }
                            }
                            System.out.println("Ganancias por producto " + nombrePr + " es " + totalidadProducto);
                            System.out.println("-----------------------------------------");
                            break;
                        }
                    }
                    if (!productoEncontrado) {
                        System.out.println("No hay producto con este nombre");
                    }
                    break;
                case 7:
                    System.out.println("Agregar un nuevo producto:");
                    System.out.print("Nombre del producto: ");
                    String nuevoNombre = scanner.nextLine();

                    double nuevoPrecio = 0;
                    boolean precioValido = false;

                    while (!precioValido) {
                        System.out.print("Precio del producto: ");
                        if (scanner.hasNextDouble()) {
                            nuevoPrecio = scanner.nextDouble();
                            scanner.nextLine();  // clear the buffer
                            precioValido = true;
                        } else {
                            System.out.println("Entrada inválida. Por favor, ingresa un número válido para el precio.");
                            scanner.next();  // clear the invalid input
                        }
                    }

                    Producto nuevoProducto = new Producto(nuevoNombre, nuevoPrecio);
                    productos.add(nuevoProducto);
                    ProductoData.guardarProducto(nuevoProducto);
                    System.out.println("Producto agregado exitosamente.");
                    System.out.println("-----------------------------------------");
                    break;
                case 8:
                    System.out.println("Eliminar un producto:");
                    System.out.print("Nombre del producto a eliminar: ");
                    String nombreEliminar = scanner.nextLine();

                    Producto productoAEliminar = null;
                    for (Producto producto : productos) {
                        if (producto.getNombre().equalsIgnoreCase(nombreEliminar)) {
                            productoAEliminar = producto;
                            break;
                        }
                    }
                    if (productoAEliminar != null) {
                        productos.remove(productoAEliminar);
                        ProductoData.eliminarProducto(productoAEliminar);
                        System.out.println("Producto eliminado exitosamente.");
                    } else {
                        System.out.println("No hay producto con este nombre");
                    }
                    System.out.println("-----------------------------------------");
                    break;
                case 9:
                    System.out.println("Actualizar el precio de un producto:");
                    System.out.print("Nombre del producto a actualizar: ");
                    String nombreActualizar = scanner.nextLine();

                    Producto productoAActualizar = null;
                    for (Producto producto : productos) {
                        if (producto.getNombre().equalsIgnoreCase(nombreActualizar)) {
                            productoAActualizar = producto;
                            break;
                        }
                    }
                    if (productoAActualizar != null) {
                        double nuevoPrecioActualizar = 0;
                        boolean precioActualizarValido = false;

                        while (!precioActualizarValido) {
                            System.out.print("Nuevo precio del producto: ");
                            if (scanner.hasNextDouble()) {
                                nuevoPrecioActualizar = scanner.nextDouble();
                                scanner.nextLine();  // clear the buffer
                                precioActualizarValido = true;
                            } else {
                                System.out.println("Entrada inválida. Por favor, ingresa un número válido para el precio.");
                                scanner.next();  // clear the invalid input
                            }
                        }

                        productoAActualizar.setPrecio(nuevoPrecioActualizar);
                        ProductoData.actualizarProducto(productoAActualizar);
                        System.out.println("Producto actualizado exitosamente.");
                    } else {
                        System.out.println("No hay producto con este nombre");
                    }
                    System.out.println("-----------------------------------------");
                    break;
                case 10:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
