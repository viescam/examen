<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Modificar Categoría</title>
    </head>
    <body>

        <h1>Modificar Categoría</h1>

        <form action="UpdateCategoria?accion=modificar&id=${categoria.id}" method="post">

            <label for="nombre">Nombre:</label>
            <input type="text" name="nombre" value="${categoria.nombre}" style="display: block;" />   

            <input type="submit" name="guardar" value="guardar">
        </form>
    </body>
</html>
