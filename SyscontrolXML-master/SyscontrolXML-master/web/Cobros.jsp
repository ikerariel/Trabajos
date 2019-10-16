<%-- 
    Document   : recargas
    Created on : 01-jul-2019, 11:46:02
    Author     : !mX - Made on Earth by Humans
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <%
        HttpSession sessionActiva = request.getSession();
        if (sessionActiva.getAttribute("sessionON") == null) {
            response.sendRedirect("/syscontrol/nologin.jsp");
        }
    %>
    <head>

        <meta charset="utf-8">
        <meta http-equiv="refresh" content="${pageContext.session.maxInactiveInterval};url=out.jsp">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="!mX">

        <title>Syscontrol</title>
        <!-- Bootstrap CSS -->
        <link href="Recursos/css/bootstrap.min.css" rel="stylesheet">

        <link href="Recursos/css/jquery-confirm.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="Recursos/css/simple-sidebar.css" rel="stylesheet">

        <!-- estilo de los paneles -->
        <style>
            .paneles{
                margin-top: 10px;
                margin-left: 15px;
            }
            #scroll{
                overflow: scroll;
                height:300px;
            }
        </style>

    </head>
    <body>
        <div class="d-flex" id="wrapper">
            <!-- Sidebar -->
            <%@ include file="menu.jsp"%>
            <!-- /#sidebar-wrapper -->

            <!-- Page Content -->
            <div id="page-content-wrapper">
                <%@ include file="navbar.jsp"%>

                <!-- Tab panes -->
                <div class="tab-content">
                    <div class="tab-pane container active" id="">
                        <!-- contenido cajas -->
                        <div class="card">

                            <div class="card-body">
                                <!-- contenido cards -->

                                <form>

                                    <div class="form-row">
                                        <div class="col-md-2 mb-2">
                                            <select type="text" class="form-control" id="ctipodoac"  required>

                                            </select> 
                                        </div>
                                        <div class="col-md-2 mb-2">
                                            <input type="text" class="form-control" id="fclienteci"  placeholder="Nro." required> 
                                        </div>
                                        <div class="col-md-5">
                                            <input type="text" style="font-weight: bold" class="form-control" id="fclientenombre"disabled="" placeholder="Nombre del cliente" required> 
                                        </div>
                                        <div class="col-md-3">
                                            <button onclick="getCobros()" type="button" class="btn btn-block btn-outline-success" id="btnconsultarCobros">Consultar Cobros</button>
                                        </div>

                                    </div>
                                    <div class="form-row">


                                    </div>

                                </form>
                                <!-- fin contenido cards -->
                                <br>
                                <!-- tablas -->
                                <div class="table-responsive" id="scroll">
                                    <table class="table table-striped table-sm table-hover" id="mitblaCobrosDettalle">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th scope="col" >#</th>
                                                <th scope="col">Nro.Factura</th>
                                                <th scope="col">Fecha Emitida.</th>
                                                <th scope="col">Monto Factura</th>
                                                <th scope="col">Monto Cobrado</th>
                                                <th scope="col">Estado.</th>
                                                <th scope="col">Opcion</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                                </div>
                                <br>
                                <div class="col-md-5">
                                    <span>Deuda Total :</span> <input disabled="" style="border: transparent; background-color: transparent" type="text" id="totalfactura">
                                </div>
                                <div class="col-md- 5">
                                    <span>Deuda Cobrada :</span> <input disabled="" style="border: transparent; background-color: transparent"  type="text" id="totalcobrado">
                                </div>


                            </div>
                        </div>

                        <!-- fin contenido cajas -->

                    </div>
                </div>

                <!-- Nav tabs end -->

                <!-- fin contenido -->
            </div>
                
                <div style="padding-top: 50px" class="modal fade" id="v_clientefacturacion" data-keyboard="false" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h6 class="modal-title" id="exampleModalLabel" style="text-align: center">Registrar Cobros</h6>
                        </div>
                   

                        <div class="modal-body">
                            <div class="form-row">
                                <div class="col-md-3 mb-4">
                                    <label for="validationDefault01">Factura Nro. : </label>
                                    <input type="text" class="form-control" id="v_modalfcedula" disabled="" splaceholder="" required="" >
                                </div>
                                <div class="col-md-6 mb-4">
                                    <label for="validationDefault01">Tipo Cobro : </label>
                                    <input type="text" class="form-control" id="v_modalfnombre" placeholder="" required="" >
                                </div>

                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button"  class="btn btn-outline-success" id="btnguardarclientefacturaicon">Guardar</button>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                        </div>

                    </div>

                </div>
            </div>

            <div class="modal fade" style="padding-top: 100px"  id="cobroview" data-backdrop="static" data-keyboard="false">
                <div class="modal-dialog" style="">
                    <div class="modal-content">
                        <!--HEADER DE LA VENTANA--->
                        <div class="modal-header">
                            <h4 class="modal-title" style="text-align: center;" id="textM">Cobros</h4>
                        </div>
                        <!--CONTENIDO DE LA VENTANA--->
                        <div class="panel">
                            <a class="close btn btn-md btn-danger" data-dismiss="modal" aria-hidden="true" title="Salir">X</a>
                            <a class="btn btn-md btn-primary" onclick=" guardarventa()"><span class="glyphicon glyphicon-floppy-save"></span> Guardar</a>
                        </div>
                        <DIV class="modal-body">
                            <form class="form-horizontal" id="miForm" >
                                <div class="form-row">
                                    <label class="control-label col-md-2">Fact Nro.</label>
                                    <div class="col-md-2">
                                        <input class="form-control" type="text" id="factura_cobro" disabled="">
                                    </div>
                                    <label class="control-label col-md-2">Tipo de Cobro</label>
                                    <div class="col-md-3">
                                        <select class="form-control" id="v_tipocobro" onchange="condicionCobro()">
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
                                            <input class="form-control" type="text" id="v_montocobrar" onkeyup=" valores('v_montocobrar')"
                                                   onkeydown="
                                                           if (event.keyCode === 13) {
                                                               agregarfilacobro();
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


        <!-- Bootstrap core JavaScript -->
        <script src="Recursos/vendor/jquery/jquery.min.js"></script>
        <script src="Recursos/vendor/jquery/bootstrap.bundle.min.js"></script>
        <!-- Menu Toggle Script -->
        <script src="Recursos/js/menu.js"></script>
        <script src="Recursos/js/jquery-confirm.min.js"></script>
        <!-- Vlidaciones Script -->
        <script src="validaciones/validacionesgenericos.js"></script>
        <script src="validaciones/validacionesCobros.js"></script>

    </body>
</html>
