<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestión de Productos</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="text-center mb-4">Gestión de Productos</h2>

    <form action="ProductosServlet" method="get" class="card p-4 shadow mb-4">
        <div class="row g-3">
            <div class="col-md-6">
                <label for="estatus" class="form-label">Mostrar productos</label>
                <select name="estatus" id="estatus" class="form-select">
                    <option value="activos">Activos</option>
                    <option value="inactivos">Inactivos</option>
                </select>
            </div>
            <div class="col-md-6 d-flex align-items-end">
                <button type="submit" class="btn btn-primary w-100">Filtrar</button>
            </div>
        </div>
    </form>

    <table class="table table-bordered shadow">
        <thead class="table-dark">
            <tr>
                <th>Nombre</th>
                <th>Descripción</th>
                <th>Cantidad</th>
                <th>Estatus</th>
                <th>Acción</th>
            </tr>
        </thead>
        <tbody>
            <%-- Reemplazar con iteración desde el Servlet o JSTL --%>
            <%-- Ejemplo: --%>
            <!--
            <tr>
                <td>Producto X</td>
                <td>Insumo eléctrico</td>
                <td>15</td>
                <td>Activo</td>
                <td>
                    <form method="post" action="CambiarEstatusProductoServlet">
                        <input type="hidden" name="idProducto" value="1">
                        <input type="hidden" name="nuevoEstatus" value="inactivo">
