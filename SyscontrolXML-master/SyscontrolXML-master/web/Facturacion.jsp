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
                height:200px;
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
                <div class="paneles">
                    <button type="button" id="btnguardarVenta"  class="btn btn-success">Guardar Venta</button>
                    <button type="button" class="btn btn-secondary"  id="btnnuevacarga" onclick="insertarVentaDetalle()">Nuevo</button>
                    <button type="button" class="btn btn-danger" style="display: none">Modificar</button>
                </div>
                <!-- Tab panes -->
                <div class="tab-content">
                    <div class="tab-pane container active" id="">
                        <!-- contenido cajas -->
                        <div class="card">

                            <div class="card-body">
                                <!-- contenido cards -->

                                <form>

                                    <div class="form-row">
                                        <div class="col-md-3 mb-3">
                                            <input type="text" disabled="" class="form-control" id="fnrofactura" placeholder="Monto" required="" >
                                        </div>
                                        <div class="col-md-2">
                                            <input disabled="" class="form-control" id="fcaja">
                                        </div>
                                        <div class="col-md-2 mb-3">
                                            <input disabled="" class="form-control" id="fcajero">

                                        </div>

                                        <div class="col-md-2 mb-2">
                                            <select disabled="" class="custom-select" id="fpago" >

                                            </select>
                                        </div>
                                        <div class="col-md-1 mb-2" >
                                            <span class="form-control-plaintext" style="font-size: 15pt;position: relative;left: 60px;font-weight: bold ">Total :</span>
                                        </div>
                                        <div class="col-md-2 mb-2" >
                                            <input style="text-align:  center; border: transparent; background-color: transparent" disabled="" class="form-control" id="totalVenta"  >
                                            <input style="display: none" id="ftotalventa"  >
                                        </div>

                                    </div>
                                    <div class="form-row">
                                        <div class="col-md-2 mb-2">
                                            <input type="text" class="form-control" id="fclienteci"  placeholder="Cedula" required> 
                                        </div>
                                        <div class="col-md-6">
                                            <input type="text" class="form-control" id="fclientenombre"disabled="" placeholder="Nombre del cliente" required> 
                                        </div>
                                        <div class="col-md-4">
                                            <input type="text" class="form-control" id="fvendedor"disabled=""  value="VENDEDOR MOSTRADOR" required> 
                                        </div>
                                        <div class="col-md-4">

                                            <div class="col-md-1">
                                                <label for="validationDefaultUsername" style="display: none">..</label>
                                                <input   type="text" style="display: none" id="fDIcliente" >
                                                <input  type="text" style="display: none"  id="fIDfactura">
                                                <input  type="text" style="display: none"  id="fIDimpuesto">
                                                <input  type="text" style="display: none"  id="fIdapertura">
                                            </div>

                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="col-md-1">
                                            <label for="validationDefaultUsername">Cod. </label>
                                            <input type="text" class="form-control" id="fCODarticulo" placeholder="Escriba una observaciòn" aria-describedby="inputGroupPrepend2">
                                        </div>
                                        <div class="col-md-6">
                                            <label for="validationDefaultUsername">Producto </label>
                                            <input type="text" class="form-control" id="fCODproducto" disabled="" aria-describedby="inputGroupPrepend2">
                                        </div>
                                        <div class="col-md-2">
                                            <label for="validationDefaultUsername">Precio </label>
                                            <input type="text" class="form-control" id="fprecio"  disabled="" aria-describedby="inputGroupPrepend2">
                                        </div>
                                        <div class="col-md-2">
                                            <label for="validationDefaultUsername">Impuesto </label>
                                            <input disabled="" type="text" class="form-control" id="fimpuesto" aria-describedby="inputGroupPrepend2">
                                        </div>
                                        <div class="col-md-1">
                                            <label for="validationDefaultUsername">Cantidad </label>
                                            <input type="text" class="form-control" id="fcantidad" aria-describedby="inputGroupPrepend2"
                                                   onkeydown="
                                                           if (event.keyCode === 13) {
                                                               CargarArticulo();
                                                           }">
                                        </div>

                                    </div>

                                </form>
                                <!-- fin contenido cards -->
                                <br>
                                <!-- tablas -->
                                <div class="table-responsive" id="scroll">
                                    <table class="table table-striped table-sm table-hover" id="mitablaDetlleVentas">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th scope="col" >Codigo</th>
                                                <th scope="col">Producto</th>
                                                <th scope="col">Precio Un.</th>
                                                <th scope="col">Cantidad.</th>
                                                <th scope="col">10%</th>
                                                <th scope="col">5%</th>
                                                <th scope="col">Exenta</th>
                                                <th scope="col">Sub Total</th>
                                                <th scope="col" style="display: none">codImpuesto</th>
                                                <th scope="col">Opcion</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                                </div>
                                <br>
                              
                                <div>
                                      <span style="font-size: 8pt; font-weight: bold" >Total a pagar :</span> <span id="numTexto" ></span>
                                </div>
                                <div>
                                      <span style="font-size: 8pt; font-weight: bold" >Liquidación de Iva :
                                      </span> <span style="font-size: 8pt; font-weight: bold" >IVA 5% </span> <input style="border: transparent; background-color: transparent" disabled="" type="text" id="valor5">
                                      <span style="font-size: 8pt; font-weight: bold" >IVA 10% </span> <input style="border: transparent; background-color: transparent" disabled=""  type="text" id="valor10">
                                      <span style="font-size: 8pt; font-weight: bold" >Total Iva : </span> <input style="border: transparent; background-color: transparent" disabled=""  type="text" id="totalIVA">
                                </div>
                              
                            </div>
                        </div>

                        <!-- fin contenido cajas -->

                    </div>
                </div>

                <!-- Nav tabs end -->

                <!-- fin contenido -->
            </div>
            <div style="padding-top: 100px" class="modal fade" id="v_clientefacturacion" data-keyboard="false" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h6 class="modal-title" id="exampleModalLabel" style="text-align: center">Nuevo Cliente</h6>
                        </div>

                        <div class="modal-body">
                            <div class="form-row">
                                <div class="col-md-3 mb-4">
                                    <label for="validationDefault01">Cedula : </label>
                                    <input type="text" class="form-control" id="v_modalfcedula" disabled="" splaceholder="" required="" >
                                </div>
                                <div class="col-md-6 mb-4">
                                    <label for="validationDefault01">Nombre Cliente : </label>
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
            <div style="padding-top: 50px" class="modal fade" id="v_articulosfacturacion" data-keyboard="false" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h6 class="modal-title" id="exampleModalLabel" style="text-align: center">Lista de Articulos</h6>
                        </div>

                        <div class="modal-body">
                            <div class="col-md-12 mb-2">
                                <input type="text" class="form-control" id="fbuscadorArticulo" placeholder="Buscar Articulo" required="" >
                            </div>
                            <div class="form-row">

                                <div class="table-responsive"> 
                                    <div class="headercontainer"   >
                                        <div class="tablecontainer" style="height: 260px" >
                                            <table class="table table-sm" id="miTablaarticulos"  >
                                                <thead class="thead-dark">
                                                    <tr>
                                                        <th><div>CODIGO</div></th>
                                                        <th><div>ARTIULO</div></th>
                                                        <th><div>PRECIO</div></th>
                                                        <th><div>IVA</div></th>
                                                        <th><div>CANT.</div></th>
                                                        <th><div>Opcion</div></th>
                                                        <th style="display: none">idimpuesto</th>
                                                    </tr>
                                                </thead>
                                                <tbody class="buscar" ></tbody>
                                            </table>
                                        </div>
                                    </div>     
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                                </div>

                            </div>
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
        <script src="validaciones/validacionesfacturacion.js"></script>

    </body>
</html>
