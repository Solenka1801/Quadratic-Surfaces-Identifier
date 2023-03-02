package proyectocalculo.app;

import proyectocalculo.exception.EcuacionException;
import proyectocalculo.model.Ecuacion;

public class App {

    static Ecuacion ecuacion = new Ecuacion("na");

    public static String crearEcuacion(String ec) throws EcuacionException {

        Ecuacion nuevaecuacion = new Ecuacion(ec);
        return ecuacion.crearEcuacion(nuevaecuacion);

    }

}
