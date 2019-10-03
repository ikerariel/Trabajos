<%-- 
    Document   : clientesvista
    Created on : 29/11/2017, 08:14:21 PM
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
        <title>CLIENTES</title> 
        <link rel="stylesheet" href="../Recursos/css/bootstrap.css"/>
        <script type="text/javascript" src="../Recursos/js/jquery.js"></script>
        <script type="text/javascript" src="../Validadores/clientesvalidador.js"></script>
        <script type="text/javascript" src="../Validadores/buscadorvalidador.js"></script>
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
                    <div class="panel-footer" style="font-weight: bold">GESTIONAR CLIENTES</div>
                    <br>
                    <div class="">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label" style=" font-weight: bold">CODIGO</label>  
                                <div class="col-md-3">
                                    <input  id="cod_cliente" style="text-transform: uppercase; font-weight: bold; font-size: 12pt;
                                            background-color: #d9edf7 " 
                                        type="text" placeholder="Registro" class="form-control input-sm"
                                        onkeydown=" if (event.keyCode === 13) { listarclientesSegunFiltro(); }">
                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">R.SOCIAL</label>  
                                <div class="col-md-3">
                                    <input id="razon_cliente"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt;
                                           background-color: #d9edf7" placeholder="Ingrese R.S." class="form-control input-sm" required autofocus="">
                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">RUC</label>  
                                <div class="col-md-3">
                                    <input id="ruc_cliente"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt; 
                                           background-color: #d9edf7" placeholder="Ingrese RUC" class="form-control input-sm">
                                </div>
                            </div>
                        </div>
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label" style=" font-weight: bold">DIRECCION</label>  
                                <div class="col-md-3">
                                    <input id="dire_cliente"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt; 
                                           background-color: #d9edf7" placeholder="Ingrese direccion" class="form-control input-sm">
                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">CORREO</label>  
                                <div class="col-md-3">
                                    <input id="corr_cliente"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt; 
                                           background-color: #d9edf7" placeholder="Ingrese Correo" class="form-control input-sm">
                                </div>
                                 <label class="col-md-1 control-label" style=" font-weight: bold">TELEFONO</label>  
                                <div class="col-md-3">
                                    <input id="tel_cliente" type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt; 
                                           background-color: #d9edf7" placeholder="Ingrese  NRO. Telefono" class="form-control input-sm" >
                                </div>
                            </div>
                        </div>
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1"></label>
                                <div class="col-md-3"></div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">CIUDADES</label>  
                                <div class="col-md-3" id="comboCiudad">
                                    <select id="descri_ciudad" type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt;
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
                                <table class="table" id="miTabla" onclick="recuperar()">
                                    <thead>
                                        <tr>
                                            <th><div>CODIGO</div></th>
                                            <th><div>RUC</div></th>
                                            <th><div>RAZON SOCIAL</div></th>
                                            <th><div>TELEFONO</div></th>
                                            <th><div>DIRECCION</div></th>
                                            <th><div>CORREO</div></th>
                                            <th><div>CIUDADES</div></th>
                                            <th style="width: 30px"> <div><center><img src="../Recursos/img/update.png"/></center></div></th>
                                            <th style="width: 30px"><div><center><img src="../Recursos/img/delete.png"/></center></div></th>
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
