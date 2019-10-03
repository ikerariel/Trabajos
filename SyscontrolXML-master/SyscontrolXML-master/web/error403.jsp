<%-- 
    Document   : error403
    Created on : 29-jul-2019, 0:34:54
    Author     : !mX
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        HttpSession sessionActiva = request.getSession();
        if (sessionActiva.getAttribute("sessionON") == null) {
            response.sendRedirect("/syscontrol/nologin.jsp");
        }
    %>
    <head>

        <meta charset="utf-8">
        <meta http-equiv="refresh" content="${pageContext.session.maxInactiveInterval};url=out.jsp">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="!mX">

        <title>Error 403 - Forbidden</title>
        <!-- Bootstrap CSS -->
        <link href="Recursos/css/bootstrap.min.css" rel="stylesheet">

        <style>
            .centrar {
                margin-top: 20%;
                margin-left: auto;
                margin-right: auto;
                max-width: 600px;
            }
        </style>

    </head>
    <body>
        <div class="centrar alert alert-danger alert-dismissible text-center" role="alert">
            El recurso no esta disponible, Usted no tienes permiso para acceder.
            <strong>Error 403 - Forbidden</strong>
        </div>
</html>
