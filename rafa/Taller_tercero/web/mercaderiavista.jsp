<%-- 
    Document   : mercaderiavista
    Created on : 22/05/2019, 06:49:08 PM
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
        <script src="Validadores/mercaderiavalidador.js"></script>
        <script type="text/javascript" src="Validadores/buscadorvalidador.js"></script>
        <title>MERCADERIAS</title>
    </head>
    <body>
        <%@ include file="menutercero.jsp"%>
        <section>
            <form class="form-horizontal"  id="defaultForm">
                <label class="col-md-8 control-label" style=" font-weight: bold; font-size: 25pt; color: #003eff">MANTENER  MERCADERIAS</label> 
                <BR>
                <BR>
                <BR>
                <div class="panel panel-default"> 
                    <div class="panel-footer" style="font-weight: bold" >
                        <a id="btnNuevo" class="btn btn-lg btn-success" style=" font-weight: bold" onclick="autonumerico()">Nuevo </a>
                        <a id="btnInsertar" class="btn btn-lg btn-primary" style=" font-weight: bold" onclick="controlarCampomerca(), campovaciomerca()">Grabar </a>
                        <a id="btnModificar" class="btn btn-lg btn-info" style=" font-weight: bold" title="Modificar" onclick="amb(2)">Modificar </a>
                        <a id="btnAnular" class="btn btn-lg btn-danger" style=" font-weight: bold" title="Borrar" onclick="amb(3)">Borrar*</a>
                        <a id="btnLimpiar" class="btn btn-lg btn-warning" style=" font-weight: bold" title="Borrar" onclick="limpiarcampomerca()">limpiar</a>
                        <a id="btnReporte" class="btn btn-lg btn-primary glyphicon glyphicon-print" title="Reporte de Panilla" onclick="reportesMercad()"></a>
                    </div>
                    <BR>
                    <div class="">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label" style=" font-weight: bold">ID</label>  
                                <div class="col-md-1">
                                    <input  id="cod_mercad" style="font-weight: bold; font-size: 12pt;
                                            background-color: #d9edf7 " 
                                        type="text" placeholder="cod" class="form-control input-sm"
                                        onkeydown=" if (event.keyCode === 13) { listarMercaderiaSegunFiltro(); }">
                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">MERCADERIA</label>  
                                <div class="col-md-3">
                                    <input id="mer_descri"  type="text" style="font-weight: bold;font-size: 12pt;
                                           background-color: #d9edf7" placeholder="Ingrese descripcion.." class="form-control input-sm" required autofocus="">
                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">COSTO</label>  
                                <div class="col-md-2">
                                    <input id="costo"  type="text" style="font-weight: bold;font-size: 12pt; 
                                           background-color: #d9edf7" placeholder="Ingrese costo" class="form-control input-sm" onkeyup="calcularprecioventa()">
                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">PRECIO</label>  
                                <div class="col-md-2">
                                    <input id="precio"  type="text" style="font-weight: bold;font-size: 12pt; 
                                           background-color: #d9edf7" placeholder="Ingrese precio" class="form-control input-sm">
                                </div>                               
                            </div>
                        </div>
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label" style=" font-weight: bold">CODIGO</label>  
                                <div class="col-md-1">
                                    <input id="genericodigo"  type="text" style=" font-weight: bold;font-size: 12pt; background-color: #d9edf7" 
                                           placeholder="Ingrese precio" class="form-control input-sm" >
                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">CATEGORIAS</label>  
                                <div class="col-md-2" id="comboCategoria">
                                    <select id="idcate" type="text" style="font-weight: bold;font-size: 12pt;
                                            background-color: #d9edf7" class="form-control input-sm"></select>
                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">MARCAS</label>  
                                <div class="col-md-2" id="comboMarcas">
                                    <select id="idmarc" type="text" style="font-weight: bold;font-size: 12pt;
                                            background-color: #d9edf7" class="form-control input-sm"></select>
                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">PROCED.</label>  
                                <div class="col-md-2" id="comboProcedencia">
                                    <select id="idproceden" type="text" style="font-weight: bold;font-size: 12pt;
                                            background-color: #d9edf7" class="form-control input-sm"></select>
                                </div>  
                                <div class="col-md-1" id="comboImpuesto">
                                    <select id="idimpuest" type="text" style="font-weight: bold;font-size: 12pt;
                                            background-color: #d9edf7" class="form-control input-sm"></select>
                                </div>
                            </div>
                        </div>
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
                                <table class="table" id="miTabla" onclick="seleccionarmerca()">
                                    <thead>
                                        <tr>
                                            <th style="width: 70px"><div>CODIGO</div></th>
                                            <th style="width: 100px"><div>MERCADERIA</div></th>
                                            <th style="width: 100px"><div>COSTO</div></th>
                                            <th style="width: 100px"><div>PRECIO</div></th>
                                            <th style="width: 100px"><div>CATEGORIA</div></th>
                                            <th style="width: 100px"><div>MARCAS</div></th>
                                            <th style="width: 100px"><div>PROCEDENCIA</div></th>
                                            <th style="width: 100px"><div>IMPUESTO</div></th>
                                            <th style="width: 100px"><div>CODIGO GENERICO</div></th>
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
