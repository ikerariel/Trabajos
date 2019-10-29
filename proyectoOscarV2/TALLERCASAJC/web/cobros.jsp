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
        <script src="validador/cobrosValidad.js"></script>  
        <script src="validador/genericoJS.js"></script>  
        <title>REGISTRAR COBROS</title>
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
            <form class="form-horizontal"  id="defaultForm"><br>

                <div class="col-md-9" id="botonesNotaRemision">
                    <a id="btnnuevocobro" class="btn btn-lg btn-primary" title="Nuevo Registro">
                        <span class="glyphicon glyphicon-check"> </span> Nuevo
                    </a>
                </div>

                <br>
                <br>
                <br>

                <div class="panel panel-default">
                    <div class="panel-footer" style="font-weight: bold">PLANILLA DE COBROS</div>

                    <div class="">
                        <div class="form-group">
                            <label class="col-md-2 control-label">Nro. Cédula :</label>
                            <div class="col-md-3">
                                <input id="v_docu" type="text" style="" class="form-control"  placeholder="Numero de cedula"
                                       onkeydown="
                                               if (event.keyCode === 13) {
                                                   getcobro();
                                               }">
                            </div>
                            <div class="col-md-7">
                                <input id="v_cliente" type="text"  class="form-control" disabled="" placeholder="Nombre cliente">
                            </div>

                        </div>
                    </div>

                    <div class="">
                        <div class="input-group input-sm">
                            <span class="input-group-addon" style=" font-weight: bold">Buscar</span>
                            <input id="filtrarPlanillaRemisionN" type="text" style="text-transform: uppercase; font-weight: bold" 
                                   class="form-control " maxlength="20" onkeyup="buscadorPlanillaRemision()"placeholder="Buscar registros...">
                        </div>
                    </div>
                    <div class="panel-body">

                        <!-- TABLAS DETALLES DE PLANILLA -->

                        <div id="scrollPlanilla" class="table-responsive">
                            <table class="table table-striped table-bordered table-hover table input-md" id="mitblacobros">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                <thead class="alert-danger">
                                    <tr class="alert-dismissable" >
                                        <th style="display: none" ><span class="glyphicon glyphicon-th-list" ></span></th>
                                        <th ><span class="glyphicon glyphicon-th-list"></span> Nro.Secuencia</th>
                                        <th ><span class="glyphicon glyphicon-th-list"></span> Fecha Emitida</th>
                                        <th ><span class="glyphicon glyphicon-th-list"></span> Nro. Factura</th>
                                        <th ><span class="glyphicon glyphicon-th-list"></span> Cliente</th>
                                        <th ><span class="glyphicon glyphicon-th-list"></span> Monto Factura</th>
                                        <th ><span class="glyphicon glyphicon-th-list"></span> ESTADO</th>
                                        <th style="display: none"><span class="glyphicon glyphicon-th-list"></span> </th>
                                        <th ><span class="glyphicon glyphicon-th-list"></span> OPCION</th>

                                    </tr>
                                </thead>
                                <tbody id="table_deta" style="background-color: #ffffff"></tbody>
                            </table>

                        </div> <hr>
                        <div class="form-group">
                            <label class="control-label col-md-2 col-lg-offset-8">Total Deuda :</label>
                            <div class="col-md-2" >
                                <input class="form-control" type="text" id="vTotaldeuda" readonly="">     
                            </div>

                        </div>
                    </div>
                </div>
            </form>
        </section>
        <div class="modal fade" id="cobroview" data-backdrop="static" data-keyboard="false">
            <div class="modal-dialog" style="width: 1000px">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <h4 class="modal-title" style="text-align: center;" id="textM">Cobros</h4>
                    </div>
                    <!--CONTENIDO DE LA VENTANA--->
                    <div class="panel panel-footer">
                        <a class="close btn btn-md btn-danger" data-dismiss="modal" aria-hidden="true" title="Salir">X</a>
                        <a class="btn btn-md btn-primary" onclick="guardarCobro()"><span class="glyphicon glyphicon-floppy-save"></span> Guardar</a>
                    </div>
                    <DIV class="modal-body">
                        <form class="form-horizontal" id="miForm" >
                            <div class="form-group">
                                <label class="control-label col-md-2">Fact Nro.</label>
                                <div class="col-md-2">
                                    <input class="form-control" type="text" id="factura_cobro" disabled="">
                                    <input class="form-control" style="display: none" type="text" id="vCodigoCobro" >
                                    <input class="form-control" style="display: none" type="text" id="vCodventa" >
                                </div>
                                <label class="control-label col-md-2">Tipo de Cobro</label>
                                <div class="col-md-3">
                                    <select class="form-control" id="v_tipocobro" onchange="condicionVentaCobro()">
                                        <option value="1">Efectivo</option>
                                        <option value="2">Tarjeta</option>
                                        <option value="3">Cheque</option>
                                    </select>
                                </div>
                                <label class="control-label col-md-1">Total.</label>
                                <div class="col-md-2">
                                    <input class="form-control" type="text" id="v_totalcobro" disabled="">
                                </div>

                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-2">Cliente.</label>
                                <div class="col-md-2">
                                    <input class="form-control" type="text" id="v_clienteci" disabled="">
                                </div>
                                <div class="col-md-6">
                                    <input class="form-control" type="text" id="v_clientenombre" disabled="">
                                </div>

                            </div>
                            <div class="">
                                <strong class="alert-warning" id="texcobro_v">Cobro por Cheque</strong>

                                <div class="form-group panel panel-footer" id="v_chque" style="display:none">
                                    <label class="control-label col-md-2 ">Nro.Cheque.</label>
                                    <div class="col-md-2">
                                        <input class="form-control" type="text" id="nrochque_ch" >
                                    </div>
                                    <label class="control-label col-md-1">Banco.</label>
                                    <div class="col-md-3">
                                        <select class="form-control"  id="banco_che">
                                            <option value="1">ITAU</option>
                                            <option value="2">VISION</option>
                                            <option value="3">FAMILIAR</option>
                                            <option value="4">REGIONAL</option>
                                            <option value="5">CONTINENTAL</option>
                                        </select>
                                    </div>
                                    <label class="control-label col-md-2">Tipo Cheque.</label>
                                    <div class="col-md-2">
                                        <select class="form-control" id="tipocheque_ch" >
                                            <option value="1">AL DIA</option>
                                            <option value="2">DIFERIDO</option>

                                        </select>
                                    </div>
                                </div>
                                <div class="form-group panel panel-footer" id="v_tarjeta" style="display:none">
                                    <label class="control-label col-md-2">Ent. Emisora</label>
                                    <div class="col-md-2">
                                        <select class="form-control" id="entemisora_t"  >
                                            <option value="4">VISA</option>
                                            <option value="5">MASTERCARD</option>
                                            <option value="6">AMERICA ESPRESS</option>
                                        </select>
                                    </div>
                                    <label class="control-label col-md-1">Tipo Tarjeta.</label>
                                    <div class="col-md-3">
                                        <select class="form-control" id="tarjettipo_t"  >
                                            <option value="1">CRÉDITO</option>
                                            <option value="2">DÉDBITO</option>

                                        </select>
                                    </div>
                                    <label class="control-label col-md-2">Nro.Boleta.</label>
                                    <div class="col-md-2">
                                        <input class="form-control" type="text" id="nroboleta_t">
                                    </div>
                                </div>

                                <div class="form-group panel panel-primary panel-footer">
                                    <label class="control-label col-md-3">Monto a Cobrar.</label>
                                    <div class="col-md-3">
                                        <input class="form-control" type="text" id="v_montocobrar" onkeyup=" numeroDecimal('v_montocobrar')"
                                               onkeydown="
                                               if (event.keyCode === 13) {
                                                   cargarfilacobro();
                                               }">
                                    </div>
                                    <div class="col-md-2">
                                        <a class="btn btn-block btn-primary"><span class="glyphicon glyphicon-download"></span></a>
                                    </div>

                                </div>
                                <div class="form-group" id="scrollcobro">
                                    <div >
                                        <table class="table table-hover table-bordered table-striped" id="tabladetallecobros">
                                            <thead>
                                                <tr>
                                                    <td style="display: none">idtipocobro</td>
                                                    <td >Tipo Cobro</td>
                                                    <td>Nro. Cheque</td>
                                                    <td style="display: none">idbancocheque</td>
                                                    <td>Banco Chque</td>
                                                    <td style="display: none">idtipotarjeta</td>
                                                    <td style="display: none">identidademisora</td>
                                                    <td>entidademisora</td>
                                                    <td>Tipo Tarjeta</td>
                                                    <td>Nro. Boleta</td>
                                                    <td>Monto</td>
                                                    <td>Estado</td>
                                                    <td style="display: none">idtipochque</td>
                                                    <td style="display: none">tipocheque</td>
                                                </tr>
                                            </thead>

                                            <tbody></tbody>
                                        </table>
                                    </div>

                                </div>
                            </div>

                            <div class="form-group" >
                                <label class="control-label col-md-6 col-lg-pull-0">Total Cobro :</label>
                                <div class="col-md-2">
                                    <input class="form-control " id="totalcobro_v" type="text" disabled="">
                                </div>
                                <label class="control-label col-md-2 col-lg-offset-0">Diferencia :</label>
                                <div class="col-md-2">
                                    <input class="form-control " id="diferencia_v" type="text" disabled="">
                                </div>



                            </div>

                        </form>   
                    </div>
                </div>
            </div> 
        </div>

    </div>
    <!--  <script src="validador/NotaRemisionvalidad.js.jsp"></script>  -->
</body>
</html>
