package es.daw.jakartasimpson.service;

import es.daw.jakartasimpson.model.Personaje;
import es.daw.jakartasimpson.repository.PersonajeRepository;

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
                .filter(personaje -> personaje.edad() <= edadMax)
                .limit(limite)
                .toList();
    }

    public List<String> lugaresDisponibles() {
        return personajeRepository.findAll().stream()
                .map(personaje -> personaje.lugar())
                .distinct()
                .sorted()
                .toList();
    }
}
