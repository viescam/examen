<%@page import="org.videoclub.domain.Pelicula"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Agregar Cinta</title>
    </head>
    <body>

        <h1>Agregar Cinta</h1>
        
        <%
            Pelicula pelicula = (Pelicula) session.getAttribute("pelicula");
        %>
        <br>
        <h2>La película seleccionada es: <%=pelicula.getNombre()%> </h2>         

        <form action="AddCinta" method="post">

            <label for="numeroSerie">Número de Serie:</label>
            <input type="text" name="numeroSerie" style="display: block;" />

            <label for="estado">Estado:</label>
            <select name="estado" style="display: block;">   
                <option value="0" >Malo</option>
                <option value="1" >Regular</option>
                <option value="2" selected>Bueno</option>          
            </select>               

            <input type="submit" value="Enviar" />
        </form>

        <a href="index.jsp">Regresar al Inicio</a>
    </body>
</html>
