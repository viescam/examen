/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.videoclub.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.videoclub.domain.Actor;
import org.videoclub.domain.Categoria;
import org.videoclub.domain.Pelicula;
import org.videoclub.service.ActorServiceLocal;
import org.videoclub.service.CategoriaServiceLocal;
import org.videoclub.service.PeliculaServiceLocal;

@WebServlet(name = "ControllerPelicula",
        loadOnStartup = 1,
        urlPatterns = {"/ListPeliculas",
            "/AddPelicula",
            "/DeletePelicula",
            "/UpdatePelicula",
            "/ManagerActorToPelicula",
            "/ListPeliculasByActor"})
public class ControllerPelicula extends HttpServlet {

    @EJB
    private ActorServiceLocal actorService;

    @EJB
    private CategoriaServiceLocal categoriaService;

    @EJB
    private PeliculaServiceLocal peliculaService;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();

        // Si la operacion es listar categorias
        switch (userPath) {
            case "/ListPeliculas":
                listPeliculas(request, response);
                // Si la operacion es Alta Pelicula
                break;
            case "/AddPelicula":
                addPelicula(request, response);
                // Si la operacion es Eliminar Pelicula
                break;
            case "/DeletePelicula":
                deletePelicula(request, response);
                // Si la operacion es Modificar Pelicula
                break;
            case "/UpdatePelicula":
                updatePelicula(request, response);
                break;
            case "/ManagerActorToPelicula":
                managerActorToPelicula(request, response);
                break;
            case "/ListPeliculasByActor":
                listPeliculasByActor(request, response);
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

    private void listPeliculas(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Ejecutamos el metodo y obtenemos la lista
            List lista = peliculaService.listPeliculas();
            // Asignamos al request el atributo lista
            ArrayList<Pelicula> listaArray = new ArrayList<>(lista);
            request.getSession().setAttribute("peliculas", listaArray);
            // Pasamos al RequestDispatcher la pagina a cargar
            RequestDispatcher rd = request.getRequestDispatcher("/listPeliculas.jsp");
            // Cargamos la pagina
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addPelicula(HttpServletRequest request, HttpServletResponse response) {
        String accion = request.getParameter("accion");

        if (accion != null && accion.equals("paso1")) {
            try {
                List lista = categoriaService.listCategorias();
                // Asignamos al request el atributo lista
                ArrayList<Pelicula> listaArray = new ArrayList<>(lista);
                request.getSession().setAttribute("categorias", listaArray);
                // Pasamos al RequestDispatcher la pagina a cargar
                RequestDispatcher rd = request.getRequestDispatcher("/addPelicula.jsp");
                // Cargamos la pagina
                rd.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (accion != null && accion.equals("paso2")) {

            //1. Recuperamos los parametros
            String nombre = request.getParameter("nombre");
            String duracion = request.getParameter("duracion");
            String anyoGrabacion = request.getParameter("anyoGrabacion");
            String idCategoria = request.getParameter("categoria");

            //2. Creamos el objeto
            Pelicula pelicula = new Pelicula(nombre, Integer.valueOf(duracion), Integer.valueOf(anyoGrabacion));

            // 3. Buscamos la categoria y la asignamos
            Categoria categoria = new Categoria();
            categoria.setId(Integer.valueOf(idCategoria));

            try {
                categoria = categoriaService.findCategoriaById(categoria);
            } catch (Exception e) {
                //Informamos cualquier error
                e.printStackTrace();
            }

            pelicula.setCategoria(categoria);

            // 4. Damos de alta la pelicula
            try {
                peliculaService.addPelicula(pelicula);
            } catch (Exception e) {
                //Informamos cualquier error
                e.printStackTrace();
            }

            listPeliculas(request, response);
        }
    }

    private void deletePelicula(HttpServletRequest request, HttpServletResponse response) {
        //1. Recuperamos los parametros
        String idString = request.getParameter("id");

        //2. Creamos el objeto
        int id = Integer.parseInt(idString);
        Pelicula pelicula = new Pelicula();
        pelicula.setId(id);

        try {
            this.peliculaService.deletePelicula(pelicula);
        } catch (Exception e) {
            //Informamos cualquier error
            e.printStackTrace();
        }

        listPeliculas(request, response);
    }

    private void updatePelicula(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion != null && accion.equals("editar")) {

            //1. Recuperamos el id 
            String idString = request.getParameter("id");
            if (idString != null) {
                //2. Creamos el objeto a recuperar
                int id = Integer.valueOf(idString);
                Pelicula pelicula = new Pelicula();
                pelicula.setId(id);
                try {
                    pelicula = this.peliculaService.findPeliculaById(pelicula);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //3. Compartimos el objeto en alcance request
                request.setAttribute("pelicula", pelicula);

                //4. Redireccionamos a la pagina para editar el objeto
                request.getRequestDispatcher("/updatePelicula.jsp").forward(request, response);
            }
        } else if (accion != null && accion.equals("modificar")) {

            //1. Recuperamos los parametros
            String idString = request.getParameter("id");
            String nombre = request.getParameter("nombre");
            String duracion = request.getParameter("duracion");
            String anyoGrabacion = request.getParameter("anyoGrabacion");
            String idCategoria = request.getParameter("categoria");

            //2. Recuperamos la pelicula                       
            Pelicula pelicula = new Pelicula();
            pelicula.setId(Integer.valueOf(idString));

            try {
                pelicula = this.peliculaService.findPeliculaById(pelicula);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 3. Asignamos los valores
            pelicula.setNombre(nombre);
            pelicula.setDuracion(Integer.valueOf(duracion));
            pelicula.setAnyoGrabacion(Integer.valueOf(anyoGrabacion));

            Categoria categoria = new Categoria();
            categoria.setId(Integer.valueOf(idCategoria));

            try {
                categoria = categoriaService.findCategoriaById(categoria);
            } catch (Exception e) {
                //Informamos cualquier error
                e.printStackTrace();
            }

            pelicula.setCategoria(categoria);

            try {
                this.peliculaService.updatePelicula(pelicula);
            } catch (Exception e) {
                //Informamos cualquier error
                e.printStackTrace();
            }

            listPeliculas(request, response);
        }
    }

    private void managerActorToPelicula(HttpServletRequest request, HttpServletResponse response) {
        String accion = request.getParameter("accion");

        if (accion != null && accion.equals("listar")) {

            // 1. Recuperamos el id
            String idString = request.getParameter("id");

            //2. Recuperamos la pelicula                       
            Pelicula pelicula = new Pelicula();
            pelicula.setId(Integer.valueOf(idString));

            try {
                pelicula = this.peliculaService.findPeliculaById(pelicula);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //4. Volvemos a listar
            listarManagerActorToPelicula(request, response, pelicula);
        } else if (accion != null && accion.equals("borrar")) {
            // 1. Recuperamos el id
            String idString = request.getParameter("id");
            Pelicula pelicula = (Pelicula) request.getSession().getAttribute("pelicula");

            //2. Recuperamos el actor                      
            int id = Integer.valueOf(idString);
            Actor actor = new Actor();
            actor.setId(id);

            //3. Borramos el actor            
            try {
                actor = this.actorService.findActorById(actor);
                // Actualizamos la pelicula
                
                //*****IMPORTATE:QUITAR
                //pelicula.getActores().remove(actor);
                //actor.getPeliculas().remove(pelicula);
                
                this.peliculaService.updatePelicula(pelicula);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //4. Volvemos a listar
            listarManagerActorToPelicula(request, response, pelicula);

        } else if (accion != null && accion.equals("agregar")) {
            // 1. Recuperamos el id
            Pelicula pelicula = (Pelicula) request.getSession().getAttribute("pelicula");
            String idActor = request.getParameter("actor");

            //2. Recuperamos el actor                      
            Actor actor = new Actor();
            actor.setId(Integer.valueOf(idActor));

            try {
                actor = this.actorService.findActorById(actor);
                // Creamos las relaciones
                
                //*****IMPORTATE:QUITAR
                //pelicula.getActores().add(actor);
                //actor.getPeliculas().add(pelicula);
                
                
                // Actualizamos los datos
                this.peliculaService.updatePelicula(pelicula);
                this.actorService.updateActor(actor);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //4. Volvemos a listar
            listarManagerActorToPelicula(request, response, pelicula);
        }
    }

    private void listarManagerActorToPelicula(HttpServletRequest request, HttpServletResponse response, Pelicula pelicula) {
        List listaActores = null;
        try {
            listaActores = this.actorService.listActores();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Obtenemos los actores que han participado en la pelicula
        
        //*****IMPORTATE:QUITAR
        //Set<Actor> actores = pelicula.getActores();
        
        // A la lista total de actores quitamos los que han participado en la pelicula
        
        //*****IMPORTATE:QUITAR
        //listaActores.removeAll(actores);
        
        
        //Lista de actores que podemos añadir a la pelicula
        ArrayList<Actor> listaActoresASeleccionar = new ArrayList<>(listaActores);
        // Lista de actores que ya participan en la pelicula
        
        //*****IMPORTATE:QUITAR
        //ArrayList<Actor> actoresYaParticipantes = new ArrayList<>(actores);

        try {
            request.getSession().setAttribute("listaActoresASeleccionar", listaActoresASeleccionar);
            request.getSession().setAttribute("pelicula", pelicula);
            
            //*****IMPORTATE:QUITAR
            //request.getSession().setAttribute("actoresYaParticipantes", actoresYaParticipantes);
            
            
            RequestDispatcher rd = request.getRequestDispatcher("/managerActorToPelicula.jsp");
            // Cargamos la pagina
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listPeliculasByActor(HttpServletRequest request, HttpServletResponse response) {
        //1. Recuperamos los parametros
        String idString = request.getParameter("id");

        //2. Creamos el objeto
        int id = Integer.parseInt(idString);
        Actor actor = new Actor();
        actor.setId(id);

        try {
            actor = this.actorService.findActorById(actor);
            
            ArrayList<Pelicula> listaPeliculas = new ArrayList<>(actor.getPeliculas());
            request.setAttribute("peliculasActor", listaPeliculas);
            
            request.setAttribute("actor", actor);
            RequestDispatcher rd = request.getRequestDispatcher("/listPeliculasByActor.jsp");
            // Cargamos la pagina
            rd.forward(request, response);            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
