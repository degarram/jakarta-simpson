package es.daw.jakartasimpson.util;

public class Utils {

    public static Integer leerEntero(String nombreCampo, String valor) throws Exception {
        if (valor == null || valor.isBlank()) { //FIXME Si el cuadro del html no tiene ningun valor, da error.
            // return null;
            throw new Exception("El campo " + nombreCampo + " no puede ser nulo o ni estar vacío");
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
