/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.videoclub.servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.videoclub.domain.Cinta;
import org.videoclub.domain.Pelicula;
import org.videoclub.service.PeliculaServiceLocal;

@WebServlet(name = "ControllerCinta",
        loadOnStartup = 1,
        urlPatterns = {"/ListCintas",
            "/AddCinta",
            "/DeleteCinta",
            "/UpdateCinta"})
public class ControllerCinta extends HttpServlet {


    @EJB
    private PeliculaServiceLocal peliculaService;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();

        // Si la operacion es listar categorias
        switch (userPath) {
            case "/ListCintas":
                listCintas(request, response);
                // Si la operacion es Alta Pelicula
                break;
            case "/AddCinta":
                addCinta(request, response);
                // Si la operacion es Eliminar Pelicula
                break;
            case "/DeleteCinta":
                deleteCinta(request, response);
                // Si la operacion es Modificar Pelicula
                break;
            case "/UpdateCinta":
                updateCinta(request, response);
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

    private void listCintas(HttpServletRequest request, HttpServletResponse response) {
        // 1. Recuperamos el id
        String idString = request.getParameter("id");

        //2. Recuperamos la Pelicula                       
        Pelicula pelicula = new Pelicula();
        pelicula.setId(Integer.valueOf(idString));

        try {
            pelicula = this.peliculaService.findPeliculaById(pelicula);

            // Asignamos al request el atributo lista
            
            //*****IMPORTATE:QUITAR
            //ArrayList<Cinta> listaArray = new ArrayList<>(pelicula.getCintas());
            //request.getSession().setAttribute("cintas", listaArray);
            
            request.getSession().setAttribute("pelicula", pelicula);
            // Pasamos al RequestDispatcher la pagina a cargar
            RequestDispatcher rd = request.getRequestDispatcher("/listCintas.jsp");
            // Cargamos la pagina
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void listCintasByPelicula(HttpServletRequest request, HttpServletResponse response) {
        try {
            Pelicula pelicula = (Pelicula) request.getSession().getAttribute("pelicula");

            // Asignamos al request el atributo lista
            
            //*****IMPORTATE:QUITAR
            //ArrayList<Cinta> listaArray = new ArrayList<>(pelicula.getCintas());
            //request.getSession().setAttribute("cintas", listaArray);
            
            request.getSession().setAttribute("pelicula", pelicula);
            // Pasamos al RequestDispatcher la pagina a cargar
            RequestDispatcher rd = request.getRequestDispatcher("/listCintas.jsp");
            // Cargamos la pagina
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    
    private void addCinta(HttpServletRequest request, HttpServletResponse response) {
        //1. Recuperamos los parametros
        String numeroSerie = request.getParameter("numeroSerie");
        String estado = request.getParameter("estado");
        Pelicula pelicula = (Pelicula) request.getSession().getAttribute("pelicula");

        //2. Creamos el objeto
        
        Cinta cinta = new Cinta(numeroSerie, Integer.valueOf(estado));
        
        //*****IMPORTATE:QUITAR
        //cinta.setPelicula(pelicula);

        try {
            //*****IMPORTATE:QUITAR
            //cintaService.addCinta(cinta);
            //pelicula.getCintas().add(cinta);
            
            peliculaService.updatePelicula(pelicula);
        } catch (Exception e) {
            //Informamos cualquier error
            e.printStackTrace();
        }

        listCintasByPelicula(request, response);
    }

    private void deleteCinta(HttpServletRequest request, HttpServletResponse response) {
        //1. Recuperamos los parametros
        String idString = request.getParameter("id");
        Pelicula pelicula = (Pelicula) request.getSession().getAttribute("pelicula");

        //2. Creamos el objeto
        int id = Integer.parseInt(idString);
        Cinta cinta = new Cinta();
        cinta.setId(id);

        try {
            // Quitamos primero la cinta de las peliculas
            
            //*****IMPORTATE:QUITAR
            //pelicula.getCintas().remove(cinta);
            //this.cintaService.deleteCinta(cinta);
            
            this.peliculaService.updatePelicula(pelicula);
        } catch (Exception e) {
            //Informamos cualquier error
            e.printStackTrace();
        }

        listCintasByPelicula(request, response);
    }

    private void updateCinta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        
        // A IMPLEMENTAR POR EL ALUMNO
    }
}
