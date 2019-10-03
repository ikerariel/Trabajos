<%-- 
    Document   : cargosvista
    Created on : 19/05/2019, 02:00:31 AM
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
        <script src="Validadores/cargosvalidador.js"></script>
        <script type="text/javascript" src="Validadores/buscadorvalidador.js"></script>
        <title>CARGOS</title>
    </head>
    <body>
        <%@ include file="menutercero.jsp"%>
        <section>
            <form class="form-horizontal"  id="defaultForm">
                <label class="col-md-8 control-label" style=" font-weight: bold; font-size: 25pt; color: #003eff">GESTIONAR CARGOS</label> 
                <BR>
                <BR>
                <BR>
                <div class="panel panel-default">              
                    <div class="panel-footer" style="font-weight: bold" >
                        <a id="btnNuevo" class="btn btn-lg btn-success" style=" display: none; font-weight: bold" onclick="autonumerico(), abilitarInsertCargo()">Nuevo </a>
                    <a id="btnInsertar" class="btn btn-lg btn-primary" style=" display: none; font-weight: bold" onclick="controlarCampocargo(),campovaciocargo()">Insertar </a>
                    <a id="btnModificar" class="btn btn-lg btn-info" style=" display: none; font-weight: bold" title="Modificar" onclick="amb(2)">Modificar </a>
                    <a id="btnAnular" class="btn btn-lg btn-danger" style=" display: none; font-weight: bold" title="Borrar" onclick="amb(3)">Borrar*</a>
                    <a id="btnLimpiar" class="btn btn-lg btn-warning" style=" display: none; font-weight: bold" title="Borrar" onclick="limpiarcampocargo()">limpiar</a>
                    <a id="btnReporte" class="btn btn-lg btn-primary glyphicon glyphicon-print" style=" display: none" title="Reporte de Panilla" onclick="reportesCarg()"></a>
                    </div>
                    <BR>
                    <div class="">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-2 control-label" style=" font-weight: bold">CODIGO</label>  
                                <div class="col-md-4">
                                    <input disabled="" id="cod_cargos" style="font-weight: bold; font-size: 12pt;
                                            background-color: #d9edf7 " type="text" placeholder="Registro" class="form-control input-sm" required=""
                                        onkeydown=" if (event.keyCode === 13) { listarCargosSegunFiltro(); }">
                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">NOMBRE</label>  
                                <div class="col-md-4">
                                    <input id="descr_cargos"  type="text" style=" font-weight: bold;font-size: 12pt; background-color: #d9edf7" 
                                           placeholder="Ingrese nombre" class="form-control input-sm" onkeypress="return sololetrasCargos(event)" required autofocus="">
                                </div>
                            </div>
                        </div> 
                    </div> 
                    <BR>
                    <div class="input-group">
                        <span class="input-group-addon">Buscar</span>
                        <input class="form-control" type="text" id="filtrar" placeholder="Ingrese Descripcion a Buscar"
                               style="text-transform: uppercase; font-weight: bold; font-size:12pt; background-color: #d9edf7 ">
                    </div>
                    <br>
                    
                    <div class="table-responsive" > 
                        <div class="headercontainer" >
                            <div class="tablecontainer">
                                <table class=" table-striped table-bordered table-hover table input-md" id="miTabla" onclick="seleccionarCargos()">
                                    <thead>
                                        <tr>
                                            <th><div>CODIGO</div></th>
                                            <th><div>CARGOS</div></th>
                                        </tr>
                                    </thead>
                                    <tbody class="buscar"></tbody>
                                </table>
                            </div>
                        </div>     
                    </div>
                </div>
                <div id="footer">
                rafael.fg80@gmail.com
                </div>
            </form>
        </section>
    </body>
</html>
