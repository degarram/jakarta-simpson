package es.daw.jakartasimpson.util;

public class Utils {

    public static Integer leerEntero(String nombreCampo, String valor) throws Exception {
        if (valor == null || valor.isBlank()) {
            return null;
        }

        Integer num;
        try {
            num = Integer.valueOf(valor);

        } catch (NumberFormatException e) {
            throw new Exception("El campo " + nombreCampo + " debe ser un número entero");
        }

        if (num < 0)
            throw new Exception("El campo " + nombreCampo + " debe ser positivo");

        return num;
    }
}
