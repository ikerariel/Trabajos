<%-- 
    Document   : aperturacierrevista
    Created on : 25/07/2019, 06:08:45 PM
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
        <script src="Validadores/aperturacierrevalidador.js"></script>
         <title>Apertura Cierre</title>
    </head>
    <body>
        <%@ include file="menutercero.jsp"%>
        <section>
            <form class="form-horizontal"  id="defaultForm">

                <div class="col-md-9" id="botonesAperturaCierre">
                    <a id="btnNuevo" href="#ventanaAperCierre" class="btn btn-lg btn-success" style=" font-weight: bold"  title="Nuevo Orden de Compras" data-toggle="modal"
                       onclick="getcodigoApertura();fechaactualAperturaC(); MostrarEstadoAper()" >Abrir </a>
                    <a id="btnRecuperar" class="btn btn-lg btn-info" style=" font-weight: bold" title="Recuperar Orden de Compras" data-toggle="modal" onclick="MostrarListaApertCierre()">Cerrar </a>
                    <a id="btnReporte" class="btn btn-lg btn-primary glyphicon glyphicon-print" title="Reporte de Panilla" onclick="reportesOrdenComp()"></a>

                </div>
                <br>
                <br>
                <br>

                <div class="panel panel-default">
                    <div class="panel-footer" style="font-weight: bold">Planilla de Apertura Cierre</div>
                    <div class="">
                        <div class="input-group  input-sm">
                                <span class="input-group-addon" style=" font-weight: bold">CODIGO*</span>
                                <input id="apertNro" type="text" style="background-color: #d9edf7" class="form-control" disabled="" placeholder="Numero Apertura">
                                <span class="input-group-addon" style=" font-weight: bold" >Estado*</span>
                                <input id="estadApert" type="text" style="" class="form-control" disabled="" placeholder="Estado Apertura">
                        </div>
                    </div>
                    <div class="">
                            <div class="input-group input-sm">
                                <span class="input-group-addon" style=" font-weight: bold">Buscar</span>
                                <input id="filtrarOrdenC" type="text" style="text-transform: uppercase; font-weight: bold" 
                                       class="form-control " maxlength="20" onkeyup="buscadorPlanillaOrden()"placeholder="Buscar Lista...">
                            </div>
                        </div>
                    <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div id="scrollPlanilla" class="table-responsive">
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaApertCi" onclick="seleccionarListaAper()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th class="alert-info">CODIGO</th>
                                            <th class="alert-info">MONTO APER</th>
                                            <th class="alert-info">USUARIO</th>
                                            <th class="alert-info">CAJERO</th>
                                            <th class="alert-danger">ESTADO</th>
                                        </tr>
                                    </thead>
                                    <tbody id="table_deta"></tbody>
                                </table>
                            </div>
                        </div>
                </div>
            </form>
        </section>
        <div class="modal fade" id="ventanaAperCierre">
            <div class="modal-dialog" style="width: 1200px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <a class="btn btn-lg btn-success col-md-1"  id="btnGuardar" title="" onclick="InsertAperturaCierres()" >Abrir</a>
                        <a class="btn btn-lg btn-danger col-md-1"  id="btnModificar" title="" onclick="ModificarDetalleOrdenCo()" >Cerrar</a>
                        <a class="close  btn btn-lg btn-danger glyphicon glyphicon-off" data-dismiss="modal" aria-hidden="true" onclick="limpiarcampoOrdenp()" title="Salir"></a>
                    </div>
                    <!--CONTENIDO DE LA VENTANA--->
                    <div class="panel">
                        <div class="panel panel-default">
                            <div class="panel-footer" style="font-weight: bold">Nueva Apertura Cierre de Cajas</div>
                            <br> 
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label">Codigo</label>  
                                    <div class="col-md-2">
                                        <input disabled="" id="codigoAper" style="font-weight: bold; font-size: 12pt" 
                                               name="codigoAper" type="text" placeholder="Registro" class="form-control input-sm alert-danger">
                                    </div>
                                    <label class="col-md-1 control-label">AperturaFecha</label>  
                                    <div class="col-md-2">
                                        <input disabled id="fechaAper" type="datetime" style="font-weight: bold;font-size: 12pt"
                                               placeholder="Ingrese fecha" class="form-control input-sm alert-danger">
                                    </div>
                                    <label class="col-md-1 control-label">Estado</label>  
                                    <div class="col-md-2">
                                        <input disabled id="estadoAper"  type="text" style="font-weight: bold;font-size: 12pt"
                                               placeholder="Ingrese estado" class="form-control input-sm alert-danger">
                                    </div>
                                    <label class="col-md-1 control-label">Timbrado</label>  
                                    <div class="col-md-2">
                                        <input id="timbrAper"  type="text" style="font-weight: bold;font-size: 12pt"
                                               placeholder="Ingrese timbrado" class="form-control input-sm alert-danger" onclick="abriraperTimbrado()">
                                    </div>
                                    <div class="col-md-2">
                                        <input id="idestadoAper" style="visibility:" type="text">
                                    </div>
                                    <div class="col-md-2">
                                        <input id="idtimbAper" style="visibility: " type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label">Usuario</label>  
                                    <div class="col-md-2">
                                        <input disabled id="usuarioAper" style="font-weight: bold;font-size: 12pt"
                                               type="text" placeholder="Ingrese Usuario" class="form-control input-sm alert-danger">
                                    </div>
                                    <label class="col-md-1 control-label">Cajero</label>  
                                    <div class="col-md-2">
                                        <input id="cajeroAper" style="font-weight: bold;font-size: 12pt" 
                                               type="text" placeholder="Ingrese cajero" class="form-control input-sm alert-danger" onclick="abrirCajeros()">
                                    </div>
                                    <label class="col-md-1 control-label">Cajas</label>  
                                    <div class="col-md-2">
                                        <input id="cajaAper" style="font-weight: bold;font-size: 12pt" 
                                               type="text" placeholder="Ingrese cajas" class="form-control input-sm alert-danger" onclick="abrirCaj()">
                                    </div>
                                    
                                    <label class="col-md-1 control-label">AperMonto</label>  
                                    <div class="col-md-2">
                                        <input id="montoAper" style="font-weight: bold;font-size: 12pt" 
                                               type="text" placeholder="Ingrese montos" class="form-control input-sm alert-danger"
                                               onkeyup="SoloNumerosAper(this)" onchange="SoloNumerosAper(this)">
                                    </div>
                                    <div class="col-md-2">
                                        <input id="idcajeroAper" style="visibility: " type="text">
                                    </div>
                                    <div class="col-md-1">
                                        <input  id="idcajaAper" type="text" style="visibility: ">
                                    </div>
                                </div>
                            </div>
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label"></label>  
                                    <div class="col-md-2">
                                        <input  id="fechaCierre" style="font-weight: bold;font-size: 12pt"
                                               type="text" placeholder="Fecha Cierre" class="form-control input-sm alert-danger">
                                    </div>
                                    
                                    <label class="col-md-1 control-label"></label>  
                                    <div class="col-md-2">
                                        <input id="montoCierre" style="font-weight: bold;font-size: 12pt" 
                                               type="text" placeholder="Monto Cierre" class="form-control input-sm alert-danger">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div> 
        </div>
        <div class="modal fade" id="ModalTimbrado">
            <div class="modal-dialog" style="width: 600px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                    <DIV class="modal-body">
                        <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div id="scroll" class="table-responsive" style="height: 200px" >
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaTimb" onclick="seleccionarTimb()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th>Codigo</th>
                                            <th>Timbrado</th>
                                            <th>Estado</th>
                                            <th>Fecha.Vto</th>
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
        <div class="modal fade" id="ModalCajeros">
            <div class="modal-dialog" style="width: 600px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                    <DIV class="modal-body">
                        <div class="container-fluid">
                            <div class="form-group">
                                <input id="filtrarCajero" type="text" style="font-weight: bold" maxlength="20"
                                       class="form-control input-md" placeholder="Buscar Cajeros" onkeyup="buscadorTablaCajero()">
                            </div>
                        </div>
                        <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div id="scroll" class="table-responsive" style="height: 200px" >
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaAper" onclick="seleccionarCajeros()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th>Codigo</th>
                                            <th>Usuario</th>
                                            <th>Perfil</th>
                                            <th>Sucursal</th>
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
        <div class="modal fade" id="ModalCaj">
            <div class="modal-dialog" style="width: 600px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                    <!--CONTENIDO DE LA VENTANA--->
                    <DIV class="modal-body">
                        
                        <div class="container-fluid">
                            <div class="form-group">
                                <input id="filtrarCaj" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20" 
                                       class="form-control input-md" placeholder="Buscar Cajas" onkeyup="buscadorTablaCaj()">
                            </div>
                        </div>
                        <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div id="scrollPlanilla" class="table-responsive">
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaCaj" onclick="seleccionarCaj()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th>codigo</th>
                                            <th>cajas</th>
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
