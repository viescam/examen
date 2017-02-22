<%@page import="org.videoclub.domain.Actor"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Modificar Actor</title>
    </head>
    <body>

        <h1>Modificar Actor</h1>

        <form action="UpdateActor?accion=modificar&id=${actor.id}" method="post">            
	
		<label for="nombre">Nombre:</label>
		<input type="text" name="nombre" style="display: block;" value="${actor.nombre}"/>
                
		<label for="apellidos">Apellidos:</label>
		<input type="text" name="apellidos" style="display: block;" value="${actor.apellidos}"/>        

                <label for="sexo">Sexo:</label>
                <select name="sexo" style="display: block;" >                
                <%
                    Actor a = (Actor) request.getAttribute("actor");
                    if (a.getSexo().equals("H")){
                %>                
                    <option value="H" selected>Hombre</option>
                    <option value="M" >Mujer</option>
                <% } else if (a.getSexo().equals("M")){ %>
                    <option value="H" >Hombre</option>
                    <option value="M" selected >Mujer</option>
                <% }else { %>
                    <option value="H" selected >Hombre</option>
                    <option value="M">Mujer</option>  
                <% } %>    
                </select>
                               
		<label for="documento">Documento:</label>
		<input type="text" name="documento" style="display: block;" value="${actor.documento}"/>  
                
		<label for="direccion">Dirección:</label>
		<input type="text" name="direccion" style="display: block;" value="${actor.direccion.direccion}"/>   
                
		<label for="poblacion">Población:</label>
		<input type="text" name="poblacion" style="display: block;" value="${actor.direccion.poblacion}"/> 
                
		<label for="codigoPostal">Código Postal:</label>
		<input type="text" name="codigoPostal" style="display: block;" value="${actor.direccion.codigoPostal}"/>   
                
		<label for="provincia">Provincia:</label>
		<input type="text" name="provincia" style="display: block;"value="${actor.direccion.provincia}"/>                                    

            <input type="submit" name="guardar" value="guardar">
        </form>
    </body>
</html>
