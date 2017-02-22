<%@page import="org.videoclub.domain.Pelicula"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Listado Películas</title>
</head>
<body>

	<h1>Listado de Películas</h1>
        
	<a href="AddPelicula?accion=paso1">Agregar Película</a>
	<br/>
	<br/>

	<table border="1">
		<tr>
                    <th>Id</th>
                    <th>Nombre</th>
                    <th>Duración</th>
                    <th>Año</th>
                    <th>Categoria</th>
                    <th></th>
                    <th></th>
                    <th></th>
 
		</tr>

                <%
                ArrayList<Pelicula> lista = (ArrayList) session.getAttribute("peliculas");
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
                    <td><a href="UpdatePelicula?accion=editar&id=<%=id%>">Modificar</td>
                    <td><a href="DeletePelicula?id=<%=id%>">Eliminar</a></td>
                    <td><a href="ManagerActorToPelicula?accion=listar&id=<%=id%>">Gestionar Actores a Película</a></td>
                    <td><a href="ListCintas?id=<%=id%>">Gestionar cintas de la película</a></td>
                </tr>
                <% } %>
	</table>
	<br>
	<a href="index.jsp">Regresar al Inicio</a>
</body>
</html>