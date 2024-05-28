package comandos;

import java.util.HashMap;
import java.util.Map;

public class CambioDeComanda {
    private Map<String, Comando> comandoMap;

    {
        comandoMap = new HashMap<>();
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

