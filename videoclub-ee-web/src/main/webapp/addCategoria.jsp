<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Agregar Categor�a</title>
    </head>
    <body>

        <h1>Agregar Categor�a</h1>

        <form action="AddCategoria" method="post">

            <label for="nombre">Nombre:</label>
            <input type="text" name="nombre" style="display: block;" />

            <input type="submit" value="Enviar" />
        </form>

        <a href="index.jsp">Regresar al Inicio</a>
    </body>
</html>
