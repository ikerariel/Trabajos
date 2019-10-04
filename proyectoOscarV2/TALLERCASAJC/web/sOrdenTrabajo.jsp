
<%-- 
    Document   : FacturasCompras
    Created on : 05/09/2018, 10:29:16 AM
    Author     : Oscar
--%>        

<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <script src="validador/sOrdentrabajoValidad.js"></script> 
        <script src="validador/genericoJS.js"></script> 
        <title>Orden de Tranajo</title>
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
        <%@include file="viwmenu.jsp" %>
        <%@include file="resportesmodales.jsp" %>

        <section>
            <form class="form-horizontal"  id="defaultForm">

                <div class="col-md-9" id="botonesRecepcion">
                    <a id="btnNuevoOrdenTrabajo"  class="btn btn-lg btn-success" style=" font-weight: bold"   data-toggle="modal"
                       >Nuevo </a>
                    <a id="btnConfirmarOrdenTrabajo" class="btn btn-lg btn-warning glyphicon glyphicon-ok"  style=" font-weight: bold" title="Confirmar Diagnostico"></a>
                    <a id="btnRevertirOrdenTrabajo" class="btn btn-lg btn-danger glyphicon glyphicon-minus-sign" style=" font-weight: bold" title="Revertir Diagnostico" ></a>
                    <a id="btnInformeOrdenTrabajo" class="btn btn-lg btn-primary glyphicon glyphicon-print" style=" font-weight: bold" title="Ver Informe" onclick=""></a>
                </div>

                <br>
                <br>
                <br>

                <div class="panel panel-default">
                    <div class="panel-footer" style="font-weight: bold">PLANILLA DE ORDEN DE TRABAJO</div>

                    <div class="">
                        <div class="input-group  input-sm">
                            <span class="input-group-addon" style=" font-weight: bold">Nro. Registro*</span>
                            <input id="nroOrdenTrabajo" type="text" style="background-color: #d9edf7" class="form-control" disabled="" placeholder="">
                            <span class="input-group-addon" style=" font-weight: bold" >Estado*</span>
                            <input id="estadoOrdenTrabajo" type="text" style="" class="form-control" disabled="" placeholder="Estado">
                        </div>
                    </div>

                    <div class="">
                        <div class="input-group input-sm">
                            <span class="input-group-addon" style=" font-weight: bold">Buscar</span>
                            <input id="filtroOrdenTrabajo" type="text" style="text-transform: uppercase; font-weight: bold" 
                                   class="form-control " maxlength="20" placeholder="Buscar registro...">
                        </div>
                    </div>
                    <div class="panel-body">

                        <!-- TABLAS DETALLES DE PLANILLA -->

                        <div id="scrollPlanilla" class="table-responsive">
                            <table class="table table-striped table-bordered table-hover table input-md" id="mitablaOrdenTrabajo" >
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                <thead >
                                    <tr  >
                                        <th class="" >NRO. REGISTRO</th>
                                        <th class="">FECHA</th>
                                        <th class="">FECHA ENTREGA</th>
                                        <th class="">NRO.PRESUPUESTO</th>
                                        <th class="">USUARIO</th>
                                        <th class="">ESTADO</th>
                                        <th class="" style="text-align: center">OPCIÓN</th>
                                    </tr>
                                </thead>
                                <tbody class=""></tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </form>
        </section>

        <!--/////////////  CABECERAS VENTANA DE FACTURAS COMPRAS //////////////////////////////////////////--->

        <div class="modal fade" id="ventanaOrdenTrabajo" data-backdrop="static" data-keyboard="false">
            <div class="modal-dialog" style="width: 80%;">
                <div class="modal-content">

                    <!--HEADER DE LA VENTANA//////////////////////////////////////////////////////////////////////--->

                    <div class="modal-header" >
                        <a class="btn btn-lg btn-primary col-md-1" style="display: none"  id="btnguardarOrdentrabajo" title=""  >Guardar</a>
                        <a class="btn btn-lg btn-success col-md-1" style="display: none" id="btntmodificarOrdentrabajo" title=""  >Guardar</a>
                        <a class="close  btn btn-lg btn-danger glyphicon glyphicon-off" id="btncloseOrdentrabajo" data-dismiss="modal" aria-hidden="true" title="Salir"></a>
                    </div>

                    <!-- //////PLANILLA DE CARGA DE DETALLES ////--->

                    <div class="panel">
                        <div class="panel panel-default">
                            <div class="panel-footer" style="font-weight: bold">NUEVA ORDEN DE TRABAJO</div>
                            <br> 
                            <div class="form-horizontal">
                                <div class="form-group">

                                    <label class="col-md-1 control-label">Nro.</label>  
                                    <div class="col-md-1">
                                        <input disabled="" id="codigoOrdentrabajo" style="text-transform: uppercase; font-weight: bold; font-size: 12pt" 
                                               name="codigo" type="text" placeholder="Codigo" class="form-control input-sm ">
                                    </div>

                                    <label class="col-md-1 control-label">Fecha</label>  
                                    <div class="col-md-2">
                                        <input disabled id="fechaOrdentrabajo" type="datetime" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               placeholder="Ingrese fecha" class="form-control input-sm ">
                                    </div>
                                    <label class="col-md-2  control-label">Nro. Presupuesto</label>  
                                    <div class="col-md-1">
                                        <input list="lispresupuesto" id="nropresupuestoOrdentrabajo" type="text" style="font-weight: bold;font-size: 12pt"
                                                class="form-control input-sm "
                                                onkeydown="
                                                        if (event.keyCode === 13) {
                                                            recuperarPresupuestoOT();
                                                        }">
                                            <datalist id="lispresupuesto">
                                    </datalist>
                                    </div>
                                    <label class="col-md-2  control-label">Fecha Presupuesto</label>  
                                    <div class="col-md-2">
                                        <input disabled="" id="fechapresupuestoOrdentrabajo" type="text" style="font-weight: bold;font-size: 12pt"
                                               class="form-control input-sm ">
                                    </div>


                                </div>
                                <div class="form-group">

                                    <label class="col-md-1 control-label">Cliente</label>  
                                    <div class="col-md-5">
                                        <input disabled id="clinteOrdentrabajo" type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               class="form-control input-sm ">

                                    </div>
                                    <label class="col-md-2 control-label">Fecha Entrega.</label>  
                                    <div class="col-md-4">
                                        <input class="form-control" id="fechaentregaOrdentrabajo" type="date">
                                    </div>


                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label">Observación.</label>  
                                    <div class="col-md-10">
                                        <input readonly="" id="obsOrdentrabajopresupuesto"  maxlength="200" style="font-weight: bold; font-size: 12pt" 
                                               n type="text" class="form-control input-sm " title="Doble Click para ver la observación completa">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label">Orden de Trabajo.</label>  
                                    <div class="col-md-8">
                                        <textarea id="obsOrdentrabajo" maxlength="200" style="font-weight: bold; font-size: 12pt" 
                                                  n type="text" placeholder="Ingese la descripción de la orden de trabajo." class="form-control input-sm "></textarea>
                                    </div>
                                    <label class="col-md-1 control-label">Nro.Caracteres</label> <br>
                                    <label  class="col-md-1 control-label"style="right: 90px; font-weight: bold;font-size: 12pt"  id="nroCarac"></label>
                                    <div class="col-md-2" style="display: none">
                                        <a class="form-control btn btn-block btn-primary" id="btnagregarOrdentrabajo" >
                                            <span class=" glyphicon glyphicon-download"></span> Agregar</a>
                                    </div>



                                </div>
                            </div>



                        </div>
                        <div class="panel-body" style="display: none">
                            <div class="table-responsive" style="height: 180px">
                                <table class="table table-striped table-bordered table-hover table input-md" id="mitabladetalleOrdentrabajo">
                                    <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th class="alert-info">CODIGO</th>
                                            <th class="alert-info">FECHA</th>
                                            <th class="alert-info">NRO. PRESUPUESTO</th>
                                            <th class="alert-info">FECHA PRESUPUESTO</th>
                                            <th class="alert-info">CLIENTE</th>
                                            <th class="alert-info">FECHA ENTREGA</th>
                                            <th class="alert-info">ORDEN DE TRABAJO</th>
                                            <th class="alert-info">DIAGNOSTICO</th>
                                            <th class="alert-info"></th>

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


        <div class="modal fade" id="viewClienteRecepcion" data-backdrop="static" data-keyboard="false">
            <div class="modal-dialog" style="width: 800px">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <h4 class="modal-title" style="text-align: center;" id="textM">Nuevo Cliente</h4>
                    </div>
                    <!--CONTENIDO DE LA VENTANA--->
                    <div class="panel panel-footer">
                        <a class="close btn btn-md btn-danger" data-dismiss="modal" aria-hidden="true" title="Salir">X</a>
                    </div>
                    <DIV class="modal-body">
                        <form class="form-horizontal" id="miForm" >
                            <div class="form-group" >
                                <label class="control-label col-md-2">Cliente :</label>
                                <div class="col-md-3">
                                    <input disabled="" class="form-control " id="clienteModalRecepcion"style="font-weight: bold;font-size: 10pt" type="text">
                                </div>
                                <div class="col-md-7">
                                    <input class="form-control " id="clienteNombreModal" style="font-weight: bold;font-size: 10pt"type="text">
                                </div>
                            </div>
                            <div class="form-group" >
                                <div class="col-md-12">
                                    <a class="btn btn-lg btn-default btn-block " id="tbnsavecliente" onclick="grabarcliente()" >
                                        <span class="glyphicon glyphicon-check"></span> Guardar</a>
                                </div>

                            </div>

                        </form>   
                    </div>
                </div>
            </div> 
        </div>
        <div class="modal fade" id="ventaObservacion" data-backdrop="static" data-keyboard="false">
            <div class="modal-dialog" >
                <div class="modal-content">
                    <div class="panel panel-footer">
                        <a class="close btn btn-md btn-danger" data-dismiss="modal" aria-hidden="true" title="Salir">X</a>
                    </div>
                    <DIV class="modal-body">
                        <form class="form-horizontal" id="miForm" >
                            <h3 id="vObservacion"></h3>

                        </form>   
                    </div>
                </div>
            </div> 
        </div>




    </div>

</body>
</html>