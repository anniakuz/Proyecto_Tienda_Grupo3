package comandos;

public class Salir implements Comando{

    @Override
    public void ejecutar() {
        System.out.println("Saliendo...");
    }
}
