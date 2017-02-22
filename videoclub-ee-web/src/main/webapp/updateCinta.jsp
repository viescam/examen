<%-- 
    Document   : updateCinta
    Created on : 22-feb-2017, 11:52:36
    Author     : alumno
--%>

<%@page import="org.videoclub.domain.Cinta"%>
<%@page import="org.videoclub.domain.Pelicula"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar Cinta</title>
    </head>
    <body>

        <h1>Modificar Cinta</h1>
        
        <%
            Pelicula pelicula = (Pelicula) session.getAttribute("pelicula");
        %>
        <br>
        <h2>La película seleccionada es: <%=pelicula.getNombre()%> </h2>         

        <form action="UpdateCinta?accion=modificar&id=${cinta.id}" method="post">

            <label for="numeroSerie">Número de Serie:</label>
            <input type="text" name="numeroSerie" style="display: block;" value="${cinta.numeroSerie}"/>

            <label for="estado">Estado:</label>
            <select name="estado" style="display: block;">   
            <%
                Cinta cinta = (Cinta) request.getAttribute("cinta");
                String[] estados={"Malo","Regular","Bueno"};
                for (int i=0;i<estados.length;i++ ) {
                    if(i == cinta.getEstado()){                    
            %>  
                <option value="<%=i%>" selected><%=estados[i]%></option>
            <%} else { %>
               <option value="<%=i%>" ><%=estados[i]%></option>
            <% }
            } %>            
            </select> 

            <input type="submit" value="Enviar" />
        </form>

        <a href="index.jsp">Regresar al Inicio</a>
    </body>
</html>
