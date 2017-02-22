<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Agregar Actor</title>
    </head>
    <body>

        <h1>Agregar Actor</h1>

        <form action="AddActor" method="post">

            <label for="nombre">Nombre:</label>
            <input type="text" name="nombre" style="display: block;" />

            <label for="apellidos">Apellidos:</label>
            <input type="text" name="apellidos" style="display: block;" />        

            <label for="sexo">Sexo:</label>
            <select name="sexo" style="display: block;">
                <option value="H" selected>Hombre</option>
                <option value="M">Mujer</option>
            </select>

            <label for="documento">Documento:</label>
            <input type="text" name="documento" style="display: block;" />  

            <label for="direccion">Dirección:</label>
            <input type="text" name="direccion" style="display: block;" />   

            <label for="poblacion">Población:</label>
            <input type="text" name="poblacion" style="display: block;" /> 

            <label for="codigoPostal">Código Postal:</label>
            <input type="text" name="codigoPostal" style="display: block;" />   

            <label for="provincia">Provincia:</label>
            <input type="text" name="provincia" style="display: block;" />                  

            <input type="submit" value="Enviar" />
        </form>

        <a href="index.jsp">Regresar al Inicio</a>
    </body>
</html>
