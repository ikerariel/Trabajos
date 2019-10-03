<%-- 
    Document   : usuariovista
    Created on : 22/05/2019, 07:17:27 PM
    Author     : Rafel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

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
        <script src="Validadores/usuariosvalidador.js"></script>
        <script type="text/javascript" src="Validadores/buscadorvalidador.js"></script>
        <title>USUARIOS</title>
    </head>
    <body>
        <%@ include file="menutercero.jsp"%>
        <section>
            <form class="form-horizontal"  id="defaultForm">
                <label class="col-md-8 control-label" style=" font-weight: bold; font-size: 25pt; color: #003eff">MANTENER  USUARIOS</label> 
                <BR>
                <BR>
                <BR>
                <div class="panel panel-default"> 
                    <div class="panel-footer" style="font-weight: bold" >
                        <a id="btnNuevo" class="btn btn-lg btn-success" style=" font-weight: bold" onclick="autonumerico()">Nuevo </a>
                        <a id="btnInsertar" class="btn btn-lg btn-primary" style=" font-weight: bold" onclick="controlarCampousua(), campovaciousua()">Grabar</a>
                        <a id="btnModificar" class="btn btn-lg btn-info" style=" font-weight: bold" title="Modificar" onclick="amb(2)">Modificar </a>
                        <a id="btnAnular" class="btn btn-lg btn-danger" style=" font-weight: bold" title="Borrar" onclick="amb(3)">Borrar*</a>
                        <a id="btnLimpiar" class="btn btn-lg btn-warning" style=" font-weight: bold" title="Borrar" onclick="limpiarcampousua()">limpiar</a>
                        <a id="btnReporte" class="btn btn-lg btn-primary glyphicon glyphicon-print" title="Reporte de Panilla" onclick="reportesUsuarios()"></a>
                    </div>
                    <BR>
                    <div class="">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label" style=" font-weight: bold">CODIGO</label>  
                                <div class="col-md-3">
                                    <input  id="cod_usua" style="text-transform: uppercase; font-weight: bold; font-size: 12pt;
                                            background-color: #d9edf7 " 
                                        type="text" placeholder="Registro" class="form-control input-sm"
                                        onkeydown=" if (event.keyCode === 13) { listarUsuarioSegunFiltro(); }">
                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">USUARIO</label>  
                                <div class="col-md-3">
                                    <input id="usu_nomb"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt;
                                           background-color: #d9edf7" placeholder="Ingrese usuarios." class="form-control input-sm" required autofocus="">
                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">CLAVE</label>  
                                <div class="col-md-3">
                                    <input id="usu_clav"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt; 
                                           background-color: #d9edf7" placeholder="Ingrese clave" class="form-control input-sm">
                                </div>
                            </div>
                        </div>
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label" style=" font-weight: bold">FUNCIONARIO</label>  
                                <div class="col-md-3" id="comboFuncionario">
                                    <select id="fun_descri" type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt;
                                            background-color: #d9edf7" class="form-control input-sm"></select>
                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">PERFIL</label>  
                                <div class="col-md-3" id="comboPerfil">
                                    <select id="perf_drscri" type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt;
                                            background-color: #d9edf7" class="form-control input-sm"></select>
                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">SUCURSAL</label>  
                                <div class="col-md-3" id="comboSucursal">
                                    <select id="sucu_descri" type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt;
                                            background-color: #d9edf7" class="form-control input-sm"></select>
                                </div>
                            </div>
                        </div>
                    </div> 
                    <BR>
                    <div class="input-group">
                        <span class="input-group-addon">BUSCAR</span>
                        <input class="form-control" type="text" id="filtrar" placeholder="Ingrese Descripcion a Buscar"
                               style="text-transform: uppercase; font-weight: bold; font-size:12pt; background-color: #d9edf7 ">
                    </div>
                    <br>
                    <div class="table-responsive" > 
                        <div class="headercontainer" >
                            <div class="tablecontainer">
                                <table class="table" id="miTabla" onclick="seleccionarusua()">
                                    <thead>
                                        <tr>
                                            <th style="width: 70px"><div>CODIGO</div></th>
                                            <th style="width: 100px"><div>USUARIO</div></th>
                                            <th style="width: 100px"><div>CLAVE</div></th>
                                            <th style="width: 100px"><div>FUNCIONARIO</div></th>
                                            <th style="width: 100px"><div>PERFIL</div></th>
                                            <th style="width: 100px"><div>SUCURSAL</div></th>
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
