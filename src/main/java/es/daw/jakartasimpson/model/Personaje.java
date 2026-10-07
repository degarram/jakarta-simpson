package es.daw.jakartasimpson.model;

/**
 * record (Java 16+)
 * Pensada para solo guardar datos
 * Los getters no llevan getX() y tiene equals, hashcode, toString...
 *
 * Son INMUTABLES:  una vez creado el personaje, no se puede cambiar.
 */
public record Personaje(
    String nombre,
    String apellido,
    int edad,
    String ocupacion,
    String lugar,
    boolean principal
) {

    // puede haber métodos...
    public String nombreCompleto(){
        return apellido.isBlank()? nombre: nombre + " " + apellido;
    }

    public boolean esMenor(){
        return edad < 18;

        // noooooooooooooo kk
//        if (edad < 18){
//            return true;
//        }else {
//            return false;
//        }

    }
}
