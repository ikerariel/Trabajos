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
            #scrollcobro{
                overflow: scroll;
                height:250px;
            }
        </style>

    </head>
    <body>
        <div class="d-flex" id="wrapper">
            <!-- Sidebar -->
            <%@ include file="menu.jsp"%>
            <%@ include file="menu.jsp"%>
            <%@ include file="menu.jsp"%>
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

            <div style="padding-top: 10px" class="modal fade" id="cobroview" data-keyboard="false" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog  modal-xl" style="align-content: center" role="document">
                    <div class="modal-content" style="">
                        <div class="alert-primary" style="text-align: center">
                            <strong > Registrar Cobros</strong>
                        </div>
                       
                        <div class="modal-body">
                            <div class="form-row">
                                <div class="col-md-2">
                                    <label for="validationDefault01">Factura Nro. : </label>
                                    <input type="text" class="form-control" id="v_modalnrofac" disabled="" splaceholder="" required="" >
                                    <input type="text" class="form-control" id="v_idcobro" style="display: none" >
                                </div>
                                <div class="col-md-2">
                                    <label for="validationDefault01">Tipo Cobro : </label>
                                    <select class="form-control" id="v_tipocobro" onchange="condicionCobro()">
                                        <option value="1">Efectivo</option>
                                        <option value="2">Tarjeta</option>
                                        <option value="3">Cheque</option>
                                    </select>
                                </div>
                                <div class="col-md-2">
                                    <label class="control-label">Total.</label>

                                    <input class="form-control" type="text" id="v_totalcobro" disabled="">
                                </div>
                                <div class="col-md-6">
                                    <label class="control-label " style="">Cliente</label>
                                    <input class="form-control" type="text" id="v_clientenombre" disabled="">
                                </div>


                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-md-4">
                                    <div id="v_chque" style="display: none">
                                        <strong class="alert-warning" id="texcobro_v">Cobro por Cheque</strong>
                                        <div class="form-row" >
                                            <div class="">
                                                <label class="control-label">Nro.Cheque.</label>
                                                <input class="form-control" type="text" id="nrochque_ch" >
                                            </div>
                                            <div class="">
                                                <label class="control-label">Banco.</label>

                                                <select class="form-control"  id="banco_che">
                                                    <option value="1">ITAU</option>
                                                    <option value="2">VISION</option>
                                                    <option value="3">FAMILIAR</option>
                                                    <option value="4">REGIONAL</option>
                                                    <option value="5">CONTINENTAL</option>
                                                </select>
                                            </div>

                                        </div>
                                        <div class="form-row" id="v_chque" style="">

                                            <div class="">
                                                <label class="control-label">Tipo Cheque.</label>

                                                <select class="form-control" id="tipocheque_ch" >
                                                    <option value="1">AL DIA</option>
                                                    <option value="2">DIFERIDO</option>

                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div id="v_tarjeta" style="display: none">
                                         <strong class="alert-warning" id="texcobro_v">Cobro por Tarjeta</strong>
                                        <div class="form-row" >
                                             
                                        <div class="">
                                            <label class="control-label">Ent. Emisora</label>

                                            <select class="form-control" id="entemisora_t"  >
                                                <option value="4">VISA</option>
                                                <option value="5">MASTERCARD</option>
                                                <option value="6">AMERICA ESPRESS</option>
                                            </select>
                                        </div>
                                        <div class="">
                                            <label class="control-label">Tipo Tarjeta.</label>

                                            <select class="form-control" id="tarjettipo_t"  >
                                                <option value="1">CRÉDITO</option>
                                                <option value="2">DÉDBITO</option>

                                            </select>
                                        </div>
                                   
                                    </div>
                                    <div class="form-row" id="v_tarjeta" style="">
                                        <div class="">
                                            <label class="control-label">Nro.Boleta.</label>

                                            <input class="form-control" type="text" id="nroboleta_t">
                                        </div>
                                    </div>
                                    </div>

                                    

                                    <div class="form-row">
                                        <div class="">
                                            <label class="control-label">Monto a Cobrar.</label>

                                            <input class="form-control" type="text" id="v_montocobrar" 
                                                   onkeydown="
                                                           if (event.keyCode === 13) {
                                                               agregarfilacobro();
                                                           }">
                                        </div>

                                    </div>

                                </div>
                                <div class="col-md-8">
                                    <div class="table-responsive" id="scrollcobro" style="">

                                        <table class="table table-striped table-sm table-hover" id="tabladetallecobros">
                                            <thead class="thead-dark">
                                                <tr>
                                                    <th style="display: none">idtipocobro</th>
                                                    <th >Tipo Cobro</th>
                                                    <th>Nro. Cheque</th>
                                                    <th style="display: none">idbancocheque</th>
                                                    <th>Banco</th>
                                                    <th style="display: none">idtipotarjeta</th>
                                                    <th style="display: none">identidademisora</th>
                                                    <th>Ent.</th>
                                                    <th>Tarjeta</th>
                                                    <th>Nro. Boleta</th>
                                                    <th>Monto</th>
                                                    <th>Estado</th>
                                                    <th style="display: none">idtipochque</th>
                                                    <th style="display: none">tipocheque</th>
                                                </tr>
                                            </thead>

                                            <tbody></tbody>
                                        </table>


                                    </div>
                                </div>

                                <div class="form-row">

                                    <div class="">

                                        <label class="control-label ">Total Cobro :</label>
                                        <input class="form-control " id="totalcobro_v" type="text" disabled="">
                                    </div>
                                    <div class="">
                                        <label class="control-label">Diferencia :</label>
                                        <input class="form-control " id="diferencia_v" type="text" disabled="">
                                    </div>

                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button"  class="btn btn-outline-success" id="btnguardarCobro">Guardar</button>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
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
