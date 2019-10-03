<%-- 
    Document   : clivista
    Created on : 12/06/2018, 01:55:39 PM
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
        <script src="Validadores/clientvalidador.js"></script>
        <script type="text/javascript" src="Validadores/buscadorvalidador.js"></script>
        <title>CLIENTES</title>
    </head>
    <body>
        <%@ include file="menutercero.jsp"%>
        <section>
            <form class="form-horizontal"  id="defaultForm">
                <label class="col-md-8 control-label" style=" font-weight: bold; font-size: 25pt; color: #003eff">MANTENER  CLIENTES</label> 
                <BR>
                <BR>
                <BR>
                <div class="panel panel-default"> 
                    <div class="panel-footer" style="font-weight: bold" >
                        <a id="btnNuevo" class="btn btn-lg btn-success" style=" display: none; font-weight: bold" title="Nuevo" onclick="autonumerico(); abilitarInsertCli()">Nuevo </a>
                        <a id="btnInsertar" class="btn btn-lg btn-primary" style=" display: none; font-weight: bold" title="Insertar" onclick="controlarCampoclient(), guardarCliente()">Grabar </a>
                        <a id="btnModificar" class="btn btn-lg btn-info" style=" display: none; font-weight: bold" title="Modificar" onclick="amb(2)">Modificar </a>
                        <a id="btnAnular" class="btn btn-lg btn-danger" style=" display: none; font-weight: bold" title="Borrar" onclick="amb(3)">Borrar*</a>
                        <a id="btnLimpiar" class="btn btn-lg btn-warning" style=" display: none; font-weight: bold" title="Borrar" onclick="limpiarcampoclient()">limpiar</a>
                        <a id="btnReporte" class="btn btn-lg btn-primary glyphicon glyphicon-print" style=" display: none" title="Reportes" onclick="reportesCliente()"></a>
                    </div>
                    <BR>
                    <div class="">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label" style=" font-weight: bold">CODIGO</label>  
                                <div class="col-md-3">
                                    <input disabled="" id="cod_cliente" style="font-weight: bold; font-size: 12pt;
                                            background-color: #d9edf7 " 
                                        type="text" placeholder="Registro" class="form-control input-sm"
                                        onkeydown=" if (event.keyCode === 13) { listarclientesSegunFiltro(); }">
                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">R.SOCIAL</label>  
                                <div class="col-md-3">
                                    <input disabled="" id="razon_cliente"  type="text" style="font-weight: bold;font-size: 12pt; background-color: #d9edf7"
                                           placeholder="Ingrese R.S." class="form-control input-sm" onkeydown=" if (event.keyCode === 13) {pasarotrocampo()}">
                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">RUC</label>  
                                <div class="col-md-2">
                                    <input disabled="" id="ruc_cliente"  type="text" style="font-weight: bold;font-size: 12pt; 
                                           background-color: #d9edf7" placeholder="Ingrese RUC" class="form-control input-sm">
                              
                                    
                                </div>
                                      <div class="col-md-1">
                                        <input disabled="" id="cv_cliente"  type="text" style="font-weight: bold;font-size: 12pt; 
                                           background-color: #d9edf7" placeholder="Ingrese RUC" class="form-control input-sm">
                                    </div>
                            </div>
                        </div>
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label" style=" font-weight: bold">DIRECCION</label>  
                                <div class="col-md-3">
                                    <input disabled="" id="dire_cliente"  type="text" style="font-weight: bold;font-size: 12pt; 
                                           background-color: #d9edf7" placeholder="Ingrese direccion" class="form-control input-sm">
                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">CORREO</label>  
                                <div class="col-md-3">
                                    <input disabled="" id="corr_cliente"  type="text" style="font-weight: bold;font-size: 12pt;  background-color: #d9edf7" 
                                           placeholder="Ingrese Correo" class="form-control input-sm">
                                </div>
                                 <label class="col-md-1 control-label" style=" font-weight: bold">TELEFONO</label>  
                                <div class="col-md-3">
                                    <input disabled="" id="tel_cliente" type="text" style="font-weight: bold;font-size: 12pt; background-color: #d9edf7" 
                                           placeholder="Ingrese  Nro. Telefono" class="form-control input-sm">
                                </div>
                            </div>
                        </div>
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label"></label> 
                                <div class="col-md-3">
                                    <input disabled="" id="codiciudad" type="text" placeholder="" maxlength="3" class="form-control input-sm" 
                                           style="visibility: hidden; font-weight: bold; font-size: 12pt">
                                </div>
                                <label class="col-md-1 control-label">CIUDAD</label>  
                                <div class="col-md-3">
                                    <input disabled="" id="descri_ciudad" type="text" style="font-weight: bold;font-size: 12pt; 
                                           background-color: #d9edf7" placeholder="Ingrese Ciudad" class="form-control input-sm" onclick="abrirCiudad()">
                                </div>
                                
                            </div>
                        </div>
                        <%--<div class="form-inline">
                            <div class="wrap form-control col-md-3">
                                <input id="descri_ciudad" list="Colores" name="color" type="text" placeholder="Ingrese Ciudad"
                                       class="form-control col-md-5" name="colores" onclick="combociudad()"
                                           onkeydown="if (event.keyCode === 13){
                                               obtener();
                                           }">
                                    <datalist id="colres"></datalist>
                            </div>
                        </div>--%>
                    </div> 
                    <BR>
                    <div class="input-group">
                        <span class="input-group-addon">BUSCAR</span>
                        <input class="form-control" type="text" id="filtrar" placeholder="Ingrese Descripcion a Buscar"
                               style="font-weight: bold; font-size:12pt; background-color: #d9edf7 ">
                    </div>
                    <br>
                    <div class="table-responsive" > 
                        <div class="headercontainer" >
                            <div class="tablecontainer">
                                <table class=" table-striped table-bordered table-hover table input-md"  id="miTabla" onclick="recuperar()">
                                    <thead>
                                        <tr>
                                            <th class="col-md-1"><div>CODIGO</div></th>
                                            <th class="col-md-1"><div>RUC</div></th>
                                            <th class="col-md-2"><div>RAZON SOCIAL</div></th>
                                            <th class="col-md-1"><div>TELEFONO</div></th>
                                            <th class="col-md-2"><div>DIRECCION</div></th>
                                            <th class="col-md-2"><div>CORREO</div></th>
                                            <th class="col-md-2" style="display: none"><div>cod.ciudad</div></th>
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
        <div class="modal fade" id="grillaCiudades">
            <div class="modal-dialog" style="width: 300px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                    <DIV class="modal-body">
                        <div class="container-fluid">
                            <div class="form-group">
                                <input id="filtrarCiudad" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20" class="form-control input-md" placeholder="Buscar Ciudad..." onkeyup="buscadorTablaCiudades()">
                            </div>
                        </div>
                        <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div id="scroll" class="table-responsive" style="height: 200px" >
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaC" onclick="seleccionCiudades()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th style="display: none">Cod.</th>
                                        <th>CIUDAD</th>   
                                        </tr>
                                    </thead>
                                    <tbody id="table_deta"></tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div> 
        </div>
    
    </body>
</html>
