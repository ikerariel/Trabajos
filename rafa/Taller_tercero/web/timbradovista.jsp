<%-- 
    Document   : timbradovista
    Created on : 19/07/2019, 02:00:31 PM
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
        <script src="Validadores/timbradovalidador.js"></script>
        <title>TIMBRADO</title>
    </head>
    <body>
        <%@ include file="menutercero.jsp"%>
        <section>
            <form class="form-horizontal"  id="defaultForm">
                <label class="col-md-8 control-label" style=" font-weight: bold; font-size: 25pt; color: #003eff">MANTENER  TIMBRADO</label> 
                <BR>
                <BR>
                <BR>
                <div class="panel panel-default"> 
                    <div class="panel-footer" style="font-weight: bold" >
                        <a id="btnNuevo" class="btn btn-lg btn-success" style=" font-weight: bold" title="Nuevo" onclick="codigoTimbrado();fechaactualTimb(); MostrarEstadoTimb()">Nuevo </a>
                        <a id="btnInsertar" class="btn btn-lg btn-primary" style=" font-weight: bold" title="Insertar" onclick="grabartimbrado()">Grabar </a>
                        <a id="btnLimpiar" class="btn btn-lg btn-warning" style=" font-weight: bold" title="Borrar" onclick="limpiarcampoclient()">limpiar</a>
                        <a id="btnReporte" class="btn btn-lg btn-primary glyphicon glyphicon-print" title="Reporte de Panilla" onclick="reportesTimbrado()"></a>
                    </div>
                    <BR>
                    <div class="">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label" style=" font-weight: bold">Codigo</label>  
                                <div class="col-md-2">
                                    <input  id="codigotimb" style="text-transform: uppercase; font-weight: bold; font-size: 12pt;
                                            background-color: #d9edf7 " 
                                        type="text" placeholder="Registro" class="form-control input-sm">
                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">timbrado</label>  
                                <div class="col-md-2">
                                    <input id="nrotimbr"  type="number" style="font-weight: bold;font-size: 12pt;
                                           background-color: #d9edf7" placeholder="Ingrese timbrado" class="form-control input-sm" required autofocus="">
                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">Fecha</label>  
                                <div class="col-md-2">
                                    <input id="fechaini"  type="text" style="font-weight: bold;font-size: 12pt; 
                                           background-color: #d9edf7" placeholder="Ingrese fecha" class="form-control input-sm">
                                </div>
                                <label class="col-md-1 control-label">FechaVto.</label> 
                                <div class="col-md-2">
                                    <input id="fechaven"  type="date" style="font-weight: bold;font-size: 12pt; 
                                           background-color: #d9edf7" placeholder="Ingrese fecha" class="form-control input-sm">
                                </div>
                            </div>
                        </div>
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label" style=" font-weight: bold">Estado</label>  
                                <div class="col-md-2">
                                    <input id="estadotimb"  type="text" style="font-weight: bold;font-size: 12pt; 
                                           background-color: #d9edf7" placeholder="Ingrese Estado" class="form-control input-sm">
                                </div>
                                <label class="col-md-1 control-label">Usuario</label> 
                                <div class="col-md-2">
                                    <input id="usuariotimb"  type="text" style="font-weight: bold;font-size: 12pt; 
                                           background-color: #d9edf7" placeholder="Ingrese Usuario" class="form-control input-sm">
                                </div>
                                <label class="col-md-1 control-label">Nro Expe.</label>  
                                <div class="col-md-2">
                                    <input id="factexpe" type="number" style="text-transform: uppercase; font-weight: bold;font-size: 12pt; 
                                           background-color: #d9edf7" placeholder="Ingrese numeros" class="form-control input-sm">
                                </div>
                                <label class="col-md-1 control-label">Nro Caja</label>  
                                <div class="col-md-2">
                                    <input id="factcaja" type="number" style="text-transform: uppercase; font-weight: bold;font-size: 12pt; 
                                           background-color: #d9edf7 " placeholder="Ingrese numeros" class="form-control input-sm">
                                </div>
                            </div>
                        </div>
                        <div class="form-horizontal">
                            <div class="form-group">  
                                <div class="col-md-2">
                                    <input id="idestadotimb" type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt; 
                                           background-color: #d9edf7; visibility: hidden" placeholder="Ingrese numero" class="form-control input-sm">
                                </div>
                                <div class="col-md-2">
                                    <a class="btn btn-lg btn-block btn-success" style="border-radius: 28%" onclick="generarparametrosTimb()">
                                AGREGAR =>>
                                </a>
                                </div>
                                <div class="col-md-2">
                                    <input id="idusuariotimb" type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt; 
                                           background-color: #d9edf7; visibility: hidden" placeholder="Ingrese estado" class="form-control input-sm" onclick="abrirCiudad()">
                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">fact.desde</label>  
                                <div class="col-md-2">
                                    <input id="factdesde"  type="number" style="font-weight: bold;font-size: 12pt; 
                                           background-color: #d9edf7" placeholder="Ingrese fecha" class="form-control input-sm">
                                </div>
                                <label class="col-md-1 control-label">fact.hasta</label> 
                                <div class="col-md-2">
                                    <input id="factasta"  type="number" style="font-weight: bold;font-size: 12pt; 
                                           background-color: #d9edf7" placeholder="Ingrese fecha" class="form-control input-sm">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">BUSCAR</span>
                        <input class="form-control" type="text" id="filtrar" placeholder="Ingrese Descripcion a Buscar"
                               style="text-transform: uppercase; font-weight: bold; font-size:12pt; background-color: #d9edf7 ">
                    </div>
                    <div class="col-md-6">
                       <div class="table-responsive" > 
                        <div class="headercontainer" >
                            <div class="tablecontainer">
                                <table class=" table-striped table-bordered table-hover table input-md" id="tablaparametrosTimb" onclick="">
                                    <thead>
                                        <tr>
                                            <th class="col-md-1"><div>Secuencia</div></th>
                                            <th class="col-md-1"><div>Nro. Esxpedición</div></th>
                                            <th class="col-md-1"><div>Nro. Caja</div></th>
                                            <th class="col-md-1"><div>Numeración</div></th>
                                        </tr>
                                    </thead>
                                    <tbody class="buscar"></tbody>
                                </table>
                            </div>
                        </div>     
                    </div>
                    </div>
                    
                    <div class="table-responsive" > 
                        <div class="headercontainer" >
                            <div class="tablecontainer">
                                <table class=" table-striped table-bordered table-hover table input-md"  id="miTablaTimbrados" onclick="recuperar()">
                                    <thead>
                                        <tr>
                                            <th><div>COD</div></th>
                                            <th><div>TIMBRADO</div></th>
                                            <th><div>FECHA VENCE</div></th>
                                            <th><div>ESTADO</div></th>
                                            <th><div>FECHA INICIO</div></th>
                                            <th><div>USUARIO</div></th>
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
