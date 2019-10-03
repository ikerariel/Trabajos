<%-- 
    Document   : menuprincipal_v
    Created on : 25/05/2018, 02:51:34 PM
    Author     : Carlos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%

        HttpSession sessionActiva = request.getSession();
        if (sessionActiva.getAttribute("sessionON") == null) {
            response.sendRedirect("/Taller_tercero/acceso_v.jsp");
        }
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link rel="stylesheet" href="Recursos/css/estilos_1.css">
        <link rel="stylesheet" href="Recursos/css/iconos.css">
        <link rel="stylesheet" href="Recursos/css/bootstrap1.css">
        <link rel="stylesheet" href="Recursos/css/font-mfizz.css">
        <link rel="stylesheet" href="Recursos/css/font-awesome.css">
        <link rel="stylesheet" href="Recursos/css/bootstrap.css"/>
        <script type="text/javascript" src="Recursos/js/jquery.js"></script>
        <script src="Recursos/js/main.js"></script>
        <script src="Recursos/js/bootstrap.js"></script>
        <script src="Recursos/js/jquery.backstretch.min.js"></script>
        <script src="Recursos/js/ImagenFondo.js"></script> 
       
        <title>Menu Principal</title>
    </head>
    <body>
        <%@ include file="menutercero.jsp"%>

    </body>
</html>
