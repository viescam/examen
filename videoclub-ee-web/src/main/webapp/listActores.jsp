<%@page import="org.videoclub.domain.Actor"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Listado Actores</title>
</head>
<body>

	<h1>Listado de Actores</h1>
        
	<a href="addActor.jsp">Agregar Actor</a>
	<br/>
	<br/>

	<table border="1">
		<tr>
                    <th>Id</th>
                    <th>Nombre</th>
                    <th>Apellidos</th>
                    <th>Sexo</th>
                    <th>Documento</th>
                    <th>Dirección</th>
                    <th>Población</th>
                    <th>Código Postal</th>
                    <th>Provincia</th>
                    <th></th>
                    <th></th>
                    <th></th>
 
		</tr>

                <%
                ArrayList<Actor> lista = (ArrayList) session.getAttribute("actores");
                for(Actor actor : lista){
                    
                    int id = actor.getId();
                    String nombre = actor.getNombre();
                    String apellidos = actor.getApellidos();
                    String sexo = actor.getSexo();
                    if(sexo.equals("H")){
                        sexo="Hombre";
                    }else if (sexo.equals("M")){
                        sexo="Mujer";
                    }else {
                        sexo="DESCONOCIDO";
                    }
                    
                    String documento = actor.getDocumento();
                    
                    String direccion = actor.getDireccion().getDireccion();
                    String poblacion = actor.getDireccion().getPoblacion();
                    String provincia = actor.getDireccion().getProvincia();
                    String codigoPostal = actor.getDireccion().getCodigoPostal();                    
                %>                
		<tr>
                    <td><%=id%></a></td>
                    <td><%=nombre%></td>
                    <td><%=apellidos%></td>
                    <td><%=sexo%></td>
                    <td><%=documento%></td>
                    <td><%=direccion%></td>
                    <td><%=poblacion%></td>
                    <td><%=codigoPostal%></td>
                    <td><%=provincia%></td>
                    <td><a href="UpdateActor?accion=editar&id=<%=id%>">Modificar</td>
                    <td><a href="DeleteActor?id=<%=id%>">Eliminar</a></td>
                    <td><a href="ListPeliculasByActor?id=<%=id%>">Visualizar Películas</a></td>
                </tr>
                <% } %>
	</table>
	<br>
	<a href="index.jsp">Regresar al Inicio</a>
</body>
</html>