<%-- 
    Document   : cajavista
    Created on : 10/11/2017, 04:46:55 PM
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
        <link rel="stylesheet" href="../Recursos/css/bootstrap.css"/>
        <link rel="stylesheet" href="../Recursos/css/bootstrap-theme.min.css"/>
        <link rel="stylesheet" href="../Recursos/css/bootstrap-select.css"/>
        <link rel="stylesheet" href="../Recursos/css/bootstrap-select.min.css"/>
        <script src="../Recursos/js/main.js"></script>
        <script src="../Recursos/js/jquery.js"></script>
        <script src="../Recursos/js/bootstrap.js"></script>
        <script src="../Recursos/js/jquery.backstretch.min.js"></script>
        <script src="../Recursos/js/jquery.dataTables.min.js"></script>
        <script src="../Recursos/js/bootstrapValidator.js"></script>
        <script src="../Recursos/js/bootstrap-select.js"></script>
        <script src="../Recursos/js/bootstrap-select.min.js"></script>
        <script src="../Recursos/js/ImagenFondo.js"></script> 
        <script type="text/javascript" src="../Validadores/cajvalidador.js"></script>
        <script type="text/javascript" src="../Validadores/buscadorvalidador.js"></script>
        
        <title>CAJA</title>
    </head>
    <body>
            <%@ include file="../menutercero.jsp"%>
        <section>
            <form class="form-horizontal"  id="defaultForm">
               
                    <a id="btnNuevo" class="btn btn-lg btn-success" style=" font-weight: bold" onclick="autonumerico()">Nuevo </a>
                    <a id="btnInsertar" class="btn btn-lg btn-primary" style=" font-weight: bold" onclick="controlarCampo(), campovacio()">Insertar </a>
                    <a id="btnModificar" class="btn btn-lg btn-info" style=" font-weight: bold" title="Modificar" onclick="amb(2)">Modificar </a>
                    <a id="btnAnular" class="btn btn-lg btn-danger" style=" font-weight: bold" title="Borrar" onclick="amb(3)">Borrar</a>
                    <a id="btnLimpiar" class="btn btn-lg btn-warning" style=" font-weight: bold" title="Borrar" onclick="limpiarcampo()">limpiar</a>
                    <a class="close  btn btn-lg btn-danger glyphicon glyphicon-off" data-dismiss="" aria-hidden="true" title="Salir"></a>
                <BR>
                <BR>
                <div class="panel panel-default">              
                    <div class="panel-footer" style="font-weight: bold">GESTIONAR CAJAS</div>
                    <br>
                    <div class="">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-2 control-label" style=" font-weight: bold">CODIGO</label>  
                                <div class="col-md-4">
                                    <input  id="cod_caja" style="text-transform: uppercase; font-weight: bold; font-size: 12pt;
                                            background-color: #d9edf7 " type="text" placeholder="Registro" class="form-control input-sm" required=""
                                        onkeydown=" if (event.keyCode === 13) { listarCajaSegunFiltro(); }">
                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">NOMBRE</label>  
                                <div class="col-md-4">
                                    <input id="descr_caja"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt;
                                           background-color: #d9edf7" placeholder="Ingrese nombre" class="form-control input-sm" required autofocus="">
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
                                <table class=" table-striped table-bordered table-hover table input-md" id="miTabla" onclick="selecc()">
                                    <thead>
                                        <tr>
                                            <th><div>CODIGO</div></th>
                                            <th><div>CAJA</div></th>
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
