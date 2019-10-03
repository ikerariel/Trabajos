<%-- 
    Document   : categoriasvista
    Created on : 13/11/2017, 07:56:15 PM
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
        <title>CATEGORIAS</title>
        <link rel="stylesheet" href="../Recursos/css/bootstrap.css"/>
        <script type="text/javascript" src="../Recursos/js/jquery.js"></script>
        <script type="text/javascript" src="../Validadores/categoriasvalidador.js"></script>
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
                    <div class="panel-footer" style="font-weight: bold">GESTIONAR CATEGORIAS</div>
                    <br>
                    <div class="">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-2 control-label" style=" font-weight: bold">CODIGO</label>  
                                <div class="col-md-4">
                                    <input  id="cod_categoria" style="text-transform: uppercase; font-weight: bold; font-size: 12pt;
                                            background-color: #d9edf7 " type="text" placeholder="Registro" class="form-control input-sm" required=""
                                        onkeydown=" if (event.keyCode === 13) { listarCategoriaSegunFiltro(); }">
                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">NOMBRE</label>  
                                <div class="col-md-4">
                                    <input id="descr_categoria"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt;
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
                                <table class=" table-striped table-bordered table-hover table input-md" id="miTabla" onclick="seleccionar()">
                                    <thead>
                                        <tr>
                                            <th><div>CODIGO</div></th>
                                            <th><div>CATEGORIAS</div></th>
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
