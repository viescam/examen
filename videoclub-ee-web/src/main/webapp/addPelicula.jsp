<%@page import="org.videoclub.domain.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Agregar Película</title>
    </head>
    <body>

        <h1>Agregar Película</h1>

        <form action="AddPelicula" method="post">

            <input type="hidden" name="accion" value="paso2"/>

            <label for="nombre">Nombre:</label>
            <input type="text" name="nombre" style="display: block;" />

            <label for="duracion">Duración:</label>
            <input type="text" name="duracion" style="display: block;" />

            <label for="anyoGrabacion">Año Grabación:</label>
            <input type="text" name="anyoGrabacion" style="display: block;" />

            <label for="categoria">Categoria:</label>
            <select name="categoria" style="display: block;">   
            <%
                ArrayList<Categoria> lista = (ArrayList) session.getAttribute("categorias");
                for (Categoria categoria : lista) {

                    int id = categoria.getId();
                    String nombre = categoria.getNombre();
            %>  
                <option value="<%=id%>" ><%=nombre%></option>
            <% } %>            
            </select>               

            <input type="submit" value="Enviar" />
        </form>

        <a href="index.jsp">Regresar al Inicio</a>
    </body>
</html>
