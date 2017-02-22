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
import org.videoclub.domain.Categoria;
import org.videoclub.domain.Pelicula;
import org.videoclub.service.CategoriaServiceLocal;

@WebServlet(name = "ControllerCategoria",
        loadOnStartup = 1,
        urlPatterns = {"/ListCategorias",
            "/AddCategoria",
            "/DeleteCategoria",
            "/UpdateCategoria",
            "/ListPeliculasByCategoria"})
public class ControllerCategoria extends HttpServlet {

    @EJB
    private CategoriaServiceLocal categoriaService;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();

        // Si la operacion es listar categorias
        switch (userPath) {
            case "/ListCategorias":
                listCategorias(request, response);
                // Si la operacion es Alta Categoria
                break;
            case "/AddCategoria":
                addCategoria(request, response);
                // Si la operacion es Eliminar Categoria
                break;
            case "/DeleteCategoria":
                deleteCategoria(request, response);
                // Si la operacion es Modificar Categoria
                break;
            case "/UpdateCategoria":
                updateCategoria(request, response);
                break;
            case "/ListPeliculasByCategoria":
                listPeliculasByCategoria(request,response);
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

    private void listCategorias(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Ejecutamos el metodo y obtenemos la lista
            List lista = categoriaService.listCategorias();
            // Asignamos al request el atributo lista
            ArrayList<Categoria> listaArray = new ArrayList<>(lista);
            request.getSession().setAttribute("categorias", listaArray);
            // Pasamos al RequestDispatcher la pagina a cargar
            RequestDispatcher rd = request.getRequestDispatcher("/listCategorias.jsp");
            // Cargamos la pagina
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addCategoria(HttpServletRequest request, HttpServletResponse response) {
        //1. Recuperamos los parametros
        String nombre = request.getParameter("nombre");

        //2. Creamos el objeto
        Categoria categoria = new Categoria(nombre);

        try {
            categoriaService.addCategoria(categoria);
        } catch (Exception e) {
            //Informamos cualquier error
            e.printStackTrace();
        }

        listCategorias(request, response);
    }

    private void deleteCategoria(HttpServletRequest request, HttpServletResponse response) {
        //1. Recuperamos los parametros
        String idString = request.getParameter("id");

        //2. Creamos el objeto
        int id = Integer.parseInt(idString);
        Categoria categoria = new Categoria();
        categoria.setId(id);

        try {
            this.categoriaService.deleteCategoria(categoria);
        } catch (Exception e) {
            //Informamos cualquier error
            e.printStackTrace();
        }

        listCategorias(request, response);
    }

    private void updateCategoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion != null && accion.equals("editar")) {

            //1. Recuperamos el id 
            String idString = request.getParameter("id");
            if (idString != null) {
                //2. Creamos el objeto a recuperar
                int id = Integer.valueOf(idString);
                Categoria categoria = new Categoria();
                categoria.setId(id);
                try {
                    categoria = this.categoriaService.findCategoriaById(categoria);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //3. Compartimos el objeto en alcance request
                request.setAttribute("categoria", categoria);

                //4. Redireccionamos a la pagina para editar el objeto
                request.getRequestDispatcher("/updateCategoria.jsp").forward(request, response);
            }
        } else if (accion != null && accion.equals("modificar")) {

            //1. Recuperamos los parametros
            String idString = request.getParameter("id");
            String nombre = request.getParameter("nombre");

            //2. Creamos el objeto
            Categoria categoria = new Categoria();
            int id = Integer.valueOf(idString);
            categoria.setId(id);

            try {
                categoria = this.categoriaService.findCategoriaById(categoria);

                categoria.setNombre(nombre);

                this.categoriaService.updateCategoria(categoria);
            } catch (Exception e) {
                //Informamos cualquier error
                e.printStackTrace();
            }

            listCategorias(request, response);
        }
    }
    
    private void listPeliculasByCategoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. Recuperamos los parametros
        String idString = request.getParameter("id");

        //2. Creamos el objeto
        int id = Integer.parseInt(idString);
        Categoria cat = new Categoria();
        cat.setId(id);
        
        try {
            cat = this.categoriaService.findCategoriaById(cat);
            
            
            ArrayList<Pelicula> listaPeliculas = new ArrayList<>(cat.getPeliculas());
            request.setAttribute("peliculasCategoria", listaPeliculas);
            
            request.setAttribute("categoria", cat);
            RequestDispatcher rd = request.getRequestDispatcher("/listPeliculasByCategoria.jsp");
            // Cargamos la pagina
            rd.forward(request, response);            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    } 
}
