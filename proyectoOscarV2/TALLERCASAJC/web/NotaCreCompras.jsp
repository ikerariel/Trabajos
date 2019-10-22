<%-- 
    Document   : NotaCreCompras
    Created on : 04/10/2019, 12:16:32 PM
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
        <script src="validador/genericoJS.js"></script> 
        <script src="validador/NCcomprasvalidad.js"></script> 
        <title>NOTA DE CREDITOS COMPRAS</title>
        <style type="text/css">
            #scrollPlanilla{
                overflow: scroll;
                height:300px;
            }
            #scroll{
                overflow: scroll;
                height:250px;
            }
            #scroll01{
                overflow: scroll;
                height:150px;
            }

            hr {
                height: 1px;
                background-color: black;
                position: relative;
                top: -20px;
            }


        </style>
    </head>
    <body>
        <%@ include file="viwmenu.jsp"%>
        <%--  <%@include file="viwmenu.jsp" %>
            <%@include file="resportesmodales.jsp" %>  --%>

        <section>
            <form class="form-horizontal"  id="defaultForm">

                <div class="col-md-9" id="botonesNotaCreCompras">
                    <a id="btnNuevoNC"   class="btn btn-lg btn-success" style=" font-weight: bold"   data-toggle="modal"
                       >Nuevo </a>
                    <a id="btnmodificarNC" class="btn btn-lg btn-info" style=" font-weight: bold" title="Modificar Factuta Compras" data-toggle="modal" >Recuperar </a>
                    <a id="btnAnularNC" class="btn btn-lg btn-danger" style=" font-weight: bold" title="Anular Factura" >Anular*</a>
                    <a id="btnConfirmarNC" class="btn btn-lg btn-warning glyphicon glyphicon-ok"  style=" font-weight: bold" title="Confirmar Factura Compras" onclick="actualizarpresupuesto(1)"></a>
                    <a id="btnRevertirNc" class="btn btn-lg btn-danger glyphicon glyphicon-minus-sign" style=" font-weight: bold" title="Revertir Confirmacion Factura Compras" onclick="actualizarpresupuesto(3)"></a>
                    <a id="btnReporte" class="btn btn-lg btn-primary glyphicon glyphicon-print" title="Reporte de Panilla" onclick="informepresupuesto()"></a>
                </div>

                <br>
                <br>
                <br>

                <div class="panel panel-default">
                    <div class="panel-footer" style="font-weight: bold">PLANILLA DE NOTAS DE CREDITOS</div>

                    <div class="">
                        <div class="input-group  input-sm">
                            <span class="input-group-addon" style=" font-weight: bold">Nro. Registro*</span>
                            <input id="nronc" type="text" style="background-color: #d9edf7" class="form-control" disabled="" placeholder="">
                            <span class="input-group-addon" style=" font-weight: bold" >Estado*</span>
                            <input id="estadonc" type="text" style="" class="form-control" disabled="" placeholder="Estado">
                        </div>
                    </div>

                    <div class="">
                        <div class="input-group input-sm">
                            <span class="input-group-addon" style=" font-weight: bold">Buscar</span>
                            <input id="filtropresupuesto" type="text" style="text-transform: uppercase; font-weight: bold" 
                                   class="form-control " maxlength="20" onkeyup="buscarpresupuesto()"placeholder="Buscar registro...">
                        </div>
                    </div>
                    <div class="panel-body">

                        <!-- TABLAS DETALLES DE PLANILLA -->

                        <div id="scrollPlanilla" class="table-responsive">
                            <table class="table table-striped table-bordered table-hover table input-md" id="mitablaNC" onclick="seleccionNCplanilla()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                <thead>
                                    <tr class="" >
                                        <th class="">CODIGO</th>
                                        <th class="">FECHA</th>
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

        <!--/////////////  CABECERAS VENTANA DE FACTURAS COMPRAS //////////////////////////////////////////--->

        <div class="modal fade" id="ventanaNC">
            <div class="modal-dialog" style="width: 80%;">
                <div class="modal-content">

                    <!--HEADER DE LA VENTANA//////////////////////////////////////////////////////////////////////--->

                    <div class="modal-header" >
                        <a class="btn btn-lg btn-primary col-md-1" style="display: none"  id="btnguardarNC" title=""  >Guardar</a>
                        <a class="btn btn-lg btn-success col-md-1" style="display: none" id="btnmodificarNCredito" title="" onclick="insertarpresupuesto(1, 2)" >Guardar</a>
                        <a class="close  btn btn-lg btn-danger glyphicon glyphicon-off" data-dismiss="modal" aria-hidden="true" title="Salir"></a>
                    </div>

                    <!-- //////PLANILLA DE CARGA DE DETALLES ////--->

                    <div class="panel">
                        <div class="panel panel-default">
                            <div class="panel-footer" style="font-weight: bold">NUEVA NOTA CREDITO</div>
                            <br> 
                            <div class="form-horizontal">
                                <div class="form-group">

                                    <label class="col-md-1 control-label">Nro.</label>  
                                    <div class="col-md-1">
                                        <input disabled="" id="codigoNC" style="text-transform: uppercase; font-weight: bold; font-size: 12pt" 
                                               name="codigo" type="text" placeholder="Codigo" class="form-control input-sm ">
                                    </div>


                                    <label class="col-md-1 control-label">Fecha</label>  
                                    <div class="col-md-2">
                                        <input disabled id="fechaNC" type="datetime" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               placeholder="Ingrese fecha" class="form-control input-sm ">
                                    </div>


                                    <label class="col-md-2 control-label">Nota Crédito</label>  
                                    <div class="col-md-2">
                                        <input  id="nroNC" style="text-transform: uppercase; font-weight: bold; font-size: 12pt" 
                                                name="codigo" type="text" placeholder="nota credito" class="form-control input-sm "
                                                >
                                    </div>
                                    <label class="col-md-1 control-label">Timbrado</label>  
                                    <div class="col-md-2">
                                        <input  id="timbradoNC" style="text-transform: uppercase; font-weight: bold; font-size: 12pt" 
                                                name="codigo" type="text" placeholder="timbrado" class="form-control input-sm "
                                                >
                                    </div>



                                </div>
                            </div>
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-2 control-label">Nro Factura.</label>  
                                    <div class="col-md-3">
                                        <input  id="nrofacturaNC" style="text-transform: uppercase; font-weight: bold; font-size: 12pt" 
                                                name="codigo" type="text" placeholder="fatura" class="form-control input-sm "
                                                onkeydown="
                                                        if (event.keyCode === 13) {
                                                        recuperarFacturaNC();
                                                        }">
                                        <input  id="idnrofacturaNC" style="display: none">
                                    </div>
                                    <label class="col-md-2 control-label">Monto a Debitar.</label>  
                                    <div class="col-md-3">
                                        <input  id="montodebitar" disabled="" style="text-transform: uppercase; font-weight: bold; font-size: 12pt" 
                                                name="codigo" type="text" placeholder="monto" class="form-control input-sm "
                                                >
                                        <input  id="depositoNC" style="display: none">
                                    </div>

                                </div>
                            </div>
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-2 control-label">Observación.</label>  
                                    <div class="col-md-10">
                                        <input  id="obserNC" style="text-transform: uppercase; font-weight: bold; font-size: 12pt" 
                                                name="" type="text" placeholder="Observación" class="form-control input-sm "
                                               >
                                    </div>
                                

                                </div>
                            </div>


                        </div>

                        <!--HEADER DE LA VENTANA detalle de Articulo////////////////////////////////////////////////////--->

                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label">Cod*</label>
                                <div class="col-md-1">
                                    <input list="lisprodutcos"  name="lisart "id="v_produtos" type="text" 
                                           placeholder="Cod" class="form-control"
                                           onkeydown="
                                                   if (event.keyCode === 13) {
                                                       obtProducto();
                                                   }">
                                    <datalist id="lisprodutcos">
                                    </datalist>
                                </div>

                                <label class="col-md-1 control-label">Desciprción</label>
                                <div class="col-md-3">
                                    <input class="form-control input-sm" disabled="" id="descripproducto" type="text" placeholder="Descirpcion del articulo"
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6;font-size: 12pt"
                                           onkeyup="separadorMiles(this)" onchange="separadorMiles(this)">
                                </div>

                                <label class="col-md-1 control-label">Precio</label>
                                <div class="col-md-2">
                                    <input id="precioproducto" disabled="" class="form-control input-sm" type="text" placeholder="Ingrese precio"
                                           style="text-transform: uppercase; font-weight: bold; background-color: #e6ffe6; font-size: 12pt"
                                           onkeyup="ValidacionesSoloNumeros(this)" onchange="ValidacionesSoloNumeros(this)">
                                </div>

                                <label class="col-md-1 control-label">Cantidad*</label>
                                <div class="col-md-1">
                                    <input id="cantproducto" type="text" placeholder="Cant" maxlength="3" class="form-control input-sm" 
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6; font-size: 12pt"
                                           onkeyup="ValidacionesSoloNumeros(this)" onchange="ValidacionesSoloNumeros(this)"
                                           onkeydown="
                                                   if (event.keyCode === 13) {
                                                       verificarfilaNC();
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
                                <table class="table table-striped table-bordered table-hover table input-md" id="mitabladetalleNC">
                                    <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th class="alert-info">CODIGO</th>
                                            <th class="alert-info">DESCRIPCION</th>
                                            <th class="alert-info">PRECIO</th>
                                            <th class="alert-info">CANTIDAD</th>
                                            <th class="alert-info">SUB TOTAL</th>
                                            <th class="alert-info">OPCION</th>

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
        <div class="modal fade" id="ModalProveedor">
            <div class="modal-dialog" style="width: 400px;">
                <div class="modal-content">

                    <!--HEADER DE LA VENTANA--->

                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                    <DIV class="modal-body">
                        <div class="container-fluid">
                            <div class="form-group">
                                <input id="filtrarProveedores" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20"
                                       class="form-control input-md" placeholder="Buscar Articulos..." onkeyup="buscadorTablaProveedores()">
                            </div>
                        </div>
                        <div class="panel-body">

                            <!-- Tabla detalle -->

                            <div id="scroll" class="table-responsive" style="height: 200px" >
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaProveedores" onclick="seleccionarProveedores()">
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
        <div class="modal fade" id="ModalOrdenCompra">
            <div class="modal-dialog" style="width: 600px;">
                <div class="modal-content">



                    <!--HEADER DE LA VENTANA                                     ORDEN COMPRAS--->


                    <div class="modal-header">
                        <div>Planilla Orden de Compra</div>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                    <!--CONTENIDO DE LA VENTANA        ORDEN COMPRAS--->
                    <DIV class="modal-body">

                        <div class="container-fluid">
                            <div class="form-group">
                                <input id="filtrarOrdenCompra" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20" 
                                       class="form-control input-md" placeholder="Buscar Pedidos..." onkeyup="buscadorTablaOrdenComprass()">
                            </div>
                        </div>
                        <div class="panel-body">

                            <!-- Tabla detalle -->
                            <div id="scrollPlanilla" class="table-responsive">
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaOrdenComp" onclick="seleccionarOrdenComprass()">
                                    <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th>NRO.ORDEN</th>
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
        <div class="modal fade" id="ModalArticulos">
            <div class="modal-dialog" style="width: 600px;">
                <div class="modal-content">

                    <!--HEADER DE LA VENTANA--->

                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                    <div class="container-fluid">
                        <div class="form-group">
                            <input id="filtrarArticulosCompras" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20" 
                                   class="form-control input-md" placeholder="Buscar Articulos..." onkeyup="buscadorTablaArticulosCompras()">
                        </div>
                    </div>
                    <div class="panel-body">
                        <!-- Tabla detalle -->
                        <div id="scroll" class="table-responsive" style="height: 300px" >
                            <table class="table table-striped table-bordered table-hover table input-md" id="miTablaArticulosCompras"
                                   onclick="seleccionarArticulosCompras()">
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
    </body>
</html>
