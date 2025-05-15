<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="DAO.MovementDAO" %>
<%@ page import="Model.Movement" %>
<!DOCTYPE html>
<html>
<head>
    <title>Historial de Movimientos</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="text-center mb-4">Historial de Movimientos</h2>

    <form method="get" action="MovementController">
    <label>Filtrar por tipo:</label>
    <select name="tipo">
        <option value="todos">Todos</option>
        <option value="entrada">Entrada</option>
        <option value="salida">Salida</option>
    </select>
    <input type="submit" value="Filtrar">
    </form>

    <table border="1" class="styled-table">
    <tr>
        <th>ID</th><th>Producto</th><th>Tipo</th><th>Cantidad</th><th>Usuario</th><th>Fecha y hora</th>
    </tr>
    <%
        List<Movement> movimientos = (List<Movement>) request.getAttribute("movimientos");
        if (movimientos != null) {
            for (Movement m : movimientos) {
    %>
    <tr>
        <td><%= m.getIdMovement() %></td>
        <td><%= m.getProductName() %></td>
        <td><%= m.getType() %></td>
        <td><%= m.getQuantity() %></td>
        <td><%= m.getUser() %></td>
        <td><%= m.getTimestamp() %></td>
    </tr>
    <%
            }
        }
    %>
    </table>

</div>

</body>
</html>
