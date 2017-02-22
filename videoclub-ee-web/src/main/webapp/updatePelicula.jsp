<%@page import="org.videoclub.domain.Pelicula"%>
<%@page import="org.videoclub.domain.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Modificar Película</title>
    </head>
    <body>

        <h1>Modificar Película</h1>

        <form action="UpdatePelicula?accion=modificar&id=${pelicula.id}" method="post">
            
            <label for="nombre">Nombre:</label>
            <input type="text" name="nombre" style="display: block;" value="${pelicula.nombre}"/>

            <label for="duracion">Duración:</label>
            <input type="text" name="duracion" style="display: block;" value="${pelicula.duracion}"/>

            <label for="anyoGrabacion">Año Grabación:</label>
            <input type="text" name="anyoGrabacion" style="display: block;" value="${pelicula.anyoGrabacion}"/>

            <label for="categoria">Categoria:</label>
            <select name="categoria" style="display: block;">   
            <%
                Pelicula pelicula = (Pelicula) request.getAttribute("pelicula");
                ArrayList<Categoria> lista = (ArrayList) session.getAttribute("categorias");
                for (Categoria categoria : lista) {

                    int id = categoria.getId();
                    String nombre = categoria.getNombre();
                    
                    if (pelicula.getId()==id){
            %>  
                <option value="<%=id%>" selected><%=nombre%></option>
            <%} else { %>
               <option value="<%=id%>" ><%=nombre%></option>
            <% }
            } %>            
            </select>             

            <input type="submit" name="guardar" value="guardar">
        </form>
    </body>
</html>
