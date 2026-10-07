package es.daw.jakartasimpson.model;

import java.util.Objects;

public class PersonajeTradicional implements Comparable<PersonajeTradicional>{

    // 1. atributos
    private String nombre;
    private String apellido;
    private int edad;
    private String ocupacion;
    private String lugar;
    private boolean principal;

    // 2. constructores

    // si no creo constructor, tengo el constructor vacío por defecto...
    public PersonajeTradicional(String nombre, String apellido, int edad, String ocupacion, String lugar, boolean principal) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.ocupacion = ocupacion;
        this.lugar = lugar;
        this.principal = principal;
    }

    public PersonajeTradicional() {}

    //---------------------
    //3. getters & setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    // --------------------------
    // 4. Métodos de comportamiento
    public String nombreCompleto(){
        return apellido.isBlank()? nombre: nombre + " " + apellido;
    }

    public boolean esMenor(){
        return edad < 18;
    }

    //---------------------------------
    // 5. Sobreescritura de métodos de la clase padre o implementación de métodos de interfaces
    @Override
    public int compareTo(PersonajeTradicional o) {
        // -1 o negativo (asc)
        // 1 o positivo (desc)
        // 0 son iguales
        return this.nombreCompleto().compareTo(o.nombreCompleto());

        //return this.getNombre().compareTo(o.getNombre());

    }

    // --------------------

    // 6. Sobrescritura de métodos de Object


    @Override
    public String toString() {
        return "PersonajeTradicional{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", ocupacion='" + ocupacion + '\'' +
                ", lugar='" + lugar + '\'' +
                ", principal=" + principal +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PersonajeTradicional that = (PersonajeTradicional) o;
        return edad == that.edad && principal == that.principal && Objects.equals(nombre, that.nombre) && Objects.equals(apellido, that.apellido) && Objects.equals(ocupacion, that.ocupacion) && Objects.equals(lugar, that.lugar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellido, edad, ocupacion, lugar, principal);
    }
}
