package es.daw.jakartasimpson.util;

public class Utils {

    public static int leerEntero(String nombreCampo, String valor){
        if (valor == null || valor.isBlank()){
            return 0;
        }

        // PENDIENTE!!!
        int numero = Integer.parseInt(valor.strip());
    }
}
