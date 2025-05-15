<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inicio de Sesión</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }
        .login-container {
            width: 300px;
            margin: 100px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px #aaa;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        input {
            width: 100%;
            padding: 10px;
            margin-top: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        input[type="submit"], button {
            background-color: #5cb85c;
            color: white;
            padding: 10px;
            border: none;
            width: 100%;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 10px;
        }
        .error {
            color: red;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Iniciar Sesión</h2>
        <form action="${pageContext.request.contextPath}/LoginController" method="post">
            <label>Correo:</label>
            <input type="email" name="mail" required><br>
            <label>Contraseña:</label>
            <input type="password" name="password" required><br>
            <button type="submit">Ingresar</button>
        </form>

        <form action="Views/registrar.jsp" method="get">
            <button type="submit">Registrarse</button>
        </form>

        <% String error = (String) request.getAttribute("error");
           if (error != null) { %>
           <p class="error"><%= error %></p>
        <% } %>
    </div>
</body>
</html>
