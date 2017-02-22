<%-- 
    Document   : listPeliculasByCategoria
    Created on : 22-feb-2017, 11:20:24
    Author     : alumno
--%>

<%@page import="org.videoclub.domain.Pelicula"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.videoclub.domain.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de peliculas por Categoría</title>
    </head>
    <body>
        <h1>Listado de Películas por Actor</h1>

        <%
            Categoria cat = (Categoria) request.getAttribute("categoria");
        %>        
	<br/>
        <h2>La categoria seleccionada es: <%=cat.getNombre()%></h2>
	<br/>

	<table border="1">
		<tr>
                    <th>Id</th>
                    <th>Nombre</th>
                    <th>Duración</th>
                    <th>Año</th>
                    <th>Categoria</th>
 
		</tr>

                <%
                ArrayList<Pelicula> lista = (ArrayList) request.getAttribute("peliculasCategoria");
                for(Pelicula pelicula : lista){
                    
                    int id = pelicula.getId();
                    String nombre = pelicula.getNombre();
                    String duracion = String.valueOf(pelicula.getDuracion());
                    String anyoGrabacion = String.valueOf(pelicula.getAnyoGrabacion());
                    String nombreCategoria = pelicula.getCategoria().getNombre();
                %>                
		<tr>
                    <td><%=id%></a></td>
                    <td><%=nombre%></td>
                    <td><%=duracion%></td>
                    <td><%=anyoGrabacion%></td>
                    <td><%=nombreCategoria%></td>
                </tr>
                <% } %>
	</table>
	<br>
	<a href="index.jsp">Regresar al Inicio</a>
</body>
</html>
