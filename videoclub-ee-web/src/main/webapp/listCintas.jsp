<%@page import="org.videoclub.domain.Pelicula"%>
<%@page import="org.videoclub.domain.Cinta"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Listado de Cintas</title>
</head>
<body>

	<h1>Listado de Cintas</h1>
        
        <%
            Pelicula pelicula = (Pelicula) session.getAttribute("pelicula");
        %>
        <br>
        <h2>La película seleccionada es: <%=pelicula.getNombre()%> </h2>        
        
	<a href="addCinta.jsp">Agregar Cinta</a>
	<br/>
	<br/>

	<table border="1">
		<tr>
                    <th>Id</th>
                    <th>Número Serie</th>
                    <th>Estado</th>
                    <th></th>
                    <th></th>
		</tr>

                <%
                ArrayList<Cinta> lista = (ArrayList) session.getAttribute("cintas");
                for(Cinta cinta : lista){
                    
                    int id = cinta.getId();
                    String numeroSerie = cinta.getNumeroSerie();
                    int estado = cinta.getEstado();
                    String descripcionEstado = "";
                    if (estado==0){
                        descripcionEstado="Malo";
                    }else if (estado==1){
                        descripcionEstado="Regular";
                    }else if (estado==2){
                        descripcionEstado="Buena";
                    }
                %>                
		<tr>
                    <td><%=id%></a></td>
                    <td><%=numeroSerie%></td>
                    <td><%=descripcionEstado%></td>
                    <td><a href="UpdateCinta?accion=editar&id=<%=id%>">Modificar</td>
                    <td><a href="DeleteCinta?id=<%=id%>">Eliminar</a></td>
                </tr>
                <% } %>
	</table>
	<br>
	<a href="index.jsp">Regresar al Inicio</a>
</body>
</html>