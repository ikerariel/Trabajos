<%-- 
    Document   : estadovista
    Created on : 22/05/2019, 02:13:14 PM
    Author     : Rafel
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
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="Recursos/css/estilos_1.css">
        <link rel="stylesheet" href="Recursos/css/iconos.css">
        <link rel="stylesheet" href="Recursos/css/bootstrap.css">
        <link rel="stylesheet" href="Recursos/css/bootstrap-theme.min.css"/>
        <link rel="stylesheet" href="Recursos/css/bootstrap-select.css"/>
        <link rel="stylesheet" href="Recursos/css/bootstrap-select.min.css"/>
        <script src="Recursos/js/main.js"></script>
        <script src="Recursos/js/jquery.js"></script>
        <script src="Recursos/js/bootstrap.js"></script>
        <script src="Recursos/js/jquery.backstretch.min.js"></script>
        <script src="Recursos/js/jquery.dataTables.min.js"></script>
        <script src="Recursos/js/bootstrapValidator.js"></script>
        <script src="Recursos/js/bootstrap-select.js"></script>
        <script src="Recursos/js/bootstrap-select.min.js"></script>
        <script src="Recursos/js/ImagenFondo.js"></script> 
        <script src="Recursos/js/jquery.backstretch.min.js"></script>
        <script src="Validadores/.js"></script>
        <script type="text/javascript" src="Validadores/reportescompras.js"></script>
        <title>Reportes</title>
    </head>
    <body>
        <%@ include file="menutercero.jsp"%>
        <section>
            <form class="form-horizontal"  id="defaultForm">
                <label class=" control-label" style=" font-weight: bold; font-size: 25pt; color: #003eff; align-content: center">Reportes</label> 
                <BR>
                <BR>
                <BR>
                <div class="panel panel-default"> 
                    <div class="panel-footer" style="font-weight: bold" >
                        <a id="btnNuevo" class="btn btn-lg btn-success" style=" font-weight: bold" onclick="infreportes(2)" >Generar Reporte </a>
                    </div>
                    <BR>
                    <div class="">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-2 control-label" style=" font-weight: bold">FECHA DESDE</label>  
                                <div class="col-md-3">
                                    <input  id="vFechadesde" style="text-transform: uppercase; font-weight: bold; font-size: 12pt;
                                            background-color: #d9edf7 " type="date" placeholder="Registro" class="form-control input-sm" required=""
                                        onkeydown=" if (event.keyCode === 13) { listarEstadoSegunFiltro(); }">
                                </div>
                                <label class="col-md-2 control-label" style=" font-weight: bold">FECHA HASTA</label>  
                                <div class="col-md-3">
                                    <input id="vFechahasta"  type="date" style="text-transform: uppercase; font-weight: bold;font-size: 12pt;
                                           background-color: #d9edf7" placeholder="Ingrese nombre" class="form-control input-sm" required autofocus="">
                                </div>
                            </div>
                        </div> 
                    </div> 
                    <BR>
                
                </div>
              
            </form>
        </section>
    </body>
</html>
