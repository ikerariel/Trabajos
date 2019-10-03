<%-- 
    Document   : notaremisionvista
    Created on : 24/07/2019, 02:16:36 PM
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        <script src="Validadores/notaremisionvalidador.js"></script>
        <title>Nota de Remision </title>
    </head>
    <body>
        <%@ include file="menutercero.jsp"%>
        <section>
            <form class="form-horizontal"  id="defaultForm">

                <div class="col-md-9" id="botonesRemi">
                    <a id="btnNuevo" href="#ventanaRemi" class="btn btn-lg btn-success" style=" font-weight: bold"  title="Nuevo Orden de Compras" data-toggle="modal"
                       onclick="getcodigoNotaRemi(); fechaactualRemi(); mostrarEstadoRemi(); mostrarUsuarioRemi(); " >Nuevo </a>
                    <a id="btnRecuperar" class="btn btn-lg btn-info" style=" font-weight: bold" title="Recuperar Nota de Remision" data-toggle="modal" onclick="recuperarDetalleNotaRemi()">Recuperar </a>
                    <a id="btnAnular" class="btn btn-lg btn-danger" style=" font-weight: bold" title="Anular Pedido">Anular*</a>
                    <a id="btnConfirmar" class="btn btn-lg btn-warning glyphicon glyphicon-ok" style=" font-weight: bold" title="Confirmar Orden de Compras" onclick=></a>
                    <a id="btnRevertir" class="btn btn-lg btn-danger glyphicon glyphicon-minus-sign" style=" font-weight: bold" title="Revertir Confirmacion Orden de Compras" onclick=""></a>
                    <a id="btnReporte" class="btn btn-lg btn-primary glyphicon glyphicon-print" title="Reporte de Panilla" onclick="reportesOrdenComp()"></a>

                </div>
                <br>
                <br>
                <br>

                <div class="panel panel-default">
                    <div class="panel-footer" style="font-weight: bold">Planilla Nota de remision</div>
                    <div class="">
                        <div class="input-group  input-sm">
                                <span class="input-group-addon" style=" font-weight: bold">Remision Nro*</span>
                                <input id="RemiNro" type="text" style="background-color: #d9edf7" class="form-control" disabled="" placeholder="Numero de Remision">
                                <span class="input-group-addon" style=" font-weight: bold" >Estado*</span>
                                <input id="estadoRemi" type="text" style="" class="form-control" disabled="" placeholder="Estado de Remision">
                        </div>
                    </div>
                    <div class="">
                            <div class="input-group input-sm">
                                <span class="input-group-addon" style=" font-weight: bold">Buscar</span>
                                <input id="filtrarPlanillaNotaRemi" type="text" style="text-transform: uppercase; font-weight: bold" 
                                       class="form-control " maxlength="20" onkeyup="buscadorPlanillaNotaRemi()"placeholder="Buscar Lista...">
                            </div>
                        </div>
                    <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div id="scrollPlanilla" class="table-responsive">
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaPlanillaNotaRemi" onclick="seleccionarNotaRemiPlanilla()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th class="alert-success">NUMERO</th>
                                            <th class="alert-info">FECHA</th>
                                            <th class="alert-success" style="display: none"> PROVEEDOR</th>
                                            <th class="alert-info">USUARIO</th>
                                            <th class="alert-success">N.FACTURA</th>
                                            <th class="alert-danger">ESTADO</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody id="table_deta"></tbody>
                                </table>
                            </div>
                        </div>
                </div>
            </form>
        </section>
        <div class="modal fade" id="ventanaRemi">
            <div class="modal-dialog" style="width: 1200px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <a class="btn btn-lg btn-success col-md-1"  id="btnGuardar" title="" onclick="insertarNotaRemi()" >Guardar</a>
                        <a class="btn btn-lg btn-danger col-md-1"  id="btnGuardarModificado" title="" onclick="updaterNotaRemi()" >Modificar</a>
                        <a class="close  btn btn-lg btn-danger glyphicon glyphicon-off" data-dismiss="modal" aria-hidden="true" onclick="limpiarcampoNotaremi()" title="Salir"></a>
                    </div>
                    <!--CONTENIDO DE LA VENTANA--->
                    <div class="panel">
                        <div class="panel panel-default">
                            <div class="panel-footer" style="font-weight: bold">Nuevo Nota de Remision</div>
                            <br> 
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label">Codigo</label>  
                                    <div class="col-md-2">
                                        <input disabled="" id="codigoRemi" style="text-transform: uppercase; font-weight: bold; font-size: 12pt" 
                                               name="codigo" type="text" placeholder="Registro" class="form-control input-sm alert-success">
                                    </div>
                                    <label class="col-md-1 control-label">Fecha</label>  
                                    <div class="col-md-3">
                                        <input disabled id="fechaRemi" type="datetime" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               placeholder="Ingrese fecha" class="form-control input-sm alert-success">
                                    </div>
                                    <label class="col-md-1 control-label">Estado</label>  
                                    <div class="col-md-3">
                                        <input disabled id="estadoRemision"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               placeholder="Ingrese estado" class="form-control input-sm alert-success">
                                    </div>
                                    <div class="col-md-2">
                                        <input id="idestadRemi" style="visibility: hidden;" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label">Usuario</label>  
                                    <div class="col-md-2">
                                        <input disabled id="usuarioRemi" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               type="text" placeholder="Ingrese Usuario" class="form-control input-sm alert-success">
                                    </div>
                                              <label class="col-md-1 control-label">N.FACTURA</label>  
                                    <div class="col-md-3">
                                        <input id="facturacompRemi" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" 
                                               type="text" placeholder="Ingrese nro Factura" class="form-control input-sm alert-success" onclick="AbrirFacturaRemi()"
                                               onkeydown="
                                                   if (event.keyCode === 13) {
                                                       RecuperarDetalleFacturaRemi();
                                                   }">
                                    </div> 
                                    <label class="col-md-1 control-label" style="text-transform: uppercase; font-weight: bold;font-size: 12pt;visibility: hidden">Proveedor</label>  
                                    <div class="col-md-3">
                                        <input id="proveeRemi" style="text-transform: uppercase; font-weight: bold;font-size: 12pt;visibility: hidden" 
                                               type="text" placeholder="Ingrese Proveedor" class="form-control input-sm alert-success" onclick="abrirproveedorRemi()">
                                    </div>
                                    
                                                             
                                    <div class="col-md-4">
                                        <input id="idproveedorRemi" style="visibility: hidden;" type="text">
                                    </div>                                   
                                    <div class="col-md-4">
                                        <input  id="idusuaRemi" type="text" style="visibility: hidden;">
                                    </div>
                                    <div class="col-md-4">
                                        <input  id="idfacturacompRemi" type="text" style="visibility: hidden;">
                                    </div>
                                    <label class="col-md-1 control-label">Observacion</label>  
                                    <div class="col-md-6">
                                        <input id="observaRemi" type="datetime" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               placeholder="escriba una Observacion" class="form-control input-sm alert-success">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label">Cod*</label>
                                <div class="col-md-1">
                                    <input id="codgenericiMerca" type="text" placeholder="Cod" class="form-control input-sm" onclick="abrirModalMercaderiaRemi()"
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6; font-size: 12pt"
                                           onkeyup="ValidacionesSoloNumeros()" onchange="ValidacionesSoloNumeros()">
                                </div>
                                <label class="col-md-1 control-label">Desciprción</label>
                                <div class="col-md-3">
                                    <input class="form-control input-sm" disabled="" id="nombreMerca" type="text" placeholder="Descirpcion del producto"
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6;font-size: 12pt"
                                           onkeyup="separadorMiles(this)" onchange="separadorMiles(this)">
                                </div>
                                <label class="col-md-1 control-label">Precio</label>
                                <div class="col-md-2">
                                    <input id="precioMerca" disabled="" class="form-control input-sm" type="text" placeholder="Ingrese precio"
                                           style="text-transform: uppercase; font-weight: bold; background-color: #e6ffe6; font-size: 12pt"
                                           onkeyup="ValidacionesSoloNumeros(this)" onchange="ValidacionesSoloNumeros(this)">
                                </div>

                                <label class="col-md-1 control-label">Cant*</label>
                                <div class="col-md-1">
                                    <input id="cantidadMerca" type="text" placeholder="Cant" maxlength="3" class="form-control input-sm" 
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6; font-size: 12pt"
                                           onkeyup="ValidacionesSoloNumeros(this)" onchange="ValidacionesSoloNumeros(this)"
                                           onkeydown="
                                                   if (event.keyCode === 13) {
                                                       CargarMercaRemiGrilla();
                                                   }">
                                </div>
                                <div class="col-md-1">
                                    <input disabled="" id="idmercad" type="text" placeholder="" maxlength="3" class="form-control input-sm" 
                                           style="visibility: hidden;">
                                </div>
                            </div>
                        </div>
                        <div class="panel-body">
                        <!-- Tabla detalle -->
                        <div class="table-responsive" style="height: 180px">
                            <table class="table table-striped table-bordered table-hover table input-md" id="miTablaDetalleNotaRemision" onclick="SeleccionarDetalleFacturaRemi()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                <thead>
                                    <tr class="alert-dismissable" >
                                        <th style="display: none">cod</th>
                                            <th>CODIGO</th>
                                            <th>DESCRIPCION</th>
                                            <th>CANTIDAD</th>
                                            <th style="width: 30px"><div><center><img src="Recursos/img/delete.png"/></center></div></th>
                                    </tr>
                                </thead>
                                <tbody id="table_deta" style="font-weight: bold;font-size: 10pt">
                                </tbody>
                            </table>
                        </div>
                        </div>
                    </div>
                </div>
            </div> 
        </div>
        <div class="modal fade" id="ModalMercaderia">
            <div class="modal-dialog" style="width: 600px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                    <div class="container-fluid">
                        <div class="form-group">
                            <input id="filtrarMercaderiaRemi" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20" 
                                   class="form-control input-md" placeholder="Buscar Mercadeeria..." onkeyup="buscadorTablaMercaderiaRemi()">
                        </div>
                    </div>
                    <div class="panel-body">
                            <!-- Tabla detalle -->
                        <div id="scroll" class="table-responsive" style="height: 300px" >   
                            <table class="table table-striped table-bordered table-hover table input-md" id="miTablaMercaderiaRemi"
                                   onclick="seleccionarMercaderiaRemi()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                <thead>
                                    <tr class="alert-dismissable" >
                                        <th style="display: none">Cod.</th>
                                        <th>Cod.Material</th>
                                        <th>Matarial</th>
                                        <th>Precio</th>
                                    </tr>
                                </thead>
                                <tbody id="table_deta"></tbody>
                            </table>
                        </div>
                    </div>                       
                </div>
            </div>
        </div>
        <div class="modal fade" id="ModalProveeRemi">
            <div class="modal-dialog" style="width: 400px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                    <DIV class="modal-body">
                        <div class="container-fluid">
                            <div class="form-group">
                                <input id="filtrarProveedorRemi" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20"
                                       class="form-control input-md" placeholder="Buscar Mercadeeria..." onkeyup="buscadorTablaProveeRemi()">
                            </div>
                        </div>
                        <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div id="scroll" class="table-responsive" style="height: 200px" >
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaProveedoresRemi" onclick="seleccionarProveedorRemi()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th>codigo</th>
                                            <th>Proveedor</th>
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
        <div class="modal fade" id="ModalFacturaRemi">
            <div class="modal-dialog" style="width: 600px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <div>Planilla de Pedidos</div>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                    <!--CONTENIDO DE LA VENTANA--->
                    <DIV class="modal-body">
                        
                        <div class="container-fluid">
                            <div class="form-group">
                                <input id="filtrarFacturaRemi" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20" 
                                       class="form-control input-md" placeholder="Buscar Pediod..." onkeyup="buscadorTablaFacturaRemi()">
                            </div>
                        </div>
                        <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div id="scrollPlanilla" class="table-responsive">
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaFacturaRemi" onclick="seleccionarFacturaRemi()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th>CODIGO</th>
                                            <th>FECHA</th>
                                            <th>PROVEEDOR</th>
                                            <th>N.FACTURA</th>
                                            <th class="alert-info">ESTADO</th>
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
        <div class="modal fade" id="grillaPresupuesto">
            <div class="modal-dialog" style="width: 600px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <div>Planilla de Presupuesto</div>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                    <!--CONTENIDO DE LA VENTANA--->
                    <DIV class="modal-body">
                        
                        <div class="container-fluid">
                            <div class="form-group">
                                <input id="filtrarPresupuesto" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20" 
                                       class="form-control input-md" placeholder="Buscar Pediod..." onkeyup="buscadorTablaPresupuesto()">
                            </div>
                        </div>
                        <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div id="scrollPlanilla" class="table-responsive">
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaPresupuesto" onclick="seleccionPresupuesto()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th>ORDEN NRO</th>
                                            <th>FECHA</th>
                                            <th>TIPO</th>
                                            <th class="alert-info">ESTADO</th>
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
