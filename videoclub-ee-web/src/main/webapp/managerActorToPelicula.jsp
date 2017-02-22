<%@page import="java.util.Set"%>
<%@page import="org.videoclub.domain.Pelicula"%>
<%@page import="org.videoclub.domain.Actor"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Gestión de Actores y Películas</title>
    </head>
    <body>

        <%
            Pelicula pelicula = (Pelicula) session.getAttribute("pelicula");
        %>
        <h1>Gestión de Actores y Películas</h1>
        <br>
        <h2>La película seleccionada es: <%=pelicula.getNombre()%> </h2>

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

                <%
                ArrayList<Actor> lista = (ArrayList) session.getAttribute("actoresYaParticipantes");
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
                    <td><a href="ManagerActorToPelicula?accion=borrar&id=<%=id%>">Eliminar</a></td>
                </tr>
                <% } %>                
            </tr>            
        </table>        

        <br>
        <br>

        <%
            ArrayList<Actor> listaActoresASeleccionar = (ArrayList) session.getAttribute("listaActoresASeleccionar");
            
            if(listaActoresASeleccionar.size()>0){
                
            %>
        
        <h4>Seleccione el actor que desea añadir:</h4>

        <form action="ManagerActorToPelicula?accion=agregar&id=<%=pelicula.getId()%>" method="post">

            <label for="actor">Actor:</label>
            <select name="actor" style="display: block;">   
            <%
                for (Actor actor : listaActoresASeleccionar) {

                    int id = actor.getId();
                    String valor = actor.getNombre() + " " + actor.getApellidos();
            %>  
                <option value="<%=id%>" ><%=valor%></option>
            <% } %>            
            </select>             
            <input type="submit" value="Enviar" />
        </form>

            <% } %>
        <a href="index.jsp">Regresar al Inicio</a>
    </body>
</html>
