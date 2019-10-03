<%-- 
    Document   : depositovista
    Created on : 12/04/2018, 03:38:08 PM
    Author     : Rafel
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
        <title>DEPOSITOS</title>
        <script src="../Validadores/depositovalidador.js"></script>
        <script src="../Validadores/buscadorvalidador.js"></script>

    </head>
    <body>
        <%@ include file="../menutercero.jsp"%>
        <section>
            <form class="form-horizontal"  id="defaultForm">
               
                    <a id="btnNuevo" class="btn btn-lg btn-success" style=" font-weight: bold" onclick="autonumerico()">Nuevo </a>
                    <a id="btnInsertar" class="btn btn-lg btn-primary" style=" font-weight: bold" onclick="amb(1)">Insertar </a>
                    <a id="btnModificar" class="btn btn-lg btn-info" style=" font-weight: bold" title="Modificar" onclick="amb(2)">Modificar </a>
                    <a id="btnAnular" class="btn btn-lg btn-danger" style=" font-weight: bold" title="Borrar" onclick="amb(3)">Borrar*</a>
                
                <BR>
                <BR>
                <div class="panel panel-default">              
                    <div class="panel-footer" style="font-weight: bold">GESTIONAR DEPOSITOS</div>
                    <br>
                    <div class="">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label" style=" font-weight: bold">CODIGO</label>  
                                <div class="col-md-3">
                                    <input  id="cod_deposito" style="text-transform: uppercase; font-weight: bold; font-size: 12pt;
                                            background-color: #d9edf7 " type="text" placeholder="Registro" class="form-control input-sm" required=""
                                        onkeydown=" if (event.keyCode === 13) { listardepositoSegunFiltro(); }">
                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">NOMBRE</label>  
                                <div class="col-md-3">
                                    <input id="depo_descrip"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt;
                                           background-color: #d9edf7" placeholder="Ingrese nombre" class="form-control input-sm" required autofocus="">
                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">SUCURSALES</label>  
                                <div class="col-md-3" id="comboCiudad">
                                    <select id="descri_sucur" type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt;
                                            background-color: #d9edf7" class="form-control input-sm"></select>
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
                                <table class=" table-striped table-bordered table-hover table input-md" id="miTabla" onclick="seleccionar()">
                                    <thead>
                                        <tr>
                                            <th style="width: 80px"><div>CODIGO</div></th>
                                            <th style="width: 80px"><div>DEPOSITOS</div></th>
                                            <th style="width: 80px"><div>SUCURSALES</div></th>
                                            <th style="width: 5px"><div><center><img src="../Recursos/img/update.png"/></center></div></th>
                                            <th style="width: 5px"><div><center><img src="../Recursos/img/delete.png"/></center></div></th>
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
