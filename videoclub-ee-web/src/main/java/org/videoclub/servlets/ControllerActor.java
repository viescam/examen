/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.videoclub.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.videoclub.domain.Actor;
import org.videoclub.domain.Direccion;
import org.videoclub.service.ActorServiceLocal;

@WebServlet(name = "ControllerActor",
        loadOnStartup = 1,
        urlPatterns = {"/ListActores",
            "/AddActor",
            "/DeleteActor",
            "/UpdateActor"})
public class ControllerActor extends HttpServlet {

    @EJB
    private ActorServiceLocal actorService;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();

        // Si la operacion es listar actores
        switch (userPath) {
            case "/ListActores":
                listActores(request, response);
                // Si la operacion es Alta Actor
                break;
            case "/AddActor":
                addActor(request, response);
                // Si la operacion es Eliminar Actor
                break;
            case "/DeleteActor":
                deleteActor(request, response);
                // Si la operacion es Modificar Actor
                break;
            case "/UpdateActor":
                updateActor(request, response);
                break;
            default:
                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listActores(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Ejecutamos el metodo y obtenemos la lista
            List lista = actorService.listActores();
            // Asignamos al request el atributo lista
            ArrayList<Actor> listaArray = new ArrayList<>(lista);
            request.getSession().setAttribute("actores", listaArray);
            // Pasamos al RequestDispatcher la pagina a cargar
            RequestDispatcher rd = request.getRequestDispatcher("/listActores.jsp");
            // Cargamos la pagina
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addActor(HttpServletRequest request, HttpServletResponse response) {
        //1. Recuperamos los parametros de la categoria
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String sexo = request.getParameter("sexo");
        String documento = request.getParameter("documento");

        String direccion = request.getParameter("direccion");
        String poblacion = request.getParameter("poblacion");
        String codigoPostal = request.getParameter("codigoPostal");
        String provincia = request.getParameter("provincia");

        //2. Creamos los objetos y las relaciones
        Actor actor = new Actor(nombre, apellidos, sexo, documento);
        Direccion direccionActor = new Direccion(direccion, poblacion, codigoPostal, provincia);

        //*****IMPORTATE:QUITAR
        //actor.setDireccion(direccionActor);

        try {
            actorService.addActor(actor);
        } catch (Exception e) {
            //Informamos cualquier error
            e.printStackTrace();
        }

        listActores(request, response);
    }

    private void deleteActor(HttpServletRequest request, HttpServletResponse response) {
        //1. Recuperamos los parametros
        String idString = request.getParameter("id");

        //2. Creamos el objeto
        int id = Integer.parseInt(idString);
        Actor actor = new Actor();
        actor.setId(id);

        try {
            this.actorService.deleteActor(actor);
        } catch (Exception e) {
            //Informamos cualquier error
            e.printStackTrace();
        }

        listActores(request, response);
    }

    private void updateActor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion != null && accion.equals("editar")) {

            //1. Recuperamos el id 
            String idString = request.getParameter("id");
            if (idString != null) {
                //2. Creamos el objeto a recuperar
                int id = Integer.valueOf(idString);
                Actor actor = new Actor();
                actor.setId(id);
                try {
                    actor = this.actorService.findActorById(actor);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //3. Compartimos el objeto en alcance request
                request.setAttribute("actor", actor);

                //4. Redireccionamos a la pagina para editar el objeto
                request.getRequestDispatcher("/updateActor.jsp").forward(request, response);
            }
        } else if (accion != null && accion.equals("modificar")) {

            //1. Recuperamos los parametros
            String idString = request.getParameter("id");
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            String sexo = request.getParameter("sexo");
            String documento = request.getParameter("documento");

            String direccion = request.getParameter("direccion");
            String poblacion = request.getParameter("poblacion");
            String codigoPostal = request.getParameter("codigoPostal");
            String provincia = request.getParameter("provincia");

            //2. Creamos el objeto
            Actor actor = new Actor();
            int id = Integer.valueOf(idString);

            // 3. Localizamos de nuevo el actor para no perder los datos de las listas
            actor.setId(id);

            try {
                actor = this.actorService.findActorById(actor);

                // 4. Modificamos los datos
                actor.setNombre(nombre);
                actor.setApellidos(apellidos);
                actor.setSexo(sexo);
                actor.setDocumento(documento);

                //*****IMPORTATE:QUITAR
                /*
                actor.getDireccion().setDireccion(direccion);
                actor.getDireccion().setPoblacion(poblacion);
                actor.getDireccion().setCodigoPostal(codigoPostal);
                actor.getDireccion().setProvincia(provincia);
                */

                this.actorService.updateActor(actor);
            } catch (Exception e) {
                //Informamos cualquier error
                e.printStackTrace();
            }

            listActores(request, response);
        }
    }

}
