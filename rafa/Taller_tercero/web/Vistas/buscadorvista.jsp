<%-- 
    Document   : buscadorvista
    Created on : 13/11/2017, 03:10:05 PM
    Author     : naty
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link rel="stylesheet" href="../Recursos/css/estilos_1.css">
        <link rel="stylesheet" href="../Recursos/css/iconos.css">
        <link rel="stylesheet" href="../Recursos/css/bootstrap1.css">
        <link rel="stylesheet" href="../Recursos/css/font-mfizz.css">
        <link rel="stylesheet" href="../Recursos/css/font-awesome.css">
        <script src="../Recursos/js/main.js"></script>
        <script src="../Recursos/js/bootstrap.js"></script>
        <script src="../Recursos/js/jquery.backstretch.min.js"></script>
        <title>BUSCADOR</title>
        <link rel="stylesheet" type="text/css" href="../Recursos/css/bootstrap.css" />
        <script type="text/javascript" src="../Recursos/js/jquery.js" ></script>
        <script type="text/javascript" src="../Validadores/buscadorvalidador.js"> </script>
    </head>
    <body>
        <%@ include file="menutercero.jsp"%>
        <div class="container">
            <label id="header2">INGRESE DESCRIPCION</label>
            <div class="input-group">
                <span class="input-group-addon">Buscar</span>
                <input class="form-control" type="text" id="filtrar" style="width: 400px" placeholder="Ingrese Descripcion a Buscar"/>
            </div>
            <div id="buscarRegistro" overflow: scroll>
                
            </div>
            <button onclick="listaBuscadores(1)" class="btn btn-sm btn-info"> caja</button>
            <button onclick="listaBuscadores(2)" class="btn btn-sm btn-info"> ciudades</button>
            <button onclick="listaBuscadores(3)" class="btn btn-sm btn-info"> sucursales</button>
            <button onclick="listaBuscadores(4)" class="btn btn-sm btn-info"> Cargos</button>
            <button onclick="listaBuscadores(5)" class="btn btn-sm btn-info"> procedencia</button>
            <br>
            <br>
            <div id="footer">
                rafael.fg80@gmail.com
            </div>
        </div>
    </body>
</html>
