package comandos;

import java.util.HashMap;
import java.util.Map;

public class CambioDeComanda {
    private Map<String, Comando> comandoMap;

    {
        comandoMap = new HashMap<>();
        /*Map es un tipo de colección con la estructura de clave/valor
        guardamos los comandos con el string de un numero y Comando*/

        /*command pattern hace que cada petición sea una clase que contiene toda la información y la implementación necesaria
        permite implementar el sistema de comandos de manera más fácil y organizada*/
    }

    public void registrarComanda(String comandoNombre, Comando comando) {
        comandoMap.put(comandoNombre, comando);
    }

    public void ejecutar(String comandoNombre) {
        if (comandoMap.get(comandoNombre) == null) {
            throw new IllegalArgumentException("La comanda no esta registrada");
        }
        try {
            comandoMap.get(comandoNombre).ejecutar();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

