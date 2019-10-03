<%-- 
    Document   : cargospruebavista
    Created on : 12/06/2018, 09:38:14 PM
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
        <link rel="stylesheet" href="../Recursos/css/font-mfizz.css">
        <link rel="stylesheet" href="../Recursos/css/font-awesome.css">
        <script src="../Recursos/js/main.js"></script>
        <script src="../Recursos/js/bootstrap.js"></script>
        <script src="../Recursos/js/jquery.backstretch.min.js"></script>
        <title>FUNCIONARIO</title>
        <link rel="stylesheet" href="../Recursos/css/bootstrap.css"/>
        <script type="text/javascript" src="../Recursos/js/jquery.js"></script>
        <script type="text/javascript" src="../Validadores/cargospruebavalidador.js"></script>
        <script type="text/javascript" src="../Validadores/buscadorvalidador.js"></script>
    </head>
    <body>
        <header>
            <div class="header-top">
                <div class="navegacion">
                    <input type="search" placeholder="buscar ...">
                </div>
            </div>
        </header>
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
                    <div class="panel-footer" style="font-weight: bold">GESTIONAR FUNCIONARIOS</div>
                    <br>
                    <div class="">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label" style=" font-weight: bold">CODIGO</label>  
                                <div class="col-md-3">
                                    <input  id="cod_funcionar" style="text-transform: uppercase; font-weight: bold; font-size: 12pt;
                                            background-color: #d9edf7 " 
                                        type="text" placeholder="Registro" class="form-control input-sm"
                                        onkeydown=" if (event.keyCode === 13) { listarfuncionarioSegunFiltro(); }">
                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">NOMBRE</label>  
                                <div class="col-md-3">
                                    <input id="nombre_fun"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt;
                                           background-color: #d9edf7" placeholder="Ingrese R.S." class="form-control input-sm" required autofocus="">
                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">APELLIDO</label>  
                                <div class="col-md-3">
                                    <input id="apellido_fun"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt; 
                                           background-color: #d9edf7" placeholder="Ingrese RUC" class="form-control input-sm">
                                </div>
                            </div>
                        </div>
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label" style=" font-weight: bold">DIRECCION</label>  
                                <div class="col-md-3">
                                    <input id="direcc_fun"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt; 
                                           background-color: #d9edf7" placeholder="Ingrese direccion" class="form-control input-sm">
                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">CORREO</label>  
                                <div class="col-md-3">
                                    <input id="correo_fun"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt; 
                                           background-color: #d9edf7" placeholder="Ingrese Correo" class="form-control input-sm">
                                </div>
                                 <label class="col-md-1 control-label" style=" font-weight: bold">CEDULA</label>  
                                <div class="col-md-3">
                                    <input id="cedula_fun" type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt; 
                                           background-color: #d9edf7" placeholder="Ingrese  NRO. Telefono" class="form-control input-sm" >
                                </div>
                            </div>
                        </div>
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label" style=" font-weight: bold">TELEFONO</label>  
                                <div class="col-md-3">
                                    <input id="telefono_fun" type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt; 
                                           background-color: #d9edf7" placeholder="Ingrese  NRO. Telefono" class="form-control input-sm" >
                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">CARGOS</label>  
                                <div class="col-md-3" id="comboCiudad">
                                    <select id="desc_cargo" type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt;
                                            background-color: #d9edf7" class="form-control input-sm"></select>
                                </div>
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
                                <table class="table" id="miTabla" onclick="recuperarfun()">
                                    <thead>
                                        <tr>
                                            <th class="col-md-1"><div>CODIGO</div></th>
                                            <th class="col-md-2"><div>DIRECCION</div></th>
                                            <th class="col-md-2"><div>CORREO</div></th>
                                            <th class="col-md-1"><div>CEDULA</div></th>
                                            <th class="col-md-1"><div>NOMBRE</div></th>
                                            <th class="col-md-1"><div>APELLIDO</div></th>
                                            <th class="col-md-1"><div>TELEFONO</div></th>
                                            <th class="col-md-1"><div>CARGOS</div></th>
                                            <th class="col-md-1"><div>CIUDADES</div></th>
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
        <!--Ventana del buscador --> 
        <!-- Modal -->
        <div class="modal fade" id="myModal" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                    <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Buscador</h4>
                    </div>
                    <div class="modal-body"><!-- Cuerpo del modal -->
                    <!-- Caja de texto del Buscador -->
                        <div class="input-group">
                            <span class="input-group-addon">Buscar</span>
                            <input class="form-control" type="text" id="filtrar" placeholder="Ingrese Pais a Buscar"/>
                        </div>
                        <!--Aqui se implementa la tabla de País -->
                        <table border="0" id="tabla_buscador" class="table table-bordered table-hover table-responsive table-condensed" onclick="capturarFila('tabla_buscador')">
                            <thead><!--Aqui van los nombres de las columnas-->
                                <tr>
                                    <th>Id</th>
                                    <th>DESCRIPCION</th>
                                </tr>
                            </thead>
                            <tbody id="tabla_buscador_body" class="abuscar"></tbody>
                        </table>                   
                    </div>
                    <div class="modal-footer">
                        DOBLE CLICK SOBRE LA FILA PARA RECUPERAR EL VALOR    
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
