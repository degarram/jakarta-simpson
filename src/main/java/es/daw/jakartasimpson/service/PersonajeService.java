package es.daw.jakartasimpson.service;

import es.daw.jakartasimpson.model.Personaje;
import es.daw.jakartasimpson.repository.PersonajeRepository;

import java.util.Comparator;
import java.util.List;

public class PersonajeService {
    // En spring no usaremos new. Inyectaremos el repository con  @Autowired
    private final PersonajeRepository personajeRepository = new PersonajeRepository();

    public List<Personaje> buscar(String lugar,
                                  Integer edadMax,
                                  String ordenarPor,
                                  boolean descendente,
                                  Integer limite) {
        return personajeRepository.findAll().stream()
                .filter(personaje -> lugar == null || lugar.isBlank() || personaje.lugar().equalsIgnoreCase(lugar))
                .filter(personaje -> edadMax == null || personaje.edad() <= edadMax)
                .sorted(crearComparator(ordenarPor, descendente))
                .limit(limite == null ? Integer.MAX_VALUE : limite)
                .toList();
    }

    public List<String> lugaresDisponibles() {
        return personajeRepository.findAll().stream()
                .map(Personaje::lugar)
                .distinct()
                .sorted()
                .toList();
    }

    private Comparator<Personaje> crearComparator(String ordenarPor, Boolean descendente) {
        Comparator<Personaje> comparador = switch (ordenarPor == null ? "" : ordenarPor) {
            case "edad" -> Comparator.comparingInt(Personaje::edad).thenComparing(Personaje::nombreCompleto);
            case "apellido" -> Comparator.comparing(Personaje::apellido).thenComparing(Personaje::nombreCompleto);
            default -> Comparator.comparing(Personaje::nombreCompleto).thenComparing(Personaje::apellido);
        };

        return descendente ? comparador.reversed() :comparador;
    }
}
