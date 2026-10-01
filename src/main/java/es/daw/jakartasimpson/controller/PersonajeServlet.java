package es.daw.jakartasimpson.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import es.daw.jakartasimpson.model.Personaje;
import es.daw.jakartasimpson.service.PersonajeService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(value = "/personajes")
public class PersonajeServlet extends HttpServlet {

    // No vamos a usar repositorios directamente del servlet, vamos a usar servicios
    private final PersonajeService  personajeService =  new PersonajeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 1. Leer los parámetros del request
        // TODO
        String lugar = request.getParameter("lugar");
        String edadMax = request.getParameter("edadMax");
        // Integer edadMin = Integer.valueOf(request.getParameter("edadMin"));
        // Continuará
        boolean descendente = request.getParameter("descendente") != null; //  si no está marcado no se envía
        // 2. Validar los datos de los parámetros

        // 3. Lógica de negocio que hará un servicio. Obtener la lista de los personajes (con o sin filtro, con o sin ordenación)
        List<Personaje> personajes = personajeService.buscar();
        // 4. Enviar a la vista la información perinente
        request.setAttribute("personajes", personajes);
        // 5. Reenviar a la vista (JSP)
        request.getRequestDispatcher("/personajes.jsp").forward(request, response);
    }

    public void destroy() {
    }
}