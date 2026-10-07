package es.daw.jakartasimpson.service;

import es.daw.jakartasimpson.model.Personaje;
import es.daw.jakartasimpson.repository.PersonajeRepository;

import java.util.Comparator;
import java.util.List;

// El servicio sí se conecta al repositorio!!!
public class PersonajeService {
    // En spring no usaremos new. Inyectaremos el respository con @Autowired
    private final PersonajeRepository personajeRepository = new PersonajeRepository();

    public List<Personaje> buscar(String lugar,
                                  Integer edadMax,
                                  String ordenarPor, // pendiente
                                  boolean descendente, // pendiente
                                  Integer limite) {
        // ------------ PRUEBAS COMPARADORES TRADICIONALES ----------------
//        PersonajeTradicional personajeTradicional = new PersonajeTradicional();
//        System.out.println("*** personajeTradicional:"+personajeTradicional); // si no tiene toString sale un churro
//
//        List<PersonajeTradicional> personajeTradicionals = new ArrayList<>();
//        personajeTradicionals.add(new PersonajeTradicional("Homer",    "Simpson",    39, "Inspector de seguridad", "Central Nuclear",   true));
//        personajeTradicionals.add(new PersonajeTradicional("Marge",    "Simpson",    36, "Ama de casa",            "Casa Simpson",      true));
//        personajeTradicionals.add(new PersonajeTradicional("Bart",     "Simpson",    10, "Estudiante",             "Escuela Primaria",  true));
//
//        System.out.println("*** personajeTradicionals SIN ORDENAR:"+personajeTradicionals);
//
//        // dos formas de ordenar por el criterio natural de los objetos de la colección
//        Collections.sort(personajeTradicionals);
//        //personajeTradicionals.sort(Comparator.naturalOrder());
//
//        System.out.println("*** personajeTradicionals CON ORDENACIÓN NATURAL:"+personajeTradicionals);
//
//        personajeTradicionals.sort(new ComparadorPorEdad());
//
//        System.out.println("*** personajeTradicionals CON ORDENACIÓN POR EDAD:"+personajeTradicionals);
//
//
//
        // -------------------------------------------------------------
        return personajeRepository.findAll().stream()
                // filter() deja pasar solo los que cumplen la condición
                // Si el usuario no eligió luar, la condición es true para todos
                .filter( p -> lugar == null || lugar.isBlank() || p.lugar().equalsIgnoreCase(lugar))
                .filter(p -> edadMax == null || p.edad() <= edadMax)
                //.sorted((p1, p2) -> p1.nombre().compareTo(p2.nombre())) // estamos ordenando solo por nombre ascendente
                .sorted(crearComparator(ordenarPor,descendente))
                .limit(limite == null? Integer.MAX_VALUE : limite)
                .toList();

    }

    public List<String> lugaresDisponibles() {
        return personajeRepository.findAll().stream()
                //.map(p -> p.lugar())
                .map(Personaje::lugar)
                .distinct()
                .sorted()
                .toList();

//        List<String> lugares = new ArrayList<>();
//        List<Personaje> personajes = personajeRepository.findAll();
//
//        for (Personaje p : personajes) {
//            if (!lugares.contains(p.lugar())) {
//                lugares.add(p.lugar());
//            }
//        }
//
//        return lugares;
    }


    private Comparator<Personaje> crearComparator(String ordenarPor,boolean descendente) {

        Comparator<Personaje> comparador = switch( ordenarPor == null? "" :ordenarPor){

            case "edad" -> Comparator.comparingInt( Personaje::edad).thenComparing( Personaje::nombre );
            case "apellido" -> Comparator.comparing(Personaje::apellido).thenComparing( Personaje::nombre );
            default -> Comparator.comparing(Personaje::nombre);

        };

        return descendente? comparador.reversed(): comparador;

    }

}
