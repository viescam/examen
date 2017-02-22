<%@page import="org.videoclub.domain.Actor"%>
<%@page import="org.videoclub.domain.Pelicula"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Listado Películas por Actor</title>
</head>
<body>

	<h1>Listado de Películas por Actor</h1>

        <%
            Actor actor = (Actor) request.getAttribute("actor");
        %>        
	<br/>
        <h2>El actor seleccionado es: <%=actor.getNombre()%> <%=actor.getApellidos()%> </h2>        
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
                ArrayList<Pelicula> lista = (ArrayList) request.getAttribute("peliculasActor");
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