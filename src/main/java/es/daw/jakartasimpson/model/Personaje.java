package es.daw.jakartasimpson.model;

/**
 * Record (Java 16+)
 * Pensada solo para guardar datos
 * Los getters no llevan getX() y tiene equals, hashcode, toString
 *
 * Son INMUTABLES: una vez los
 */
public record Personaje(
        String nombre,
        String apellido,
        int edad,
        String ocupacion,
        String lugar,
        boolean principal
) {
    public String nombreCompleto() {
        return apellido.isBlank() ? nombre : nombre + " " + apellido;
    }
    public boolean esMenor() {
        return edad < 18;
    }
}
