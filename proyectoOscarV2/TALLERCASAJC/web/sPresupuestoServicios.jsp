
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
        <script src="validador/sPresupuestoServiciosValidad.js"></script> 
        <script src="validador/genericoJS.js"></script> 
        <title>Presupuesto de Servicios</title>
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
                    <a id="btnNuevoPServicio"  class="btn btn-lg btn-success" style=" font-weight: bold"   data-toggle="modal"
                       >Nuevo </a>
                    <a id="btnConfirmarPServicio" class="btn btn-lg btn-warning glyphicon glyphicon-ok"  style=" font-weight: bold" title="Confirmar Diagnostico"></a>
                    <a id="btnRevertirPServicio" class="btn btn-lg btn-danger glyphicon glyphicon-minus-sign" style=" font-weight: bold" title="Revertir Diagnostico" ></a>
                    <a id="btnInformePServicio" class="btn btn-lg btn-primary glyphicon glyphicon-print" style=" font-weight: bold" title="Ver Informe" onclick=""></a>
                </div>

                <br>
                <br>
                <br>

                <div class="panel panel-default">
                    <div class="panel-footer" style="font-weight: bold">PLANILLA DE PRESUPUESTO DE SERVICIOS</div>

                    <div class="">
                        <div class="input-group  input-sm">
                            <span class="input-group-addon" style=" font-weight: bold">Nro. Registro*</span>
                            <input id="nroPServicio" type="text" style="background-color: #d9edf7" class="form-control" disabled="" placeholder="">
                            <span class="input-group-addon" style=" font-weight: bold" >Estado*</span>
                            <input id="estadoPServicio" type="text" style="" class="form-control" disabled="" placeholder="Estado">
                        </div>
                    </div>

                    <div class="">
                        <div class="input-group input-sm">
                            <span class="input-group-addon" style=" font-weight: bold">Buscar</span>
                            <input id="filtroDiagnostico" type="text" style="text-transform: uppercase; font-weight: bold" 
                                   class="form-control " maxlength="20" placeholder="Buscar registro...">
                        </div>
                    </div>
                    <div class="panel-body">

                        <!-- TABLAS DETALLES DE PLANILLA -->

                        <div id="scrollPlanilla" class="table-responsive">
                            <table class="table table-striped table-bordered table-hover table input-md" id="mitablaPServicio" >
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                <thead >
                                    <tr  >
                                        <th class="" >NRO. REGISTRO</th>
                                        <th class="">FECHA</th>
                                        <th class="">CLIENTE</th>
                                        <th class="">ESTADO</th>
                                        <th class="">USUARIO</th>
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

        <div class="modal fade" id="ventanaPServicio">
            <div class="modal-dialog" style="width: 80%;">
                <div class="modal-content">

                    <!--HEADER DE LA VENTANA//////////////////////////////////////////////////////////////////////--->

                    <div class="modal-header" >
                        <a class="btn btn-lg btn-primary col-md-1" style="display: none"  id="btnguardarPServicio" title=""  >Guardar</a>
                        <a class="btn btn-lg btn-success col-md-1" style="display: none" id="btntmodificarPServicio" title=""  >Guardar</a>
                        <a class="close  btn btn-lg btn-danger glyphicon glyphicon-off" id="btnclosePservicio" data-dismiss="modal" aria-hidden="btncloseDiagnosticotrue" title="Salir"></a>
                    </div>

                    <!-- //////PLANILLA DE CARGA DE DETALLES ////--->

                    <div class="panel">
                        <div class="panel panel-default">
                            <div class="panel-footer" style="font-weight: bold">NUEVO PRESUPUESTO DE SERVICIO</div>
                            <br> 
                            <div class="form-horizontal">
                                <div class="form-group">

                                    <label class="col-md-1 control-label">Nro.</label>  
                                    <div class="col-md-1">
                                        <input disabled="" id="codigoPServicio" style="text-transform: uppercase; font-weight: bold; font-size: 12pt" 
                                               name="codigo" type="text" placeholder="Codigo" class="form-control input-sm ">
                                    </div>

                                    <label class="col-md-1 control-label">Fecha</label>  
                                    <div class="col-md-2">
                                        <input disabled id="fechaPServicio" type="datetime" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               placeholder="Ingrese fecha" class="form-control input-sm ">
                                    </div>
                                    <label class="col-md-2  control-label">Nro. Diagnóstico</label>  
                                    <div class="col-md-1">
                                        <input list="lisdiagnostico" id="nroDiagnosticoPServicio" type="text" style="font-weight: bold;font-size: 12pt"
                                                class="form-control input-sm "
                                                onkeydown="
                                                           if (event.keyCode === 13) {
                                                               recuperarPServicioDiagnostico();
                                                           }">
                                          <datalist id="lisdiagnostico">
                                    </datalist>
                                    </div>
                                    <label class="col-md-2  control-label">Fecha Diagnostico</label>  
                                    <div class="col-md-2">
                                        <input disabled="" id="fechaPservicioDiagnostico" type="text" style="font-weight: bold;font-size: 12pt"
                                               class="form-control input-sm ">
                                    </div>


                                </div>
                                <div class="form-group">

                                    <label class="col-md-1 control-label">Cliente</label>  
                                    <div class="col-md-5">
                                        <input disabled id="clinteNombrePsdiagnostico" type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               class="form-control input-sm ">

                                    </div>
                                      <label class="col-md-2 control-label">Condición Pago</label>  
                                    <div class="col-md-4">
                                        <select class="form-control" id="cbm_condPago">
                                            
                                        </select>

                                    </div>


                                </div>
                                <div class="form-group">
                                    <label class="col-md-1 control-label">Diagnóstico.</label>  
                                    <div class="col-md-11">
                                        <textarea id="obsPsdiagnostico" maxlength="200" style="font-weight: bold; font-size: 12pt" 
                                                  n type="text" placeholder="Ingese el diagnóstico." class="form-control input-sm "></textarea>
                                    </div>
                                  


                                </div>
                         
                            </div>



                        </div>

                        <!--HEADER DE LA VENTANA detalle de Articulo////////////////////////////////////////////////////--->

                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label">Cod*</label>
                                <div class="col-md-1">
                                    <input list="listaarticulosPS"  name="lisart "id="v_articusPS" type="text" 
                                           placeholder="Cod" class="form-control"
                                           onkeydown="
                                                   if (event.keyCode === 13) {
                                                       traerResp();
                                                   }">
                                    <datalist id="listaarticulosPS">
                                    </datalist>
                                </div>

                                <label class="col-md-1 control-label">Desciprción</label>
                                <div class="col-md-4">
                                    <input class="form-control input-sm" disabled="" id="descripArtPServicio" type="text" placeholder="Descirpcion del articulo"
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6;font-size: 12pt"
                                           onkeyup="separadorMiles(this)" onchange="separadorMiles(this)">
                                </div>

                                <label class="col-md-1 control-label">Precio*</label>
                                <div class="col-md-2">
                                    <input disabled="" id="precioPServicio" type="text" placeholder="Cant" maxlength="3" class="form-control input-sm" 
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6; font-size: 12pt"
                                           >
                                </div>
                                <label class="col-md-1 control-label">Cantidad*</label>
                                <div class="col-md-1">
                                    <input id="cantArtPServicio" type="text" placeholder="Cant" maxlength="3" class="form-control input-sm" 
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6; font-size: 12pt"
                                           onkeyup="ValidacionesSoloNumeros(this)" onchange="ValidacionesSoloNumeros(this)"
                                           onkeydown="
                                                   if (event.keyCode === 13) {
                                                       cargaGrillaPS();
                                                   }">
                                </div>

                                <div class="col-md-1">
                                    <input disabled="" id="codArti" type="text" placeholder="" maxlength="3" class="form-control input-sm" 
                                           style="visibility: hidden;">
                                </div>
                            </div>
                        </div>
                     
                            <!-- Tabla detalle -->
                            <div class="table-responsive" style="height: 130px">
                                <table class="table table-striped table-bordered table-hover table input-sm" id="mitabladetallePServicio" >
                                    <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class=""  >
                                            <th> <span class="glyphicon glyphicon-th-list"></span> CODIGO</th>
                                            <th><span class="glyphicon glyphicon-th-list"></span>  DESCRIPCION</th>
                                            <th><span class="glyphicon glyphicon-th-list"></span>  PRECIO U.</th>
                                            <th><span class="glyphicon glyphicon-th-list"></span>  CANTIDAD</th>
                                            <th><span class="glyphicon glyphicon-th-list"></span>  SUB TOTAL</th>
                                            <th style="text-align:  center"><span class="glyphicon glyphicon-th-list"></span> OPCION</th>
                                        </tr>
                                    </thead>
                                    <tbody id="table_deta" style="font-weight: bold;font-size: 10pt">
                                    </tbody>
                                </table>

                            </div>
                         
                            <div class="form-horizontal" >
                                <div class="form-group" style="top: -20px">

                                    <label class="control-label col-md-1 col-md-offset-9" >Total:</label>
                                    <div class="col-md-2">
                                        <input style="border: transparent; text-align: center; font-size: 15pt;color: blue" class="form-control" id="totalPServicio">
                                    </div>


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




    </div>

</body>
</html>