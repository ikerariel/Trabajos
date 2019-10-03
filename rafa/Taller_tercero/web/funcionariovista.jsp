<%-- 
    Document   : funcionariovista
    Created on : 22/05/2019, 06:39:10 PM
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
        <script src="Validadores/funcionariovalidador.js"></script>
        <script type="text/javascript" src="Validadores/buscadorvalidador.js"></script>
        <title>FUNCIONARIO</title>
    </head>
    <body>
        <%@ include file="menutercero.jsp"%>
        <section>
            <form class="form-horizontal"  id="defaultForm">
                <label class="col-md-8 control-label" style=" font-weight: bold; font-size: 25pt;
                       color: #003eff">MANTENER  FUNCIONARIOS</label> 
                <BR>
                <BR>
                <BR>
                <div class="panel panel-default"> 
                    <div class="panel-footer" style="font-weight: bold" >
                        <a id="btnNuevo" class="btn btn-lg btn-success" style=" font-weight: bold" title="Nuevo" onclick="autonumerico()">Nuevo </a>
                        <a id="btnInsertar" class="btn btn-lg btn-primary" style=" font-weight: bold" title="Grabar" onclick="controlarCampofuncio(), campovaciofuncio()">Grabar </a>
                        <a id="btnModificar" class="btn btn-lg btn-info" style=" font-weight: bold" title="Modificar" onclick="amb(2)">Modificar </a>
                        <a id="btnAnular" class="btn btn-lg btn-danger" style=" font-weight: bold" title="Borrar" onclick="amb(3)">Borrar*</a>
                        <a id="btnLimpiar" class="btn btn-lg btn-warning" style=" font-weight: bold" title="Borrar" onclick="limpiarcampofuncio()">limpiar</a>
                        <a id="btnReporte" class="btn btn-lg btn-primary glyphicon glyphicon-print" title="Reporte de Panilla" onclick="reportesFuncionar()"></a>
                    </div>
                    <BR>
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
                                           background-color: #d9edf7" placeholder="Ingrese Nombre" class="form-control input-sm" required autofocus="">
                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">APELLIDO</label>  
                                <div class="col-md-3">
                                    <input id="apellido_fun"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt; 
                                           background-color: #d9edf7" placeholder="Ingrese Apellido" class="form-control input-sm">
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
                                           background-color: #d9edf7" placeholder="Ingrese  NRO. Cedula" class="form-control input-sm" >
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
                                <table class="table" id="miTabla" onclick="seleccionarfuncio()">
                                    <thead>
                                        <tr>
                                            <th class="col-md-1"><div>CODIGO</div></th>
                                            <th class="col-md-2"><div>DIRECCION</div></th>
                                            <th class="col-md-2"><div>CORREO</div></th>
                                            <th class="col-md-1"><div>CEDULA</div></th>
                                            <th class="col-md-1"><div>NOMBRE</div></th>
                                            <th class="col-md-1"><div>APELLIDO</div></th>
                                            <th class="col-md-1"><div>TELEFONO</div></th>
                                            <th class="col-md-2"><div>CARGOS</div></th>
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
    </body>
</html>
