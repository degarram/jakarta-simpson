package es.daw.jakartasimpson.controller;

import es.daw.jakartasimpson.model.Personaje;
import es.daw.jakartasimpson.service.PersonajeService;
import es.daw.jakartasimpson.util.Utils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/personajes")
public class PersonajeServlet extends HttpServlet {

    // No vamos a usar repositorios directamente del servlet, vamos a usar servicios
    private final PersonajeService personajeService = new PersonajeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 1. Leer los parámetros del request
        String lugar = request.getParameter("lugar");
        String edadMax = request.getParameter("edadMax");
        boolean descendente = request.getParameter("descendente") != null; //  si no está marcado no se envía
        String ordenarPor = request.getParameter("ordenarPor");
        String limite = request.getParameter("limite"); // Integer

        // 2. Convertir y validar los datos de los parámetros
        List<Personaje> personajes = new ArrayList<>();

        try {
            Integer edadMaxInt = Utils.leerEntero("edadMax", edadMax);
            Integer limiteInt = Utils.leerEntero("limite", limite);

            // 3. Lógica de negocio que hará un servicio. Obtener la lista de los personajes (con o sin filtro, con o sin ordenación)
            personajes = personajeService.buscar(lugar, edadMaxInt, ordenarPor, descendente, limiteInt);
        } catch (Exception e) {
            // Enviar mensaje de error a personajes.jsp
            request.setAttribute("error", e.getMessage());
        }

        // 4. Enviar a la vista la información perinente
        request.setAttribute("personajes", personajes);
        request.setAttribute("lugares", personajeService.lugaresDisponibles());

        // 5. Reenviar a la vista (JSP)
        request.getRequestDispatcher("/personajes.jsp").forward(request, response);
    }

    public void destroy() {
    }
}