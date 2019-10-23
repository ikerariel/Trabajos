<%-- 
    Document   : NotaRemision
    Created on : 05/10/2019, 01:16:38 AM
    Author     : Oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
           <%

        HttpSession sessionActivaUser = request.getSession();
        if (sessionActivaUser.getAttribute("user") == null) {
            response.sendRedirect("/TALLERCASAJC/acceso.jsp");
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
        <script src="Recursos/js/jquery.backstretch.min.js"></script>
        <script src="Recursos/js/ImagenFondo.js"></script> 
        <script src="validador/NotaRemisionvalidad.js"></script>  
        <script src="validador/genericoJS.js"></script>  
        <title>NOTA DE REMISION</title>

        <style>
            #scrollPlanilla{
                overflow: scroll;
                height:200px;
            }  
        </style> 
    </head>
    <body>
         <%@ include file="viwmenu.jsp"%> 
        <section>
            <form class="form-horizontal"  id="defaultForm">

                <div class="col-md-9" id="botonesNotaRemision">
                    <a id="btnNuevoNR" href="#ventanaNotaRemision" class="btn btn-lg btn-success" style=" font-weight: bold"   title="Nuevo Nota Remision" data-toggle="modal"
                       onclick="getcodigoRemision(); fechaactualRemision()">Nuevo </a>
                    <a id="btnModificarNR" class="btn btn-lg btn-info" style=" font-weight: bold" title="Modificar Nota Remision" data-toggle="modal" onclick="recuperarDetNotaRemision()">Recuperar </a>
                    <a id="btnAnularNR" class="btn btn-lg btn-danger" style=" font-weight: bold" title="Anular Nota Remision">Anular*</a>
                    <a id="btnConfirmarNR" class="btn btn-lg btn-warning glyphicon glyphicon-ok" style=" font-weight: bold" title="Confirmar Nota Remision" onclick=></a>
                    <a id="btnRevertirNR" class="btn btn-lg btn-danger glyphicon glyphicon-minus-sign" style=" font-weight: bold" title="Revertir Confirmacion Nota Remision" onclick=""></a>
                    <a id="btnReporteNR" class="btn btn-lg btn-primary glyphicon glyphicon-print" title="Reporte de Panilla" onclick="reportes()"></a>
                </div>

                <br>
                <br>
                <br>

                <div class="panel panel-default">
                    <div class="panel-footer" style="font-weight: bold">PLANILLA DE NOTA REMISION</div>

                    <div class="">
                        <div class="input-group  input-sm">
                            <span class="input-group-addon" style=" font-weight: bold">NotaRemi. Nro:*</span>
                            <input id="_nro_NreC" type="text" style="background-color: #d9edf7" class="form-control" disabled="" placeholder="Numero de Nota Remision">
                            <span class="input-group-addon" style=" font-weight: bold" >Estado*</span>
                            <input id="estadoRemisionC" type="text" style="" class="form-control" disabled="" placeholder="Estado de Nota Remision">
                        </div>
                    </div>

                    <div class="">
                        <div class="input-group input-sm">
                            <span class="input-group-addon" style=" font-weight: bold">Buscar</span>
                            <input id="filtrarPlanillaRemisionN" type="text" style="text-transform: uppercase; font-weight: bold" 
                                   class="form-control " maxlength="20" onkeyup="buscadorPlanillaRemision()"placeholder="Buscar Nota Remision...">
                        </div>
                    </div>
                    <div class="panel-body">

                        <!-- TABLAS DETALLES DE PLANILLA -->

                        <div id="scrollPlanilla" class="table-responsive">
                            <table class="table table-striped table-bordered table-hover table input-md" id="miTablaPlanillaRemisionN" onclick="seleccionarNotaRemision()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                <thead>
                                    <tr class="alert-dismissable" >
                                        <th class="">CODIGO</th>
                                        <th class="">FECHA</th>
                                        <th class="">REMISION NRO</th>
                                        <th class="">OBSERVACION</th>
                                        <th class="">USUARIO</th>
                                        <th class="">ESTADO</th>

                                    </tr>
                                </thead>
                                <tbody id="table_deta"></tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </form>
        </section>

        <!--/////////////  CABECERAS VENTANA DE NOTA REMISION //////////////////////////////////////////--->

        <div class="modal fade" id="ventanaNotaRemision">
            <div class="modal-dialog" style="width: 1300px;">
                <div class="modal-content">

                    <!--HEADER DE LA VENTANA//////////////////////////////////////////////////////////////////////--->

                    <div class="modal-header" >
                        <a class="btn btn-lg btn-primary col-md-1" style="display: none"  id="btnguardarRemisión" title="" onclick="InsertarNotaRemision()" >Guardar</a>
                        <a class="btn btn-lg btn-success col-md-1" style="display: none" id="btnmodificarRemision" title="" onclick="ModificarDetFacturasComprasRemision()" >Guardar</a>
                        <a class="close  btn btn-lg btn-danger glyphicon glyphicon-off" data-dismiss="modal" aria-hidden="true" title="Salir"></a>
                    </div>

                    <!-- //////PLANILLA DE CARGA DE DETALLES ////--->

                    <div class="panel">
                        <div class="panel panel-default">
                            <div class="panel-footer" style="font-weight: bold">NOTA DE REMISION</div>
                            <br> 
                            <div class="form-horizontal">
                                <div class="form-group">

                                    <label class="col-md-1 control-label">Codigo</label>  
                                    <div class="col-md-2">
                                        <input disabled="" id="codigoNRemision" style="text-transform: uppercase; font-weight: bold; font-size: 12pt" 
                                               name="codigo" type="text" placeholder="Codigo" class="form-control input-sm alert-danger">
                                    </div>

                                    <label class="col-md-1 control-label">Fecha</label>  
                                    <div class="col-md-3">
                                        <input disabled id="_fecha_Nre" type="datetime" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               placeholder="Ingrese fecha" class="form-control input-sm alert-danger">
                                    </div>

                                    <label class="col-md-1 control-label">Estado</label>  
                                    <div class="col-md-3">
                                        <input disabled id="_estado_Nre"    type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               placeholder="PENDIENTE" class="form-control input-sm alert-success">
                                    </div>

                                    <div class="col-md-2">
                                        <input id="_Idestado_Nre" style="visibility: hidden;" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-2 control-label">Nro. Factura</label>  
                                    <div class="col-md-3">
                                        <input list="listafacturas" id="_Idfactcompra_Nre" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" 
                                               type="text" placeholder="Ingrese Factuta Compra" class="form-control" 
                                             onclick="AbrirFacturasComprasRemision()"
                                               onkeydown="
                                                       if (event.keyCode === 13) {
                                                           RecuperarDetFacturasComprasRemision();
                                                       }">
                                                                                           <datalist id="listafacturas"></datalist>
                                      <input style="display: none" class="form-control" id="idfactura" type="text">
                                    </div>
                                     <label class="col-md-2 control-label">Nro. Remisión</label>  
                                    <div class="col-md-2">
                                        <input class="form-control" id="nroremision" type="text">
                                    </div>

                                    <div class="col-md-1">
                                        <input  id="_Idusuario_Nre" type="text" style="visibility: hidden;">
                                    </div>
                                </div>
                            </div>
                            <div class="form-horizontal">
                                <div class="form-group">
                                    
                                    <label class="col-md-2 control-label">Obervación.*</label>  
                                    <div class="col-md-10">
                                        <input maxlength="98" id="_obse_Nre" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"  class="form-control input-sm" required=""  autofocus onkeydown="">
                                    </div>


                                    
                                </div>
                            </div>

                        </div>

                        <!--HEADER DE LA VENTANA detalle de Articulo////////////////////////////////////////////////////--->

                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label">Cod*</label>
                                <div class="col-md-1">
                                    <input id="codgenericiArti" type="text" placeholder="Cod" class="form-control input-sm" onclick="abrirModalArticulosRemision()"
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6; font-size: 12pt"
                                           onkeyup="ValidacionesSoloNumeros()" onchange="ValidacionesSoloNumeros()">
                                </div>

                                <label class="col-md-1 control-label">Desciprción</label>
                                <div class="col-md-3">
                                    <input class="form-control input-sm" disabled="" id="nombreArti" type="text" placeholder="Descirpcion del articulo"
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6;font-size: 12pt"
                                           onkeyup="separadorMiles(this)" onchange="separadorMiles(this)">
                                </div>

                                <label class="col-md-1 control-label">Precio</label>
                                <div class="col-md-2">
                                    <input id="precioArti" disabled="" class="form-control input-sm" type="text" placeholder="Ingrese precio"
                                           style="text-transform: uppercase; font-weight: bold; background-color: #e6ffe6; font-size: 12pt"
                                           onkeyup="ValidacionesSoloNumeros(this)" onchange="ValidacionesSoloNumeros(this)">
                                </div>

                                <label class="col-md-1 control-label">Cantidad*</label>
                                <div class="col-md-1">
                                    <input id="cantidadArti" type="text" placeholder="Cant" maxlength="3" class="form-control input-sm" 
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6; font-size: 12pt"
                                           onkeyup="ValidacionesSoloNumeros(this)" onchange="ValidacionesSoloNumeros(this)"
                                           onkeydown="
                                                   if (event.keyCode === 13) {
                                                       CargarArtiRemisionGrilla();
                                                   }">
                                </div>

                                <div class="col-md-1">
                                    <input disabled="" id="codArti" type="text" placeholder="" maxlength="3" class="form-control input-sm" 
                                           style="visibility: hidden;">
                                </div>
                            </div>
                        </div>
                        <div class="panel-body">

                            <!-- Tabla detalle para cargar aeticulo -->

                            <div class="table-responsive" style="height: 180px">
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaDetNotaRemision" onclick="SeleccionarDetNotaRemision()">
                                    <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th class="alert-info">ID</th>
                                            <th class="alert-info">DESCRIPCION</th>
                                            <th class="alert-info">PRECIO</th>
                                            <th class="alert-info">CANTIDAD</th>
                                            <th class="alert-info">SUB TOTAL</th>
                                            <th  class="alert-danger" style="width: 30px"><div><center><img src="../Recursos/img/delete.png"/></center></div></th>
                                        </tr>
                                    </thead>
                                    <tbody id="table_deta" style="font-weight: bold;font-size: 10pt">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="col-xs-3 col-xs-offset-9 input-group input-group-sm">
                            <span class="input-group-addon">Total a Pagar:</span>
                            <input class="form-control" id="total" style="font-size: 15px" type="text"
                                   onkeyup="ValidacionesSoloNumeros(this)" onchange="ValidacionesSoloNumeros(this)">
                        </div>
                    </div>
                </div>
            </div> 
        </div>
        <div class="modal fade" id="ModalProveedorRemi">
            <div class="modal-dialog" style="width: 400px;">
                <div class="modal-content">

                    <!--HEADER DE LA VENTANA--->

                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                    <DIV class="modal-body">
                        <div class="container-fluid">
                            <div class="form-group">
                                <input id="filtrarProveedoresRemision" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20"
                                       class="form-control input-md" placeholder="Buscar Articulos..." onkeyup="buscadorTablaProveedoresRemision()">
                            </div>
                        </div>
                        <div class="panel-body">

                            <!-- Tabla detalle -->

                            <div id="scroll" class="table-responsive" style="height: 200px" >
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaProveedoresRemision" onclick="seleccionarProveedoresRemision()">
                                    <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
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
        <div class="modal fade" id="ModalNotaRemision">
            <div class="modal-dialog" style="width: 600px;">
                <div class="modal-content">



                    <!--HEADER DE LA VENTANA                                     FACTURA COMPRAS--->


                    <div class="modal-header">
                        <div>Planilla Factura Remision</div>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                    <!--CONTENIDO DE LA VENTANA        FACTURA COMPRAS--->
                    <DIV class="modal-body">

                        <div class="container-fluid">
                            <div class="form-group">
                                <input id="filtrarFacturasComprasRemision" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20" 
                                       class="form-control input-md" placeholder="Buscar Pedidos..." onkeyup="buscadorTablaFacturasComprasRemision()">
                            </div>
                        </div>
                        <div class="panel-body">

                            <!-- Tabla detalle -->
                            <div id="scrollPlanilla" class="table-responsive">
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaFacturasComprasRemision" onclick="seleccionarFaccturasComprasRemision()">
                                    <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th>NRO.FACTURA</th>
                                            <th>FECHA</th>
                                            <th>USUARIO</th>
                                            <th class="alert-info">ESTADOS</th>
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
        <div class="modal fade" id="ModalArticulosRemision">
            <div class="modal-dialog" style="width: 600px;">
                <div class="modal-content">

                    <!--HEADER DE LA VENTANA--->

                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                    <div class="container-fluid">
                        <div class="form-group">
                            <input id="filtrarArticulosRemision" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20" 
                                   class="form-control input-md" placeholder="Buscar Articulos..." onkeyup="buscadorTablaArticulosRemision()">
                        </div>
                    </div>
                    <div class="panel-body">
                        <!-- Tabla detalle -->
                        <div id="scroll" class="table-responsive" style="height: 300px" >
                            <table class="table table-striped table-bordered table-hover table input-md" id="miTablaArticulosRemision"
                                   onclick="seleccionarArticulosRemision()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                <thead>
                                    <tr class="alert-dismissable" >
                                        <th style="display: none">Cod.</th>
                                        <th>Cod.Articulo</th>
                                        <th>Descripcion</th>
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
    </div>
   <!--  <script src="validador/NotaRemisionvalidad.js.jsp"></script>  -->
    </body>
</html>
