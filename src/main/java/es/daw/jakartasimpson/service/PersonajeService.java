package es.daw.jakartasimpson.service;

import es.daw.jakartasimpson.model.Personaje;
import es.daw.jakartasimpson.repository.PersonajeRepository;

import java.util.List;

public class PersonajeService {
    // En spring no usaremos new. Inyectaremos el repository con  @Autowired
    private final PersonajeRepository personajeRepository = new PersonajeRepository();

    public List<Personaje> buscar() {
        return personajeRepository.findAll();
    }
}
